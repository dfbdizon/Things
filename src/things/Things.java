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
import java.util.ArrayList; 

public class Things{
    
    private static User currentUser;
    private static ArrayList projectObjects = new ArrayList<Project>();
    private static ArrayList aorObjects = new ArrayList<AreaOfResponsibility>();

    
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
        Task task= new Task(-1, "Finish Things", "ASAP", new Date(2015, 2, 17), 1, 1234);
        System.out.println(task.saveTask());
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
            /*ArrayList aorObjects = new ArrayList<AreaOfResponsibility>();
              AreaOfResponsibility aor;
              String SQL;
              Statement statement;
              int aorID;
              if(aorsIsClicked){
                SQL = "SELECT * FROM AORS";
                statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                
                //Querying from DB
                resultSet = statement.executeQuery( SQL );
                while(resultSet.next()){
                    //Getting contents of the database
                    int aorID = resultSet.getInt("AORID");
                    String aorName = resultSet.getString("AORNAME");
                    
                    aor = new AreaOfResponsibility(aorID, aorName);
                    System.out.println("AOR ID: " + aorID);
                    System.out.println("AOR Name: " + aorName);
                    aorObjects.add(aor);
                }
                System.out.println("AOR objects created from db");
                //display aors in gui
            }
            */
            // creating a new aor
            /*
              if(clikedCreateAOR){
                //get aorName
                //get username
                try{
                    con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
            
                    SQL = "SELECT COUNT (*) FROM AORS WHERE USERNAME ='"+username+"'";
                    statement = con.createStatement();
                    aorID = statement.executeQuery( SQL ).getInt(1) + 1;
            
                    SQL = "INSERT INTO AORS(AORID, AORNAME, USERNAME) VALUES ("+ aorID + ", "+ aorName + ", " + username + ")";
                    statement = con.createStatement();
                    statement.executeUpdate( SQL );
                    System.out.println("AOR created in database");
                    
                    aor = new AreaOfResponsibility(aorID, aorName);
                    System.out.println("AOR object created");
                    aorObjects.add(aor);
                }catch(Exception e){
                    e.printStackTrace();
                }
                
              }
            */
            // deleting an aor
            /*
              if(clickedDeleteAOR){
                //get aor
                aor.deleteAOR();
                aorObjects.remove(aor);
              }
            */
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void createProjectList(){
        Project project;
        String SQL;
        Statement statement;
        int projectID;
        try{
            Connection con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
            SQL = "SELECT * FROM PROJECTS";
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                
            //Querying from DB
            ResultSet resultSet = statement.executeQuery( SQL );
            while(resultSet.next()){
                //Getting contents of the database
                projectID = resultSet.getInt("PROJECTID");
                String projectName = resultSet.getString("PROJECTNAME");
                Date deadline = resultSet.getDate("DEADLINE");
                    
                project = new Project(projectID, projectName, deadline);
                projectObjects.add(project);
                System.out.println("Project ID: " + projectID);
                System.out.println("Project Name: " + projectName);
                System.out.println("Deadline: " + deadline+ "\n");
            }
            System.out.println("Project objects created from db");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void createProject(String projectName, Date deadline){
        Project project;
        String SQL;
        Statement statement;
        int projectID;
        try{
            Connection con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
            con = DriverManager.getConnection( dbHost, dbUsername, dbPassword );
            
            SQL = "SELECT COUNT (*) FROM PROJECTS WHERE USERNAME ='"+currentUser.getUsername()+"'";
            statement = con.createStatement();
            projectID = statement.executeQuery( SQL ).getInt(1) + 1;
            
            SQL = "INSERT INTO PROJECTS(PROJECTID, PROJECTNAME, DEADLINE, USERNAME) VALUES ("+ projectID + ", "+projectName + ", " + deadline + ", " + currentUser.getUsername() + ")";
            statement = con.createStatement();
            statement.executeUpdate( SQL );
            System.out.println("Project created in database");
                    
            project = new Project(projectID, projectName, deadline);
            System.out.println("Project object created");
            projectObjects.add(project);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteProject(Project project){
        project.deleteProject(0);
        projectObjects.remove(project);
    }
    public static User getUser(){
        return currentUser;
    }
    public static String getDbHost() {
        return dbHost;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }
}
