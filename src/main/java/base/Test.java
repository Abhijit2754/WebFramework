package base;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import javax.swing.text.Style;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
//        characterQuizz();
//        wordsQuizz();
          checkingValueAsMutableAndImmutable();

//        System.out.println("We are writting this file for priting the username and password");
//        String username;
//        String password;
//        String filePath = "./src/main/resources/configuration.properties";
//        FileReader reader;
//        try {
//            reader = new FileReader(filePath);
//            Properties p = new Properties();
//            p.load(reader);
//            username = p.getProperty("username");
//            password = p.getProperty("password");
//            System.out.println("the username is : " + username + " And the password is :" + password);
//        } catch (Exception e) {
//            System.out.println("This operation could not be possible");
//        }
    }

    public static void characterQuizz(){
        String str = "I want to do my carrier in my Automation Engineer";
        char[] ar = str.toCharArray();
        for (int i = 0 ; i < ar.length; i++){
            char a = ar[i];
            System.out.println(a);
        }
    }

    public static void wordsQuizz(){
        String str = "I want my carrier in Automation engineer";
        String[] arr = str.split(" ");
        for(int i = 0 ; i < arr.length ; i++){
            String undestr = arr[i];
            if(!(undestr.equals("want") || undestr.equals("in") || undestr.equals("engineer"))){
                char[] ch = undestr.toCharArray();
                for (int j = ch.length - 1; j >= 0; j--) {
                    System.out.print(ch[j]);
                }
                System.out.println();
            }else {
                System.out.println(undestr);
            }
        }
    }

    public static void checkingValueAsMutableAndImmutable()
    {
        String str = "Abhijit";
        str.concat("cdcd");

        System.out.println(str);

        // will print output only Abhijit
    }
}