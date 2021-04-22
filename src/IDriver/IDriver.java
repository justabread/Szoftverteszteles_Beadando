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
public interface IDriver {
    public int sendCommand(Subscriber subs, boolean boiler, boolean ac);
}
