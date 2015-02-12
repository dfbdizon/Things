/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;
import java.util.Date;

/**
 *
 * This shall be implemented as a String.
 * Searching and organizing tags should be handled for displays and printing.
 */
public class Tag {
    int tagID;
    String tagName;
    boolean isDeleted;
    
    public Tag(int tagID, String tagName, boolean isDeleted){
        this.tagID = tagID;
        this.tagName = tagName;
        this.isDeleted = isDeleted;
        createLog(" is created.");
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
        Date date = new Date();
        String msg = this.tagName + text + " " + date.toString();
        Log log = new Log(7, msg);
    }
    /**
     * Tag Table:
     *  
     * 
     * Task_Tag:
     */
}
