/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ILoader;

import java.util.List;

/**
 *
 * @author pappj
 */
public class Subscriber {
    public String subscriber;
    public String homeid;
    public String boilerType;
    public String airConditionerType;
    public List<Temperature> tempreatures;   

    public Subscriber(String _subscriber, String _homeid, String _boilerType, String _airConditionerType, List<Temperature> _tempreatures) {
        subscriber = _subscriber;
        homeid = _homeid;
        boilerType = _boilerType;
        airConditionerType = _airConditionerType;
        tempreatures = _tempreatures;
    }
}
