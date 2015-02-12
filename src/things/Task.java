/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

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
    boolean isDeleted = false;

    public Task(String name, Tag tag, String note, Calendar date){
        taskName = name;
        listOfTags = new ArrayList<Tag>();
        listOfTags.add(tag);
        notes = note;
        deadline = date;
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