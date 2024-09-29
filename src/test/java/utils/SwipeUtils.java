package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class SwipeUtils {

    private final AppiumDriver driver;

    public SwipeUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // Swipe method using W3C actions
    public void swipe(Direction direction, long durationMillis) {
        // Get screen dimensions
        int screenWidth = driver.manage().window().getSize().width;
        int screenHeight = driver.manage().window().getSize().height;

        int startX = 0, startY = 0, endX = 0, endY = 0;

        // Define swipe coordinates based on direction
        switch (direction) {
            case UP:
                startX = screenWidth / 2;
                startY = (int) (screenHeight * 0.9);  // Start from 90% down the screen (closer to the bottom)
                endX = screenWidth / 2;
                endY = (int) (screenHeight * 0.1);  // Swipe to 10% from the top (closer to the top)
                break;

            case DOWN:
                startX = screenWidth / 2;
                startY = (int) (screenHeight * 0.1);  // Start from 10% down the screen (closer to the top)
                endX = screenWidth / 2;
                endY = (int) (screenHeight * 0.9);  // Swipe to 90% from the top (closer to the bottom)
                break;

            case LEFT:
                startX = (int) (screenWidth * 0.9);  // Start from 90% of the width
                startY = screenHeight / 2;
                endX = (int) (screenWidth * 0.1);  // Swipe to 10% of the width
                endY = screenHeight / 2;
                break;

            case RIGHT:
                startX = (int) (screenWidth * 0.1);  // Start from 10% of the width
                startY = screenHeight / 2;
                endX = (int) (screenWidth * 0.9);  // Swipe to 90% of the width
                endY = screenHeight / 2;
                break;
        }

        // Perform swipe using W3C actions
        performSwipe(startX, startY, endX, endY, durationMillis);
    }

    private void performSwipe(int startX, int startY, int endX, int endY, long durationMillis) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(durationMillis), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}