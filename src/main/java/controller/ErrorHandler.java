/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pappj
 */
public class ErrorHandler {
    public static void err(String message)
    {
        try {
            String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date());
            File errObj = new File(out);
            if (errObj.createNewFile()) {
                System.out.println("File created: " + errObj.getName());
            }
            
            FileWriter myWriter = new FileWriter(errObj);
            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
        }
    }
}
