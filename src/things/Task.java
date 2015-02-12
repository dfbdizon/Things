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

/**
 *
 * The Task class contains all the details about the task that the User needs to do.
 * It keeps all of the tags and is filed under Projects and Areas of Responsibilities.
 * Goes to TrashBin when removed by User.
 * Log is created when a new task is created.
 */
public class Task {
    String taskName;
    ArrayList listOfTags;
    String notes;
    Calendar deadline;
    Boolean isDeleted = false;

    public Task(String name, Tag tag, String note, Calendar date, int type, int headid){
        taskName = name;
        listOfTags = new ArrayList<Tag>();
        listOfTags.add(tag);
        notes = note;
        deadline = date;
    }
    
    public boolean saveTask(String name, String note, Calendar date, int type, int headid){
        try{
            Connection con = DriverManager.getConnection( Things.getDbHost(), Things.getDbUsername(), Things.getDbPassword() );
            String SQL = "SELECT COUNT(*) FROM TASKS AS C";
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int id = 0;
            //Querying from DB
            ResultSet resultSet = statement.executeQuery( SQL );

            if( resultSet.isBeforeFirst() ){
                resultSet.next();
                id = resultSet.getInt("C") + 1;
            }
            
            if(id!=0){
                SQL = "INSERT INTO TASKS VALUES ("+id+", '"+name+"', '"+note+"', '"+date+"', "+type+", "+headid+")";
            }else{
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }
    
    public ArrayList getListOfTags() {
        return listOfTags;
    }

    public String getNotes() {
        return notes;
    }

    public Calendar getDeadline() {
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