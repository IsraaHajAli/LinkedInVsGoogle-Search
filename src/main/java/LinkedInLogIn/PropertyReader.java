package LinkedInLogIn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private Properties properties = new Properties();

    public PropertyReader() {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    ///////////////////////////////////////////////

    public String getValidUsername() {
        return getProperty("validusername");
    }

    public String getValidPassword() {
        return getProperty("validpassword");
    }
    
    public String getInValidUsername() {
        return getProperty("invalidusername");
    }

    public String getInValidPassword() {
        return getProperty("invalidpassword");
    }
    
    
    public String getLoginURL() {
        return getProperty("loginURL");
    }
    
    public String getDriverPath() {
    	return getProperty("chromepath");
    }
    
    public String getPageTitle() {
    	return getProperty("logintitle");
    }

    public String getGoogleURL() {
        return getProperty("googleURL");
    }
}
