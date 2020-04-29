package com.ex.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is responsible for taking in the config variables setup in config.cfg
 * and returning them to useable settings anywhere within code through getters via this
 * class.
 *
 * @param configFile - the array of properties read from Config.cfg
 */
public class ConfigVarsService {
    private Properties configFile;

    public ConfigVarsService() {
        configFile = new Properties();
        try {
            FileInputStream inStream = new FileInputStream("config.cfg");
            configFile.load(inStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR LOADING CONFIG FILE");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR LOADING CONFIG FILE");
        }
    }

    /**
     * A getter function to get any key from this service on demand.  Requires a valid
     * ConfigVarsService object to invoke this.
     * @param key - String literal of the keyname found in Config.cfg
     * @return - the value of the key read from file.
     */
    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }
}
