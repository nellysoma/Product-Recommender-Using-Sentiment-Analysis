/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author harmony
 */
public class DbConnection {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost/prorec";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;//manage connection
    
    private PreparedStatement createAccount = null;
    private PreparedStatement logUserRegistration = null;
    private PreparedStatement UpdateAdministrator_last_login = null;
   
    
    private Statement selectUser = null;
    private Statement selectAdminUser = null;
    
    private ResultSet selectUserResult = null;
    private ResultSet selectAdminUserResult = null;
    
    FileInputStream profilePictureFile;
    
    
    public static void connectToDataBase() throws ClassNotFoundException {

        try {

            // Load (and therefore register) the  Driver
            Class.forName("com.mysql.jdbc.Driver");

            // Get a Connection to the database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        } catch (SQLException error) {

            System.out.print(error.getMessage());
            error.printStackTrace();
            System.out.print("What is trying to say here is that, it could not connect to the database");

        }
    }
    
    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }
    
    public void createUserAccount(String first_name,String last_name,String phone,String email,         
            String password, String profilePicture) throws ClassNotFoundException, FileNotFoundException, SQLException{
        
        try {
            
            connectToDataBase();
            
            createAccount = connection.prepareStatement("INSERT INTO user_table(first_name,last_name,"
                    + "phone,email,password,profile_picture)VALUES(?,?,?,?,?,?)");
            
            createAccount.setString(1, first_name);
            createAccount.setString(2, last_name);
            createAccount.setString(3, phone);
            createAccount.setString(4, email);
            createAccount.setString(5, password);
            
            System.out.println("in DbConnection profile picture is:" + profilePicture);
            File file1 = new File(profilePicture);
            profilePictureFile = new FileInputStream(profilePicture);
            createAccount.setBinaryStream(6, (InputStream) profilePictureFile, (int) (file1.length()));
            
            
            
            createAccount.executeUpdate();
    } catch (SQLException error) {

            error.printStackTrace();
            System.out.print(error);
        } finally {
            closeConnection(connection);
        }
}
    
    public void logUserRegistration() throws ClassNotFoundException{
        
        String actionPerformed = "New User Registration";
        String performedBy = "Guest User";
        
        try {
            
            connectToDataBase();
            
            logUserRegistration = connection.prepareStatement("INSERT INTO log(actionPerformed,performedBy)VALUES(?,?)");
            
            logUserRegistration.setString(1, actionPerformed);
            logUserRegistration.setString(2, performedBy);
            
            logUserRegistration.executeUpdate();
            
        } catch (SQLException error) {

            error.printStackTrace();
            System.out.print(error);
        } finally {
            closeConnection(connection);
        }
    }
    
    public String[] userLogin(String username, String password) throws ClassNotFoundException{
        
    String firstName  = "";
    String lastName  = "";
    String phone  = "";
    
        try {

            connectToDataBase();
            
           String sql = "SELECT * FROM user_table WHERE email = '"+username+"'";
            
           selectUser = connection.createStatement();
           
           selectUserResult = selectUser.executeQuery(sql);
           
           selectUserResult.next();
           
           password = selectUserResult.getString("password");  
           firstName = selectUserResult.getString("first_name");           
           lastName = selectUserResult.getString("last_name");
           username = selectUserResult.getString("email");
           phone = selectUserResult.getString("phone");
          
           
        } catch (SQLException error) {

            error.printStackTrace();
            System.out.print(error);
        } finally {
            closeConnection(connection);
        }
        return  new String[]{password,firstName,lastName,username,phone};
    }
    
    public String[] administratorLogin(String username, String password) throws ClassNotFoundException{
        
    String firstName  = "";
    String lastName  = "";
            
        try {

            connectToDataBase();
            
           String sql = "SELECT * FROM admin where username = '"+username+"'"; 
           
           selectAdminUser = connection.createStatement();
           selectAdminUserResult = selectAdminUser.executeQuery(sql);
           selectAdminUserResult.next();;
           password = selectAdminUserResult.getString("password");
           firstName = selectAdminUserResult.getString("first_name");
           lastName = selectAdminUserResult.getString("last_name");
           username = selectAdminUserResult.getString("username");
           
        } catch (SQLException error) {

            //error.printStackTrace();
            System.out.print(error);
        } finally {
            closeConnection(connection);
        }
        return  new String[]{password,firstName,lastName,username};
    }
 
}


