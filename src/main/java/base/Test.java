package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")+"/src/main/resources/configuration.properties");
        String propertiesFilePath = "./src/main/resources/configuration.properties";

        FileReader reader = null;
        String value;
        try {
            reader = new FileReader(propertiesFilePath);

            Properties p = new Properties();
            p.load(reader);
            value = p.getProperty("url");
            System.out.println("The value is :"+ value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
