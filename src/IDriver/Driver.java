/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDriver;

import ILoader.Subscriber;

/**
 *
 * @author pappj
 */
public class Driver implements IDriver{

    @Override
    public int sendCommand(Subscriber subs, boolean boiler, boolean ac) {
        throw new UnsupportedOperationException("TODO send commands by using CommandService to devices");
    }
    
}
