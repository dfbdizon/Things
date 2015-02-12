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

public class Things {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Setting up host, username, and password
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
            
            while(resultSet.next()){
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
