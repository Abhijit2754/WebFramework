package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
    public final String propertiesFile;
    FileReader reader = null;
    Properties properties = null;

    public PropertiesUtility(String propertiesFileName)
    {
        propertiesFile= propertiesFileName;
    }

    public void inititatFileReader() throws FileNotFoundException {
        try {
           reader = new FileReader(propertiesFile);
            properties = new Properties();
            try {
                properties.load(reader);
            }catch (Exception e){
                throw new RuntimeException("unable to load the file");
            }
        }catch (Exception e){
            throw new RuntimeException("Unable to read the file");
        }
    }

    public void setValueToPropertiesFile(String key, String value) throws FileNotFoundException {
        inititatFileReader();
        try{
            properties.setProperty(key,value);
        }catch (Exception e){
            throw new RuntimeException("Unable to write the data in properties file");
        }
    }

    public String getValueFromPropertiesFile(String key) throws FileNotFoundException {
        inititatFileReader();
        String value;
        try{
            value = properties.getProperty(key);
            return value;
        } catch (Exception e) {
            throw new RuntimeException("unable to fetch the value");
        }
    }
}
