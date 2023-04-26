/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author harmony
 */
public class UserController extends HttpServlet {

    private java.util.Map<String, String[]> sessionMap = new HashMap<String, String[]>();// create HashMap
    
    String phoneType1 = "";
    String smartPhoneBrand = "";
    String brandType = "";
    String model = "";
    String concat = brandType + "-" + model;
    //String[] cleaned;
    List<String> outputList = new ArrayList();
    List<String> bestCategoryList = new ArrayList();
    
    String train_file = "";
    File file1;
    
    int timeToPause = 60000;
   
    public void goToUploadDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sessionId = request.getParameter("sessionId");
        

        String[] sessionMapValues = sessionMap.get(sessionId);

        String sessionFirstName = sessionMapValues[2];
        String sessionLastName = sessionMapValues[1];
        String sessionUserName = sessionMapValues[0];

        request.setAttribute("sessionId", sessionId);
        request.setAttribute("sessionFirstName", sessionFirstName);
        request.setAttribute("sessionLastName", sessionLastName);
        request.setAttribute("sessionUserName", sessionUserName);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/user/uploadDocument.jsp");

        rd.forward(request, response);
    }
    
    public void goToMobilePhoneSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String phoneType =request.getParameter("phoneType") ;
        System.out.println("Phone Type is: " + phoneType);
        request.setAttribute("phoneType", phoneType);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/enterProductDetails.jsp");

        rd.forward(request, response);
    }
   
    public void getRecomendation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("we have reach getRecommendation Method");
         phoneType1 = request.getParameter("phoneType");
         
         System.out.println("Phone type is :" + phoneType1);
         smartPhoneBrand = request.getParameter("smartPhoneBrand");
         System.out.println("smartPhoneBrand is :" + smartPhoneBrand);
         brandType = request.getParameter("brandType");
         model = request.getParameter("model");         
         concat = brandType + "-" + model;  
         System.out.println("concat is :" + concat);
         
         getReviews(request,response);
         
         request.setAttribute("outputList", outputList);
         request.setAttribute("bestCategoryList", bestCategoryList);
         
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");

         rd.forward(request, response);
    }
    
    /**public void getReviews1(String phoneType1,String smartPhoneBrand,String concat){
        System.out.println("we have reach getReviews Method");
        try {
            Document document = Jsoup.connect("https://www.jumia.com.ng/" + phoneType1 + "/" + smartPhoneBrand + "/").get();
            System.out.println("concat is :" + concat);
            System.out.println("https://www.jumia.com.ng/" + phoneType1 + "/" + smartPhoneBrand + "/");
            Elements links = document.select("a[href*=" + concat + "]");

            for (Element link : links) {

                System.out.println(link.absUrl("href"));
                Document modelLink = Jsoup.connect(link.absUrl("href")).get();
                Elements allReviews = modelLink.select("div.reviews");
                for (Element reviewElement : allReviews) {

                    Elements review = reviewElement.select("div.detail");
                    //System.out.println(review.text());
                    String userReview = review.text();
                    
                    String[] cleaned = userReview.split("Read more Hide");
                    String[] classifyDocumentsDetails = classifyDocuments(cleaned);
                    
                    for (String eacheReview : cleaned) {
                        
                        String eacheReview1 = eacheReview.replace("Read more Hide", " ");
                        
                        String getAllResults = classifyDocumentsDetails[0];
                        String getAllBestCategory = classifyDocumentsDetails[1];
                        
                         String outputString = eacheReview1 + " " + getAllResults + " " + getAllBestCategory;
                            
                         System.out.println(outputString);
                            
                         outputList.add(outputString);
                         bestCategoryList.add(getAllBestCategory);
                    }
                                       
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }**/

    public void getReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        System.out.println("we have reach getReviews Method");
        
        String productURL = request.getParameter("productURL");
        
        List<String> reviewList = new ArrayList();
       
        try {
            Document document = visitLinkViaJSOUP(productURL);
            
            Elements links = document.select("p.-pvs");
            
            if(links.isEmpty()){
                
                String noReviews = "There are no reviews for this product";
                System.out.println(noReviews);
            }

            for (Element link : links) {
                
                String review = link.text();
                System.out.println(review);
                reviewList.add(review);
                
                for (String eacheReview : reviewList) {
                    
                    String[] classifyDocumentsDetails = classifyDocuments(review);
                        
                        String getAllResults = classifyDocumentsDetails[0];
                        String getAllBestCategory = classifyDocumentsDetails[1];
                        
                         String outputString = eacheReview + " " + getAllResults + " " + getAllBestCategory;
                            
                         System.out.println(outputString);
                            
                         outputList.add(outputString);
                         bestCategoryList.add(getAllBestCategory);
                    }  
            }
            
            request.setAttribute("reviewList", reviewList);
            request.setAttribute("outputList", outputList);
            request.setAttribute("bestCategoryList", bestCategoryList);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");

            rd.forward(request, response);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void listAvailableProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        System.out.println("we have reach listAvailableProducts Method");
        List<String> productList = new ArrayList();
        String smartPhoneBrand1 = request.getParameter("smartPhoneBrand");
        System.out.println(smartPhoneBrand1);
        String url = "https://www.jumia.com.ng/" + smartPhoneBrand1 + "/";
        System.out.println(url);
        try {
            
            Document document =  visitLinkViaJSOUP(url);
            Elements links = document.select("a.core");
            
             for (Element link : links) {
                productList.add(link.absUrl("href"));
             }
             
            request.setAttribute("productList", productList);
            
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productList.jsp");

            rd.forward(request, response);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public Document visitLinkViaJSOUP(String URL) throws IOException{
        
        Document pageContent = null;
        
        try {
            Connection.Response response = Jsoup.connect(URL)
                    .timeout(60000)
                    .method(Connection.Method.GET)
                    .execute();
            
            
           System.out.println("Status Code is: " + response.statusCode());
           System.out.println("Status Message is: " + response.statusMessage());
          //System.out.println("Status Message is: " + response.body());
           System.out.println("Cookies is: " + response.cookies());
           
           pageContent = response.parse();
           
            
        } catch (HttpStatusException ex) {
            
            ex.getMessage();
            ex.getLocalizedMessage();
        }
        
        return pageContent;
    }
    
    public String[] visitLink(String url) throws IOException, ClassNotFoundException, FileNotFoundException{
         
         String pageContent = "";
         
         int responseCode = 0;
         
         //DbConnection dbObject = new DbConnection();
         
         try {
             
             String PROTOCOL = "https";
             String HOSTNAME = "www.researchgate.net";//www.researchgate.net/"
             String PARAMETERS = "/"+url;
             
             URL u = new URL(PROTOCOL, HOSTNAME, PARAMETERS);
             
             System.out.println("This is the url formed in visitLink: " + u.toString());
             
             //URL u = new URL(url);
             
             HttpsURLConnection http = (HttpsURLConnection) u.openConnection();
             
             http.setRequestMethod("GET");
             http.setDoOutput(true);
             http.setUseCaches(false);
             http.setRequestProperty("Content-Type", "text/html");
             http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36 Edg/102.0.1245.33");

             //Connect to the server
             http.connect();
             
             responseCode = http.getResponseCode();
             System.out.println("GET Response Code :: " + responseCode);
             System.out.println(http.getResponseMessage());
             System.out.println(http.getErrorStream());
             System.out.println(http.getHeaderFields());
             
             if (responseCode == HttpURLConnection.HTTP_OK) {

                 BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                 String line = "";

                 while ((line = in.readLine()) != null) {
                     pageContent += line;
                 }
                 in.close();
                 
                 //dbObject.storeLinkRetrieved(email, topicName,entryFileName,url,String.valueOf(responseCode));
             }
             if(responseCode == 429 || responseCode == 403){
                 pause(timeToPause);
                 System.out.println("Process stopped for 60 seconds");
                 //dbObject.storeLinkRetrieved(email, topicName,entryFileName,url,String.valueOf(responseCode));
             }
            
        } catch (MalformedURLException e) {
            System.out.println("The cause is " + e.getCause());
            System.out.println("The mesage is " + e.getMessage());
            System.out.println("The localized mesage is " + e.getLocalizedMessage());
            e.printStackTrace();
           
        }
         
         //return pageContent;
         return  new String[]{pageContent,String.valueOf(responseCode),};
     }
    
    public void pause(int ms){
        
        try{
            Thread.sleep(ms);
        }catch(InterruptedException ex){
            System.err.format("The problem is: ", ex);
            Thread.currentThread().interrupt();
        }
    }
    
    public String[] classifyDocuments1(String[] reviews)
            throws IOException, FileNotFoundException {

        String modelFileName = "en-sentiment1.bin";

        String category = "";
        String category1 = "";
        String category2 = "";
        
        String rootPath = System.getProperty("catalina.home");
        ServletContext servletContext = getServletContext();
        String relativePath = servletContext.getInitParameter("fileUploads1.dir");
        
        String modelFile = rootPath + File.separator + relativePath + File.separator + modelFileName;
        

        Tokenizer tokenizer = WhitespaceTokenizer.INSTANCE;

        try {
            
            

            for (String s : reviews) {
                String stringUserReview = s;
                //System.out.println(stringUserReview);
                String[] inputText = tokenizer.tokenize(stringUserReview);
                InputStream modelIn = new FileInputStream(modelFile);
                

                DoccatModel model1 = new DoccatModel(modelIn);
                DocumentCategorizerME categorizer = new DocumentCategorizerME(model1);

                double[] outcomes = categorizer.categorize(inputText);

                for (int i = 0; i < categorizer.getNumberOfCategories(); i++) {
                    category = categorizer.getCategory(i);
                    //System.out.println(category + " - " + outcomes[i]);
                }

                category1 = categorizer.getBestCategory(outcomes);
                //System.out.println(categorizer.getBestCategory(outcomes));
                //System.out.println(categorizer.getAllResults(outcomes));
                category2 = categorizer.getAllResults(outcomes);
            }
           
        } catch (Exception e) {

            e.printStackTrace();
        }

        return new String[]{category2, category1};
    }
    
    
    
     public String[] classifyDocuments(String reviews)
            throws IOException, FileNotFoundException {
         
         String servletContext2 = getServletContext().getRealPath("/");
         String servletContext1 = servletContext2.replace("\\build\\web\\", "");

        String modelFileName = "en-sentiment3.bin";

        String category = "";
        String category1 = "";
        String category2 = "";
        
        String rootPath = System.getProperty("catalina.home");
        ServletContext servletContext = getServletContext();
        String relativePath = servletContext.getInitParameter("fileUploads1.dir");
        
        //String modelFile = rootPath + File.separator + relativePath + File.separator + modelFileName;
        String modelFile = servletContext1 + File.separator + modelFileName;
        System.getProperty(modelFile); 
         
        Tokenizer tokenizer = WhitespaceTokenizer.INSTANCE;

        try {
            
            

            //for (String s : reviews) {
                String stringUserReview = reviews;
                //System.out.println(stringUserReview);
                String[] inputText = tokenizer.tokenize(stringUserReview);
                InputStream modelIn = new FileInputStream(modelFile);
                

                DoccatModel model1 = new DoccatModel(modelIn);
                DocumentCategorizerME categorizer = new DocumentCategorizerME(model1);

                double[] outcomes = categorizer.categorize(inputText);

                for (int i = 0; i < categorizer.getNumberOfCategories(); i++) {
                    category = categorizer.getCategory(i);
                    //System.out.println(category + " - " + outcomes[i]);
                }

                category1 = categorizer.getBestCategory(outcomes);
                //System.out.println(categorizer.getBestCategory(outcomes));
                //System.out.println(categorizer.getAllResults(outcomes));
                category2 = categorizer.getAllResults(outcomes);
            //}
           
        } catch (Exception e) {

            e.printStackTrace();
        }

        return new String[]{category2, category1};
    }
         
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sessionId = request.getParameter("sessionId");
                
        sessionMap.remove(sessionId);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/user/userLogin.jsp");
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

            String user_action = request.getParameter("user_action");

            switch (user_action) {
   
                case "go_to_upload_document":
                    goToUploadDocument(request, response);
                    break;
                                                    
                case "go_to_mobilePhone_search_page":
                    goToMobilePhoneSearchPage(request, response);
                    break;
               
                case "get_recomendation":
                    getRecomendation(request, response);
                    break;
                case "listAvailableProducts":
                    listAvailableProducts(request, response);
                    break;
                case "getReviews":
                    getReviews(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;

            }

        } catch (ServletException | IOException error) {

            error.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
