package pages;

import utility.FileHandlingUtility;

import java.io.IOException;
import java.util.Properties;


public class LocatorLoader {

    String platformName = System.getProperty("platform");
    Properties properties = new Properties();
    FileHandlingUtility fileHandlingUtility = new FileHandlingUtility();

    public LocatorLoader() {

        if (platformName == null) {
            platformName = "iOS";
        }

        if (platformName.equalsIgnoreCase("android")) {
            try {
                properties.load(fileHandlingUtility.getFileFromResources("locators/androidLocators.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (platformName.equalsIgnoreCase("iOS")) {
            try {
                properties.load(fileHandlingUtility.getFileFromResources("locators/iOSLocators.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}