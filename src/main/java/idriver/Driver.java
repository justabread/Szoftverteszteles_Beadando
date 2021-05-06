/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDriver;

import ILoader.Subscriber;
import controller.ErrorHandler;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author pappj
 */
public class Driver implements IDriver{

    public static String handleBoiler(String boilerName, boolean command)
    {
        if(boilerName != null)
        {
            switch(boilerName)
            {
                case "Boiler 1200W":
                    if(command)
                    {
                        return "bX3434";
                    }else
                    {
                        return "bX1232";
                    }                                

                case "Boiler p5600":
                    if(command)
                    {
                        return "cX7898";
                    }else
                    {
                        return "cX3452";
                    }

                case "Boiler tw560":
                    if(command)
                    {
                        return "dX3422";
                    }else
                    {
                        return "dX111";
                    }

                case "Boiler 1400L":
                    if(command)
                    {
                        return "kx8417";
                    }else
                    {
                        return "kx4823";
                    }
            }
        }
                
        return " ";
    }           
    
    public static String handleAir(String airName, boolean command)
    {
        if(airName != null)
        {
            switch(airName)
            {
                case "Air p5600":
                    if(command)
                    {
                        return "bX5676";
                    }else
                    {
                        return "bX3421";
                    }                                

                case "Air c3200":
                    if(command)
                    {
                        return "cX3452";
                    }else
                    {
                        return "cX5423";
                    }

                case "Air rk110":
                    if(command)
                    {
                        return "eX1111";
                    }else
                    {
                        return "eX2222";
                    }
            }
        }        
        
        return " ";
    }                        
    
    @Override
    public int sendCommand(Subscriber subs, boolean boiler, boolean ac) {                       
        
        
        try {
            String urlParameters = "{\"homeId\":\"" + subs.homeid + "\",\"boilerCommand\":\"" + handleBoiler(subs.boilerType, boiler) + "\",\"airConditionerCommand\":\"" + handleAir(subs.airConditionerType, ac) + "\"}";           
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            URL url = new URL("http://193.6.19.58:8182/smarthome/" + subs.homeid);
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
            
            return Integer.valueOf(response);
        } catch (Exception e) {
            ErrorHandler.err("HTTP Request error while sending parameters to server.");
        }
        
        return -1;
    }
    
}
