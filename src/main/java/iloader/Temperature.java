/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ILoader;

/**
 *
 * @author pappj
 */
public class Temperature {
    public String period;
    public double temperature;
    
    public Temperature(String _period, Double _temperature)
    {
        period = _period;
        temperature = _temperature;
    }
}