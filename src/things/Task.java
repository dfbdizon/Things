/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * The Task class contains all the details about the task that the User needs to do.
 * It keeps all of the tags and is filed under Projects and Areas of Responsibilities.
 * Goes to TrashBin when removed by User.
 * Log is created when a new task is created.
 */
public class Task {
    Connection con;
    String SQL;
    Statement statement;
    ResultSet resultSet;
    
    String taskName;
    ArrayList listOfTags;
    String note;
    Date deadline;
    int taskId=0;
    int type;
    int headid;
    boolean isDeleted = false;

    public Task(String name, String note, Date date, int type, int headid){
        taskName = name;
        listOfTags = new ArrayList();
        note = note;
        deadline = date;
        
        try{
            con =DriverManager.getConnection( Things.getDbHost(), Things.getDbUsername(), Things.getDbPassword() );
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            //Setting taskId for Task
            SQL = "SELECT COUNT(*) FROM TASKS";
            resultSet = statement.executeQuery( SQL );
            if( resultSet.isBeforeFirst()){
                resultSet.next();
                taskId = resultSet.getInt(1) + 1;
            }else{
                taskId = 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param name
     * @param note
     * @param date
     * @param type
     * @param headid
     * @return 
     */
    public boolean saveTask(){
        try{
            SQL = "INSERT INTO TASKS (TASKID, TASKNAME, DESCRIPTION, DEADLINE, TASKTYPE, HEADID) VALUES (" + taskId
                    + ", '" +taskName+ "', '" +note+ "', '" +deadline+ "', " +type+ "," +headid+ ")";
            System.out.println(statement.executeUpdate( SQL ));
            
            System.out.println("A task is created.");
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 
     * @return 
     */
    public boolean deleteTask(){
        isDeleted = true;
        
        SQL = "UPDATE TASKS SET ISDELETED = true WHERE TASKID = " + taskId;
        try{
            statement.executeUpdate(SQL);
            System.out.println("IsDeleted updated!");
            return true;
        }catch(SQLException esql){
            esql.printStackTrace();
        }
        return false;
    }

    public String getTaskName() {
        return taskName;
    }
    
    public ArrayList getListOfTags() {
        return listOfTags;
    }

    public String getDescription() {
        return note;
    }

    public Date getDeadline() {
        return deadline;
    }
    
    public void addTag(Tag tag){
        listOfTags.add(tag);
    }
    
    public void deleteTask(String taskName){
      isDeleted = true;
    }
    
    public void createLog(){
    }
}