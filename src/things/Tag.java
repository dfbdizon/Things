/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * This shall be implemented as a String.
 * Searching and organizing tags should be handled for displays and printing.
 */
public class Tag {
    Connection con;
    String SQL;
    Statement statement;
    ResultSet resultSet;
    
    int tagID;
    String tagName;
    boolean isDeleted;
    
    public Tag(String tagName){
        this.tagName = tagName;
        this.isDeleted = false;
               
        try{
            con =DriverManager.getConnection( Things.getDbHost(), Things.getDbUsername(), Things.getDbPassword() );
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            //Setting taskId for Task
            SQL = "SELECT COUNT(*) FROM TAGS";
            resultSet = statement.executeQuery( SQL );
            if( resultSet.isBeforeFirst()){
                resultSet.next();
                tagID = resultSet.getInt(1) + 1;
            }else{
                tagID = 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        createLog(" is created.");
        //Saving tag to database
        saveTag(tagID, tagName, isDeleted);
        
    }
    
    public boolean saveTag(int tagID, String tagName, Boolean isDeleted){
        try{
            SQL = "INSERT INTO TAGS (TAGID, TAGNAME, ISDELETED) VALUES (" + tagID
                    + ", '" +tagName+ "', '" +isDeleted+ ")";
            System.out.println(statement.executeUpdate( SQL ));
            
            System.out.println("A tag is created.");
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public void deleteTag(){
        this.isDeleted = true;
        createLog(" is deleted.");
    }
    
    public void createLog(String text){
        String msg = this.tagName + text;
        Log log = new Log(msg);
    }
    /**
     * Tag Table:
     *  
     * 
     * Task_Tag:
     */
}
