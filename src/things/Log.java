/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.SQLException;
/**
 *
 * @author Faye Dizon
 */
// Unnecessary class -- String of logs already at Logbook class
public class Log {
    Connection con;
    String SQL;
    Statement statement;
    ResultSet resultSet;
    
    int logID;
    String logMsg;
    
    public Log(String logMsg){
        Date date = new Date();
        this.logMsg = logMsg + " " + date.toString();
        
        try{
            con =DriverManager.getConnection( Things.getDbHost(), Things.getDbUsername(), Things.getDbPassword() );
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Setting taskId for Task
        /*    SQL = "SELECT COUNT(*) FROM TAGS";
            resultSet = statement.executeQuery( SQL );
            if( resultSet.isBeforeFirst()){
                resultSet.next();
                tagId = resultSet.getInt(1) + 1;
            }else{
                tagId = 1;
            }*/
        }catch(Exception e){
            e.printStackTrace();
        }
        /*
        //Saving task to database
     //   saveLog(this.logMsg);
        */
    }
         
   /* public boolean saveLog(String msg){
        try{
            SQL = "INSERT INTO LOG (TAGID, TAGNAME, ISDELETED) VALUES (" + tagID
                    + ", '" +tagName+ "', '" +isDeleted+ ")";
            System.out.println(statement.executeUpdate( SQL ));
            
            System.out.println("A tag is created.");
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
     */
    
    public void printLog(){
        System.out.println(this.logMsg);
    }
}
