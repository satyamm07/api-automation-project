package com.core.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Satyam
 */
public class BaseClass {

    public static Properties prop;

    public Properties loadProperties() throws Exception{

        prop = new Properties();
        FileInputStream fileInputStream =
                new FileInputStream(System.getProperty("user.dir") + "/configurator.properties");
        prop.load(fileInputStream);

        return prop;
    }

}
