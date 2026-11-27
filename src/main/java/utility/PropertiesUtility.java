
package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    private final String propertiesFile;
    private Properties properties;

    public PropertiesUtility(String propertiesFileName) {
        this.propertiesFile = propertiesFileName;
    }

    public void inititatFileReader() throws FileNotFoundException {
        properties = new Properties();
        try (FileReader reader = new FileReader(propertiesFile)) {
            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file: " + propertiesFile,e);
        }
    }

    public String getValueFromPropertiesFile(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties file is not initialized");
        }
        return properties.getProperty(key);
    }

    public void setValueToPropertiesFile(String key, String value) {
        if (properties == null) {
            throw new IllegalStateException("Properties file is not initialized");
        }
        properties.setProperty(key, value);
    }
}
