/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ILoader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author pappj
 */
public class Loader implements ILoader{

    @Override
    public Subscribers loadSubscribers(){
        //2 temporary lists for instantiation
        List<Subscriber> subs = new ArrayList<>();
        List<Temperature> temps = new ArrayList<>();
        
        //loading the json in
        String jsonFile = "iloader.json";
        InputStream is = null;
        try {           
            is = new FileInputStream(jsonFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //instantiation of objects required by the json to parse
        BufferedReader in = new BufferedReader(new InputStreamReader(is));        
        JSONTokener tokener = new JSONTokener(in);
        JSONObject root = new JSONObject(tokener);        
        JSONArray subscribers = root.optJSONArray("subscribers");        
        
        if(subscribers != null)
        {
            //goes through all the subscribers
            for(int i = 0; i < subscribers.length(); i++)
            {                               
                //gets what subscriber are we at
                JSONObject subAtIndex =  subscribers.optJSONObject(i);
                if(subAtIndex != null)
                {
                    //gets the tempreatures to the current subscriber
                    JSONArray temperatures = subAtIndex.optJSONArray("temperatures");
                    for(int j = 0; j < temperatures.length(); j++)
                    {
                        Temperature temp = new Temperature(temperatures.getJSONObject(j).getString("period"), temperatures.getJSONObject(j).getDouble("temperature"));
                        temps.add(temp);
                    }
                }else
                {
                    System.err.println("Error: tempreature is null");
                }
                Subscriber sub = new Subscriber(subscribers.getJSONObject(i).getString("subscriber"),
                                                subscribers.getJSONObject(i).getString("homeId"),
                                                subscribers.getJSONObject(i).getString("boilerType"), 
                                                subscribers.getJSONObject(i).getString("airConditionerType"), 
                                                temps);
                subs.add(sub);
            }
        }else
        {
            System.err.println("Error: tempreature is null");
        }                     
               
        Subscribers endSubs = new Subscribers(subs);
        return endSubs;
    }   
    
}
