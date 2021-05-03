/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDriver;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author pappj
 */
public class Driver implements IDriver{

    @Override
    public int sendCommand(String homeId, boolean boiler, boolean ac) {                       
        try {
            String urlParameters = "{\"homeId\":\"" + homeId + "\",\"boilerCommand\":\"bX3434\",\"airConditionerCommand\":\"bX5676\"}";           
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            URL url = new URL("http://193.6.19.58:8182/smarthome/" + homeId);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postData);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
            String response = sb.toString();
            System.out.println(response);
        } catch (Exception e) {
        }
       
        return 3;
    }
    
}
