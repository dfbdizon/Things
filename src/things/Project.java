/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Faye Dizon
 */
/*
* The Project class contains an array of tasks. 
* These tasks fall under this specific project, and have deadlines.
* This class has a name, an array of tasks, and a deadline.
*/
public class Project {
    int projectID;
    String projectName;
    ArrayList taskList;
    Calendar deadline;
    
    public Project(int projectID, String projectName, Date deadline){
        this.projectID = projectID;
        this.projectName = projectName;
        this.deadline.setTime(deadline);
    }
    
    String getProjectName(){
        return(projectName);
    }
    void setProjectName(String name){
        projectName = name;
    }
    ArrayList getTaskList(){
        return(taskList);
    }
    void createTask(){
        //insert code here
        //add params
    }
    Calendar getDeadline(){
        return(deadline);
    }
    void createLog(Logbook lb, String msg){
        lb.createLog(msg);
    }
    /*
    * deleteTask()
    */
}
