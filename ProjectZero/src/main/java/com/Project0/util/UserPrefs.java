package com.Project0.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/* THIS CLASS IS TO STORE THE CONFIGURATION OF DATABASE INTO A FILE SO IT CAN
BE MANIPULATED FOR DIFFERENT CONNECTION MEANS WITHOUT HAVING TO RECODE ANYTHING
IN THE PROJECT AND RECOMPILE.  FILE = CONFIG.CFG IN ROOT SOURCE OF PROJECT
 */
public class UserPrefs {

    Properties configFile;

    public UserPrefs() {
        configFile = new Properties();
        try {
            FileInputStream inStream = new FileInputStream("config.cfg");
            configFile.load(inStream);
            System.out.println("CONFIG FILE LOADED!");
        } catch (IOException e) {
            System.out.println("ERROR LOADING CONFIG FILE");
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }

    public static void main(String[] args) {
        Preferences userPrefs = Preferences.userNodeForPackage(UserPrefs.class);

        try {
            String[] keys = userPrefs.keys();

            if (keys == null || keys.length == 0) {
                userPrefs.put("hostname", "www.codejava.net");
                userPrefs.putInt("port", 12345);
                userPrefs.putBoolean("authentication", true);
                userPrefs.putLong("timeout", 90000);
            } else {
                String hostname = userPrefs.get("hostname", null);
                int port = userPrefs.getInt("port", 80);
                boolean authentication = userPrefs.getBoolean("authentication", false);
                long timeout = userPrefs.getLong("timeout", 20000);

                String username = userPrefs.get("username", "tom");

                System.out.println(hostname);
                System.out.println(port);
                System.out.println(authentication);
                System.out.println(timeout);
                System.out.println(username);
            }
        } catch (BackingStoreException ex) {
            System.err.println(ex);
        }    }
}
