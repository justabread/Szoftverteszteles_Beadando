/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ILoader.Loader;

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
        
        loader.loadSubscribers();
    }
    
}