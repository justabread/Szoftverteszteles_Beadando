/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDriver;

import ILoader.Subscriber;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

/**
 *
 * @author pappj
 */
public class Driver implements IDriver{

    @Override
    public int sendCommand(Subscriber subs, boolean boiler, boolean ac) {
        
        try {                    
            //String jsonInputString = "{" + "\"homeId\"" + ":" + "\"" + subs.homeid + "\"" + "," + "\"boilerCommand\"" + ":" + "\"bX1232\"" + "," + "\"airConditionerCommand\"" + ":" + "\"cX3452\"" + "}";
            String urlParameters  = "homeId=" + subs.homeid + "&boilerCommand=bX1232&airConditionerCommand=cX3452";
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = "http://193.6.19.58:8182/smarthome/" + subs.homeid;
            URL    url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
               wr.write( postData );
            }
            
            try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 3;
    }
    
}
