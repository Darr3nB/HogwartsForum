package com.HogwartsForum.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
    public static int oneDayForCookies = 60 * 60 * 24;
    public static int commentReputationValue = 5;

    public static String getValueByKeyFromConfigProperties(String fieldName) {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            return prop.getProperty(fieldName);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
