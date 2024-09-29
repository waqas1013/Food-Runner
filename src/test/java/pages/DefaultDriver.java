package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utility.FileHandlingUtility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DefaultDriver {
    protected AppiumDriver driver;
    String appiumServerURL = "http://127.0.0.1:4723/";
    String platformName = System.getProperty("platform");
    String deviceName = System.getProperty("device");
    Properties properties = new Properties();
    FileHandlingUtility fileHandlingUtility = new FileHandlingUtility();


    public AppiumDriver setUp() throws InterruptedException {

        System.out.println("platformName:  " + platformName);
        if (platformName == null){
            platformName = "iOS";
        }

        if (platformName.equalsIgnoreCase("Android")) {
            try {
                properties.load(fileHandlingUtility.getFileFromResources("androidCommon.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (platformName.equalsIgnoreCase("iOS")) {
            System.out.println("platformName:  " + platformName);
            try {
                properties.load(fileHandlingUtility.getFileFromResources("iOSCommon.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (platformName == null || platformName.equalsIgnoreCase("Android")) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName(deviceName)
                    .setPlatformName(platformName)
                    .setAppPackage(properties.getProperty("appPackageName"))
                    .setAppActivity(properties.getProperty("appActivityName"))
                    .setAutomationName(properties.getProperty("automationName"));

            try {
                driver = new AndroidDriver(new URL(appiumServerURL), options);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else if (platformName.equalsIgnoreCase("iOS")) {
            System.out.println("platformName:  " + platformName);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", (properties.getProperty("deviceName")));
            caps.setCapability("platformVersion", "17.5");
            caps.setCapability("automationName", "XCUITest"); // Use XCUITest for iOS
            caps.setCapability("bundleId", properties.getProperty("bundleId"));

            try {
                driver = new IOSDriver(new URL(appiumServerURL), caps);
                Thread.sleep(5000);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Platform Type is invalid");
        }

        return driver;

        /*String platformName = Objects.requireNonNull(options.getPlatformName()).toString();

        String appiumServerURL = "http://127.0.0.1:4723/";

        try {
            if (platformName.equals("Android")) {
                driver = new AndroidDriver(new URL(appiumServerURL), options);
            } else {
                driver = new IOSDriver(new URL(appiumServerURL), options);
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        //driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

         */
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}