/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;
/**
 *
 * @author FluxDev 2015
 */

import java.sql.Connection; //for connecting to DB
import java.sql.Date;
import java.sql.DriverManager; //for connecting to DB
import java.sql.SQLException; //for connecting to DB
import java.sql.Statement; //for creating SQL statements
import java.sql.ResultSet; //for getting table from queries
import javax.swing.JFrame;

public class Things extends JFrame{

    User currentUser;
    
    public Things() {
        super("Things");
        
        initComponents();
        initLogic();
        
        login("imyersupergirl", "ilovesuperman");
    }
    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Things().setVisible(true);
            }
        });
    }
    
    public void initComponents(){
        setSize(775,700);
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void initLogic(){
     //Setting up host, username, and password
        System.out.println("Hey");
        String dbHost = "jdbc:derby://localhost:1527/ThingsDB";
        String dbUsername = "fluxdev";
        String dbPassword = "1234";
        
        //connecting to the database, start database server for this to work
        try{
            //Connecting to the database
            Connection con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
            
            /**
             * ResultSet.TYPE_FORWARD_ONLY (Traversing the records forward)
             * ResultSet.TYPE_SCROLL_SENSITIVE (Traversing records while noting changes)
             * ResultSet.TYPE_SCROLL_INSENSITIVE (Traversing records)
             * 
             * ResultSet.CONCUR_READ_ONLY
             * ResultSet.CONCUR_UPDATABLE
             */
            
            String SQL = "SELECT * FROM TASKS";
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            //Querying from DB
            ResultSet resultSet = statement.executeQuery( SQL );
            
            while(resultSet.next()){ //iterated through the rows of the database
                //Getting contents of the database
                int taskID = resultSet.getInt("TASKID");
                String taskName = resultSet.getString("TASKNAME");
                String description = resultSet.getString("DESCRIPTION");
                Date date = resultSet.getDate("DEADLINE");
                
                System.out.println("Task ID: " + taskID);
                System.out.println("Task Name: " + taskName);
                System.out.println("Notes: " + description);
                System.out.println("Deadline: " + date + "\n");
            }
            /*if(projectsIsClicked){
                String SQL = "SELECT * FROM PROJECTS";
                Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                
                //Querying from DB
                resultSet = statement.executeQuery( SQL );
                Project project; 
                while(resultSet.next()){
                    //Getting contents of the database
                    int projectID = resultSet.getInt("PROJECTID");
                    String projectName = resultSet.getString("PROJECTNAME");
                    Date deadline = resultSet.getDate("DEADLINE");
                    
                    project = new Project(projectID, projectName, deadline);
                    System.out.println("Project ID: " + projectID);
                    System.out.println("Project Name: " + projectName);
                    System.out.println("Deadline: " + deadline+ "\n");
                }
            }
            */
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * This method implements the user login and verification.
     * @param username
     * @param password
     * @return 
     */
    public static User login(String username, String password){
        
        String dbHost = "jdbc:derby://localhost:1527/ThingsDB";
        String dbUsername = "fluxdev";
        String dbPassword = "1234";
        
        try{
            Connection con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
            String SQL = "SELECT USERNAME,PASSWORD,NAME FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'";
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            //Querying from DB
            ResultSet resultSet = statement.executeQuery( SQL );

            if( resultSet.isBeforeFirst() ){
                resultSet.next();
                User userLogin = new User(resultSet.getString("USERNAME"), resultSet.getString("PASSWORD"), resultSet.getString("NAME"));
                System.out.println("Logged in as "+userLogin.getName()+"!");
                return userLogin;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}