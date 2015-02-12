/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

/**
 *
 * @author Faye Dizon
 */
// Unnecessary class -- String of logs already at Logbook class
public class Log {
    int logID;
    String logMsg;
    public Log(int logID, String logMsg){
        this.logID = logID;
        this.logMsg = logMsg;
    }
    public void printLog(){
        //print logMsg
    }
}
