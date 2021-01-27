package com.sumit.learning.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    static FileInputStream fis;
    static Properties prop;

     static{
         try {
             fis=new FileInputStream(System.getProperty("user.dir")+ File.separator+"src"+
                     File.separator+"main"+File.separator+"resources"+File.separator+"Properties"
                     +File.separator+"config.properties");
             prop=new Properties();
             prop.load(fis);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }
    public static Properties getProperties()
    {
        return prop;
    }
}
