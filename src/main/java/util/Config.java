package util;

import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String getStringByKey(String key) {
        Properties prop = new Properties();
        try {
            prop.load(ClassLoader.getSystemResourceAsStream("msq.properties"));
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
