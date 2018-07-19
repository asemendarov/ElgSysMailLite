package Tool;

import FileHelper.FileHelper;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NavigableMap;
import java.util.Properties;

public class Configuration {

    public static final String NAME_PROGRAM = "ElgSysMailLite";

    private static Properties mailProperties = new Properties();
    private static String messageProperties;

    private static Exception exception;

    static {
        try {
            // Для .jar
            //mailProperties.load(new FileInputStream(new File(NAME_PROGRAM + File.separator + "mail.properties")));

            mailProperties.load(new FileInputStream(new File( "mail.properties")));
            System.out.println("Конфигурационный файл mail.properties прочитан успешно");
        } catch (Exception e) {
            exception = e;
        }

        try {
            // Для .jar
            //messageProperties = new FileHelper(NAME_PROGRAM + File.separator + "message.html").read();

            messageProperties = new FileHelper("message.html").read();
            System.out.println("Конфигурационный файл message.html прочитан успешно");
        } catch (IOException e) {
            exception = e;
        }
    }

    public static void init() throws Exception {
        if(exception != null)
            throw exception;

        // pass
    }

    public static Properties getMailProperties() throws Exception {
        if(exception != null)
            throw exception;

        return mailProperties;
    }

    public static String getMailProperty(String key) throws Exception {
        if(exception != null)
            throw exception;

        return mailProperties.getProperty(key);
    }

    public static String getMessageProperties() throws Exception {
        if(exception != null)
            throw exception;

        return messageProperties;
    }
}