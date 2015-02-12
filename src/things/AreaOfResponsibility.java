/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.util.ArrayList;

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
    String aorName;
    ArrayList taskList;
    public AreaOfResponsibility(String aorName){
        this.aorName = aorName;
    }
    String getAorName(){
        return(aorName);
    }
    void setAorName(String name){
        aorName = name;
    }
    ArrayList getTaskList(){
        return(taskList);
    }
    void createTask(){
        // insert code here
        // add params
    }
    void createLog(Logbook lb, String msg){
        lb.createLog(msg);
    }
    /*
    * deleteTask()
    */
}
