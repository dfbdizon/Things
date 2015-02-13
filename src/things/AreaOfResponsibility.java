/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Faye Dizon
 */
/*
* AOR contains an array of tasks. 
* These tasks do not belong to a project.
* This class organizes your tasks into different categories.
* This class has a name, and an array of tasks.
*/
public class AreaOfResponsibility {
    int aorID;
    String aorName;
    ArrayList taskList;
    boolean isDeleted = false;
    
    public AreaOfResponsibility(int aorID, String aorName){
        this.aorName = aorName;
        this.aorID = aorID;
        createLog(1);
    }
    public String getAORName(){
        return(aorName);
    }
    public void setAORName(String name){
        aorName = name;
    }
    public ArrayList getTaskList(){
        return(taskList);
    }
    public void createTask(){
        // insert code here
        // add params
    }
    //public void createLog(Logbook lb, String msg){
    //    lb.createLog(msg);
    //}
    public void deleteAOR(int logID){
        isDeleted = true;
        createLog(0);
    }
    public void createLog(int type){
        Log log;
        if(type == 0) log = new Log(getAORName() + "has been deleted");
        else log = new Log(getAORName() + "has been created");
    }
    /*
    * deleteTask()
    */
}
