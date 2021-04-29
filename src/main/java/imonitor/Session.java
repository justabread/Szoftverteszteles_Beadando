/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMonitor;

/**
 *
 * @author pappj
 */
public class Session {
    public String sessionId;
    public double tempreature;
    public boolean boilerState;
    public boolean airConditionerState;
    
    public Session(String _sessionId, double _tempreature, boolean _boilerState, boolean _airConditionerState)
    {
        this.sessionId = _sessionId;
        this.tempreature = _tempreature;
        this.boilerState = _boilerState;
        this.airConditionerState = _airConditionerState;
    }
}
