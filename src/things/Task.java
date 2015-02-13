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
    String description;
    Date deadline;
    int taskId;
    int type;
    int headid;
    boolean isDeleted = false;
    
    Task(){
        
    }

    /**
     * 
     * @param id //-1 when the task is not yet saved in the database
     * @param name
     * @param description
     * @param date
     * @param type
     * @param headid 
     */
    Task(int id, String name, String description, Date date, int type, int headid){
        this.taskName = name;
        this.listOfTags = new ArrayList();
        this.description = description;
        this.deadline = date;
        this.type = type;
        this.headid = headid;
        
        if(id <0){ //negative id for saving tasks
            try{
                con =DriverManager.getConnection( Things.getDbHost(), Things.getDbUsername(), Things.getDbPassword() );
                statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                //Setting taskId for Task
                SQL = "SELECT COUNT(*) FROM TASKS";
                resultSet = statement.executeQuery( SQL );
                if( resultSet.isBeforeFirst()){
                    resultSet.next();
                    this.taskId = resultSet.getInt(1) + 1;
                }else{
                    this.taskId = 1;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{ //non negative id for loading tasks
            this.taskId = id;
        }
    }
    
    /**
     * 
     * @param name
     * @param description
     * @param date
     * @param type
     * @param headid
     * @return 
     */
    public boolean saveTask(){
        try{
            SQL = "INSERT INTO TASKS (TASKID, TASKNAME, DESCRIPTION, DEADLINE, TASKTYPE, HEADID) VALUES (" + taskId
                    + ", '" +taskName+ "', '" +description+ "', '" +deadline+ "', " +type+ "," +headid+ ")";
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
    
    /**
     * 
     * @param type - if project or aor
     * @param headid - project id or aor id
     * @return 
     */
    public static ArrayList getTasks(int type, int headid){
        ArrayList listOfTasks = new ArrayList <Task>();
        
        try{
            Connection connect = DriverManager.getConnection( Things.getDbHost(), Things.getDbUsername(), Things.getDbPassword() );
            String query = "SELECT * FROM TASKS WHERE TASKTYPE="+type+" AND HEADID="+headid;
            Statement stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = stmt.executeQuery( query );
            
            set.beforeFirst();
            while(set.next()){
                boolean deleted = set.getBoolean("ISDELETED"); //if deleted, do not display
                if(!deleted){
                    int id = set.getInt("TASKID");
                    String name = set.getString("TASKNAME");
                    String desc = set.getString("DESCRIPTION");
                    Date deadline = set.getDate("DEADLINE");
                    int ttype = set.getInt("TASKTYPE");
                    int hid = set.getInt("HEADID");
                    
                    Task task = new Task(id, name, desc, deadline, ttype, hid);
                    listOfTasks.add(task);
                }
            }
        }catch(SQLException esql){
            esql.printStackTrace();
        }
        
        return listOfTasks;
    }

    public String getTaskName() {
        return taskName;
    }
    
    public ArrayList getListOfTags() {
        return listOfTags;
    }

    public String getDescription() {
        return description;
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