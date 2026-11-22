package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadWrite {
    String filePath;

    public ReadWrite(String filePathName){
        this.filePath = filePathName;
    }

    public String getFilePath(String key)
    {
        FileReader reader = null;
        String value;

        try {
            reader= new FileReader(filePath);

            Properties file =new Properties();
            file.load(reader);
            value = file.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
