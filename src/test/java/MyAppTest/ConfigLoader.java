package MyAppTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class ConfigLoader {

    private static final Logger logger = Logger.getLogger(ConfigLoader.class);

    public static String read(final String key) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(getEnvironment())) {
            prop.load(input);
        } catch (IOException ex) {
            logger.error("Cannot read from properties file");
        }
        return prop.getProperty(key);
    }

    public static String readDB(final String key) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("target/test-classes/env.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            logger.error("Cannot read from properties file");
        }
        return prop.getProperty(key);
    }

    public static String getEnvironment() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties");
        Properties commandLine = new Properties();
        try {
            commandLine.load(inputStream);
        } catch (IOException e) {
            logger.error("Cannot read argument from command line");
        }
        if (commandLine.getProperty("app.env").equalsIgnoreCase("local")) {
            return "src/test/resources/configLocal.properties";
        } else if (commandLine.getProperty("app.env").equalsIgnoreCase("dev")) {
            return "src/test/resources/configDev.properties";
        } else {
            throw new IllegalArgumentException("Invalid environment argument. Provide app.env local or dev");
        }
    }

    public static String getLongWorkingHoursDescriptionText() {
        File file = new File("src/test/resources/longdescription.txt");
        String description = null;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                description = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return description;
    }
}
