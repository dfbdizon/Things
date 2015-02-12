/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;
import java.util.Date;

/**
 *
 * @author Faye Dizon
 */
// Unnecessary class -- String of logs already at Logbook class
public class Log {
    int logID;
    String logMsg;
    public Log(String logMsg){
        Date date = new Date();
        this.logMsg = logMsg + " " + date.toString();
    }
    public void printLog(){
        //print logMsg
    }
}
