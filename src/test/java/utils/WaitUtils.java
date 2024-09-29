package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private final AppiumDriver driver;
    private static final Duration TIMEOUT_DURATION = Duration.ofSeconds(30); // Timeout for waiting for elements

    public WaitUtils(AppiumDriver driver) {this.driver = driver;}

    // Method to wait until an element is clickable
    public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_DURATION);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
