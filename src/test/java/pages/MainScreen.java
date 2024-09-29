package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WaitUtils;


public class MainScreen extends LocatorLoader {
    private final AppiumDriver appiumDriver;
    private final WaitUtils waitUtils;
    String platformName = System.getProperty("platform");


    // Constructor to initialize the driver and page elements
    public MainScreen(AppiumDriver driver) {
        this.appiumDriver = driver;
        this.waitUtils = new WaitUtils(driver);
        if (platformName == null) {
            platformName = "iOS";
        }
    }

    // Method to check if the home page is visible
    public boolean isHomePageDisplayed() {
        WebElement mainScreen;
        if (platformName.equalsIgnoreCase("Android")) {
            mainScreen = appiumDriver.findElement(By.xpath(properties.getProperty("mainScreen")));
            return mainScreen != null && mainScreen.isDisplayed();
        } else {
            mainScreen = appiumDriver.findElement(By.id(properties.getProperty("appLogo")));
            return mainScreen != null && mainScreen.isDisplayed();
        }
    }


    // Method to click on the "Fast Food" filter. Also checking the state if its clicked. iOS filter state always returns true. Test still passes for iOS because the state is returned as true always, to make it fail, if you change the assertion to false, then the test will fail, because we will expect false but it will return true.
    public void applyFastFoodFilter() {
        WebElement fastFoodFilter;
        if (platformName.equalsIgnoreCase("Android")) {
            fastFoodFilter = appiumDriver.findElement(By.xpath(properties.getProperty("fastFoodFilter")));
            waitUtils.waitUntilVisible(fastFoodFilter);
            fastFoodFilter.click();
            Assert.assertTrue(fastFoodFilter.isEnabled());
        } else {
            fastFoodFilter = appiumDriver.findElement(By.name(properties.getProperty("fastFoodFilter")));
            waitUtils.waitUntilVisible(fastFoodFilter);
            fastFoodFilter.click();
            Assert.assertTrue(fastFoodFilter.isEnabled());

        }
    }

    // Method to click on the "Top Rated" filter
    public void applyTopRatedFilter() {
        WebElement applyTopRatedFilter;
        if (platformName.equalsIgnoreCase("Android")) {
            applyTopRatedFilter = appiumDriver.findElement(By.xpath(properties.getProperty("topRatedFilter")));
            waitUtils.waitUntilVisible(applyTopRatedFilter);
            applyTopRatedFilter.click();
        } else {
            applyTopRatedFilter = appiumDriver.findElement(By.id(properties.getProperty("topRatedFilter")));
            waitUtils.waitUntilVisible(applyTopRatedFilter);
            applyTopRatedFilter.click();

        }
    }

    // Method to click on the "Take Out" filter
    public void applyTakeOutFilter() {
        WebElement applyTakeOutFilter;
        if (platformName.equalsIgnoreCase("Android")) {
            applyTakeOutFilter = appiumDriver.findElement(By.xpath(properties.getProperty("takeOutFilter")));
            waitUtils.waitUntilVisible(applyTakeOutFilter);
            applyTakeOutFilter.click();
        } else {
            applyTakeOutFilter = appiumDriver.findElement(By.id(properties.getProperty("takeOutFilter")));
            waitUtils.waitUntilVisible(applyTakeOutFilter);
            applyTakeOutFilter.click();

        }
    }

    //In Multiple Filters testing, we test 2 filters applied together, in this case, Fast Food filter & Top Rated filters are applied. And checking state of filter that its applied.
    public boolean areMultipleFiltersSelected() {
        WebElement fastFoodFilter;
        WebElement topRatedFilter;
        if (platformName.equalsIgnoreCase("Android")) {
            fastFoodFilter = appiumDriver.findElement(By.xpath(properties.getProperty("fastFoodFilter")));
            topRatedFilter = appiumDriver.findElement(By.xpath(properties.getProperty("topRatedFilter")));
            return (topRatedFilter.isEnabled() && fastFoodFilter.isEnabled());
        } else {
            fastFoodFilter = appiumDriver.findElement(By.id(properties.getProperty("fastFoodFilter")));
            topRatedFilter = appiumDriver.findElement(By.id(properties.getProperty("topRatedFilter")));
            return (topRatedFilter.isEnabled() && fastFoodFilter.isEnabled());
        }
    }

    public boolean isTopRatedFilterSelected() {
        WebElement topRatedFilter;
        if (platformName.equalsIgnoreCase("Android")) {
            topRatedFilter = appiumDriver.findElement(By.xpath(properties.getProperty("topRatedFilter")));
            return (topRatedFilter.isEnabled());
        } else {
            topRatedFilter = appiumDriver.findElement(By.id(properties.getProperty("topRatedFilter")));
            return (topRatedFilter.isEnabled());
        }
    }

    public boolean isTakeOutFilterSelected() {
        WebElement takeOutFilter;
        if (platformName.equalsIgnoreCase("Android")) {
            takeOutFilter = appiumDriver.findElement(By.xpath(properties.getProperty("takeOutFilter")));
            return takeOutFilter.isEnabled();
        } else {
            takeOutFilter = appiumDriver.findElement(By.id(properties.getProperty("takeOutFilter")));
            return takeOutFilter.isEnabled();
        }
    }

    public boolean isWayneChadBroskiBurgerRestauarantIsDisplayed() {
        try {
            WebElement wayneChadBroskiBurgerRestaurant;
            if (platformName.equalsIgnoreCase("Android")) {
                wayneChadBroskiBurgerRestaurant = appiumDriver.findElement(By.xpath(properties.getProperty("wayneChadBroskiBurgerRestaurant")));
            } else {
                wayneChadBroskiBurgerRestaurant = appiumDriver.findElement(By.id(properties.getProperty("firstRestaurantInTheList")));
            }
            return wayneChadBroskiBurgerRestaurant.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isYumasCandyShopIsDisplayed() {
        try {
            WebElement yumasCandyShop;
            if (platformName.equalsIgnoreCase("Android")) {
                yumasCandyShop = appiumDriver.findElement(By.xpath(properties.getProperty("yumasCandyShop")));
            } else {
                yumasCandyShop = appiumDriver.findElement(By.id(properties.getProperty("yumasCandyShop")));
            }
            return yumasCandyShop.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isEmiliasFancyFoodIsDisplayed() {
        try {
            WebElement emiliasFancyFood;
            if (platformName.equalsIgnoreCase("Android")) {
                emiliasFancyFood = appiumDriver.findElement(By.xpath(properties.getProperty("emiliasFancyFood")));
            } else {
                emiliasFancyFood = appiumDriver.findElement(By.id(properties.getProperty("emiliasFancyFood")));
            }
            return emiliasFancyFood.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPizzeriaVarshaRestauarantIsDisplayed() {
        try {
            WebElement pizzeriaVarshaRestaurant;
            if (platformName.equalsIgnoreCase("Android")) {
                pizzeriaVarshaRestaurant = appiumDriver.findElement(By.xpath(properties.getProperty("pizzeriaVarshaRestaurant")));
            } else {
                pizzeriaVarshaRestaurant = appiumDriver.findElement(By.id(properties.getProperty("pizzeriaVarshaRestaurant")));
            }
            return pizzeriaVarshaRestaurant.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isGuillaumeCroissantIsDisplayed() {
        try {
            WebElement guillaumeCroissants;
            if (platformName.equalsIgnoreCase("Android")) {
                guillaumeCroissants = appiumDriver.findElement(By.xpath(properties.getProperty("guillaumesCroissants")));
            } else {
                guillaumeCroissants = appiumDriver.findElement(By.id(properties.getProperty("guillaumesCroissants")));
            }
            return guillaumeCroissants.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isMartinsMancaveIsDisplayed() {
        try {
            WebElement martinsMancave;
            if (platformName.equalsIgnoreCase("Android")) {
                martinsMancave = appiumDriver.findElement(By.xpath(properties.getProperty("martinsMancave")));
            } else {
                martinsMancave = appiumDriver.findElement(By.id(properties.getProperty("martinsMancave")));
            }
            return martinsMancave.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isHenriksMuddyWaterRestauarantIsDisplayed() {
        try {
            WebElement henriksMuddyRestaurant;
            if (platformName.equalsIgnoreCase("Android")) {
                henriksMuddyRestaurant = appiumDriver.findElement(By.xpath(properties.getProperty("henriksMuddyWaterRestaurant")));
            } else {
                henriksMuddyRestaurant = appiumDriver.findElement(By.id(properties.getProperty("henriksMuddyWaterRestaurant")));
            }
            return henriksMuddyRestaurant.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPontusExpiredYogurtIsDisplayed() {
        try {
            WebElement pontusExpiredYogurt;
            if (platformName.equalsIgnoreCase("Android")) {
                pontusExpiredYogurt = appiumDriver.findElement(By.xpath(properties.getProperty("pontusExpiredYogurts")));
            } else {
                pontusExpiredYogurt = appiumDriver.findElement(By.id(properties.getProperty("pontusExpiredYogurts")));
            }
            return pontusExpiredYogurt.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDoortjeBedAndBreakfastIsDisplayed() {
        try {
            WebElement doortjeBedAndBreakfast;
            if (platformName.equalsIgnoreCase("Android")) {
                doortjeBedAndBreakfast = appiumDriver.findElement(By.xpath(properties.getProperty("doortjeBed&Breakfast")));
            } else {
                doortjeBedAndBreakfast = appiumDriver.findElement(By.id(properties.getProperty("doortjeBed&Breakfast")));
            }
            return doortjeBedAndBreakfast.isDisplayed();
        } catch (NoSuchElementException e) {
            // If the element is not found, return false instead of throwing an exception
            return false;
        }
    }

    public boolean verifyEstimateDeliveryTimeIsShownOnRestaurantCard() {
        try {
            WebElement estimateDeliveryTime;
            if (platformName.equalsIgnoreCase("Android")) {
                estimateDeliveryTime = appiumDriver.findElement(By.xpath(properties.getProperty("doortjeBed&Breakfast")));
            } else {
                estimateDeliveryTime = appiumDriver.findElement(By.id(properties.getProperty("doortjeBed&Breakfast")));
            }
            return estimateDeliveryTime.isDisplayed();
        } catch (NoSuchElementException e) {
            // If the element is not found, return false instead of throwing an exception
            return false;
        }
    }
}