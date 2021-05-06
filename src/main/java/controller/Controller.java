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
import java.util.Timer;
import java.util.TimerTask;
import org.joda.time.DateTime;

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
        Monitor monitor = new Monitor();
        Driver driver = new Driver();
        Subscribers subs = loader.loadSubscribers();
        DateTime dt = new DateTime();
        int currHour = dt.getHourOfDay();       
        
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {               
                for(int i = 0; i < subs.subscribers.size(); i++)
                {
                    System.out.print(subs.subscribers.get(i).homeid + ": ");
                    for(int j = 0; j < subs.subscribers.get(i).tempreatures.size(); j++)
                    {
                        String[] hours = subs.subscribers.get(i).tempreatures.get(j).period.split("-");
                        double goalTemp = subs.subscribers.get(i).tempreatures.get(j).temperature;
                        double currentTemp = monitor.getSession(subs.subscribers.get(i).homeid).tempreature;
                        int startHour = Integer.parseInt(hours[0]);
                        int endHour = Integer.parseInt(hours[1]);                
                        if(currHour >= startHour && currHour < endHour)
                        {
                            if(currentTemp > goalTemp)
                            {
                                
                                System.out.print(currHour + " :heat down \n");
                                System.out.println(goalTemp + ", " + currentTemp);
                                System.out.println(driver.sendCommand(subs.subscribers.get(i), false, true));
                            }else if(currentTemp < goalTemp)
                            {                                                             
                                System.out.print(currHour + " :heat up \n");
                                System.out.println(goalTemp + ", " + currentTemp);
                                System.out.println(driver.sendCommand(subs.subscribers.get(i), true, false)); 
                            }else
                            {
                                System.out.print(currHour + " :normal \n");
                                System.out.println(goalTemp + ", " + currentTemp);
                            }
                        }
                    }
                }
                System.out.println("End");
                System.out.println();
            }
        };

        timer.schedule (hourlyTask, 0l, 300000);
    }
    
}
