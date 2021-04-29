/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMonitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import org.json.*;

/**
 *
 * @author pappj
 */
public class Monitor implements IMonitor {

    @Override
    public Session getSession(String homeId) {
        
        URL url = null;
        Session endSession = null;
        
        try {
            //setting up httprequest
            url = new URL("http://193.6.19.58:8182/smarthome/" + homeId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            //connection method and timeouts set
            con.setRequestMethod("GET");            
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            
            int status = con.getResponseCode();
            
            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            
            //requesting response from httprequest and parsing it as a jsonobject
            if((inputLine = in.readLine()) != null)
            {
                try {
                    JSONObject responseObject = new JSONObject(inputLine);                                     
                    endSession= new Session(responseObject.getString("sessionId"),
                                            responseObject.getDouble("temperature"),
                                            responseObject.getBoolean("boilerState"),
                                            responseObject.getBoolean("airConditionerState"));
                }catch (JSONException e){
                    System.err.println("Error: home ID is invalid.");
                }
            }else
            {
                System.err.println("Error: returned value is NULL.");
            }

            in.close();
            con.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }              
                
        return endSession;        
    }
    
}
