/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DbConnection;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author harmony
 */
public class AdministratorController extends HttpServlet {

    
    private java.util.Map<String, String[]> sessionMap = new HashMap<String, String[]>();// create HashMap
    
    String train_file = "";
    File file1;
    
    protected void administratorLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            

            DbConnection admin_login = new DbConnection();

            String[] administrator_details = admin_login.administratorLogin(username, password);

            String administrator_password = administrator_details[0];
            String firstName = administrator_details[1];
            String lastName = administrator_details[2];
            String username1 = administrator_details[3];

            String[] sessionData = {username1, firstName, lastName};

            if (username != null || password != null) {

                if (!"".equals(username) || !"".equals(password)) {

                    if (administrator_password.equals(password)) {

                            HttpSession session = request.getSession(true);

                            String sessionId = session.getId();

                            sessionMap.put(sessionId, sessionData);

                            String[] sessionMapValues = sessionMap.get(sessionId);

                            String sessionFirstName = sessionMapValues[1];
                            String sessionLastName = sessionMapValues[2];
                            String sessionUserName = sessionMapValues[0];

                            
                            request.setAttribute("sessionId", sessionId);
                            request.setAttribute("sessionFirstName", sessionFirstName);
                            request.setAttribute("sessionLastName", sessionLastName);
                            request.setAttribute("sessionUserName", sessionUserName);

                            RequestDispatcher rd = request.getRequestDispatcher("/admin/administrator_dashboard.jsp");

                            rd.forward(request, response);
                                                       
                        
                    }
                }
            }

        } catch (ClassNotFoundException | NumberFormatException | ServletException | IOException error) {

            error.printStackTrace();
        }
    }
    
    public void goToUploadTrainingFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sessionId = request.getParameter("sessionId");
        

        String[] sessionMapValues = sessionMap.get(sessionId);

        String sessionFirstName = sessionMapValues[2];
        String sessionLastName = sessionMapValues[1];
        String sessionUserName = sessionMapValues[0];

        request.setAttribute("sessionId", sessionId);
        request.setAttribute("sessionFirstName", sessionFirstName);
        request.setAttribute("sessionLastName", sessionLastName);
        request.setAttribute("sessionUserName", sessionUserName);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/upload_training_file.jsp");

        rd.forward(request, response);
    }
    
    
    public void uploadTrainFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException {
        
       
        String sessionId = "";
        
        String rootPath = System.getProperty("catalina.home");
            ServletContext servletContext = getServletContext();
            String relativePath = servletContext.getInitParameter("fileUploads1.dir");

            File file = new File(rootPath + File.separator + relativePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // Verify the content type
            String contentType = request.getContentType();

            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                // Create a factory for disk-based file items
                DiskFileItemFactory fileFactory = new DiskFileItemFactory();

                File filesDir = (File) (file);

                fileFactory.setRepository(filesDir);

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(fileFactory);

                // Parse the request to get file items.
                List<FileItem> fileItemsList = upload.parseRequest(request);


                
                
                // Process the uploaded items
                Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
                while (fileItemsIterator.hasNext()) {

                   FileItem  fileItem = fileItemsIterator.next();

                    if (fileItem.isFormField()) {

                        String name = fileItem.getFieldName();
                        String value = fileItem.getString();

                        if (name.equals("sessionId")) {
                            sessionId = value;
                        }
                        
                    } else {
                        
                    
                        train_file = rootPath + File.separator + relativePath + File.separator + fileItem.getName();
                        System.out.println("This is what's in train_file: " + train_file);
                        file1 = new File(train_file);                       
                        
                        System.out.println("This is what's in rootPath: " + rootPath);
                        System.out.println("This is what's in relativePath: " + relativePath);
                        System.out.println(fileItem.getName());
                        
                        
                        try {
                            fileItem.write(file1);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            }
            
            String[] sessionMapValues = sessionMap.get(sessionId);

            String sessionFirstName = sessionMapValues[2];
            String sessionLastName = sessionMapValues[1];
            String sessionUserName = sessionMapValues[0];
            
            System.out.println("fileItem Name is"+file1.getName());
            
            trainModel(request, response);
            
            request.setAttribute("sessionId", sessionId);
            request.setAttribute("sessionFirstName", sessionFirstName);
            request.setAttribute("sessionLastName", sessionLastName);
            request.setAttribute("sessionUserName", sessionUserName);

            
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/training_successful.jsp");
            rd.forward(request, response);
            
    }
    
    public void trainModel(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, FileNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String modelFileName = "en-sentiment1.bin";
        
        String rootPath = System.getProperty("catalina.home");
        ServletContext servletContext = getServletContext();
        String relativePath = servletContext.getInitParameter("fileUploads1.dir");
        
        String modelFile = rootPath + File.separator + relativePath + File.separator + modelFileName;
        
        
        
        try  {
            
            DoccatModel model = null;
            DoccatFactory df = new DoccatFactory();     
            
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(file1);
           
            ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, StandardCharsets.UTF_8);            
            
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);            
            
            model = DocumentCategorizerME.train("en", sampleStream,TrainingParameters.defaultParams(),df);           
            
            OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
           
            model.serialize(modelOut);
        
        }catch(Exception e){}
        
        
    }
    

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sessionId = request.getParameter("sessionId");
                
        sessionMap.remove(sessionId);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/adminLogin.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String administrator_action = request.getParameter("administrator_action");

            switch (administrator_action) {

                case "administrator_login":
                    administratorLogin(request, response);
                    break;
                    
                case "go_to_upload_training_file":
                    goToUploadTrainingFile(request, response);
                    break;
                    
                case "upload_train_file":
                    uploadTrainFile(request, response);
                    break;
                    
               /** case "go_to_add_room":
                    goToAddRoom(request, response);
                    break;
                    
                case "add_room":
                    addRoom(request, response);
                    break;**/
                   
                case "logout":
                    logout(request, response);
                    break;
            }

        } catch (ServletException | IOException | ClassNotFoundException | SQLException | FileUploadException error) {

            error.printStackTrace();
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
