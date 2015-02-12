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
    boolean isDeleted = false;
    
    public Project(int projectID, String projectName, Date deadline){
        this.projectID = projectID;
        this.projectName = projectName;
        this.deadline.setTime(deadline);
    }
    
    public String getProjectName(){
        return(projectName);
    }
    public void setProjectName(String name){
        projectName = name;
    }
    public ArrayList getTaskList(){
        return(taskList);
    }
    public void createTask(){
        //insert code here
        //add params
    }
    public Calendar getDeadline(){
        return(deadline);
    }
    public void createLog(Logbook lb, String msg){
        lb.createLog(msg);
    }
    public void deleteProject(){
        isDeleted = true;
        createLog();
    }
    public void createLog(){
        Date date = new Date();
        //insert code here
    }
    /*
    * deleteTask()
    */
}
