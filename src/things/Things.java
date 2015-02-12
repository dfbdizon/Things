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
//import java.sql.SQLException; //for connecting to DB
import java.sql.Statement; //for creating SQL statements
import java.sql.ResultSet; //for getting table from queries

public class Things{

    User currentUser;

    
    public Things() { 
         initLogic();
    }
    /**
     * @param args the command line arguments
     */    
    private static final String dbHost = "jdbc:derby://localhost:1527/ThingsDB";
    private static final String dbUsername = "fluxdev";
    private static final String dbPassword = "1234";
        

    /**
     * @param args the command line arguments
     */
       
    public static void main(String[] args) {      
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    
    public void initLogic(){
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
                //display projects in gui
            }
            */
            // creating a new project
            /*
              if(clikedCreateProject){
                //get projectName & deadline input
                try{
                    con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
                    SQL = "INSERT INTO PROJECTS(PROJECTNAME, DEADLINE) VALUES ("+ projectName + ", " + deadline + ")";
                    statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
                    //Querying from DB
                    ResultSet resultSet = statement.executeUpdate( SQL );
                    System.out.println("Project created in database");
                }catch(Exception e){
                    e.printStackTrace();
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
    
}
