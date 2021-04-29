/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import IDriver.Driver;
import ILoader.Loader;
import ILoader.Subscribers;
import IMonitor.Monitor;
import IMonitor.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pappj
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Loader loader = new Loader();
        Subscribers subs = loader.loadSubscribers();
        
        String homeID = "KD34AF24DS";
        
        Monitor monitor = new Monitor();
        Session session = monitor.getSession(homeID);
        
        //System.out.println(a.sessionId);
        Driver driver = new Driver();
        driver.sendCommand(subs.subscribers.get(0), session.boilerState, session.airConditionerState);
    }
    
}
