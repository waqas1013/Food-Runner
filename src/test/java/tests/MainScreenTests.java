package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DefaultDriver;
import pages.LocatorLoader;
import pages.MainScreen;
import utils.SwipeUtils;

import java.net.MalformedURLException;

public class MainScreenTests extends LocatorLoader {

    private DefaultDriver defaultDriver;
    private AppiumDriver driver;
    private MainScreen mainScreen;
    private SwipeUtils swipeUtils;
    private ExtentReports extent;  // For generating reports
    private ExtentTest test; // For logging test steps

    @BeforeClass
    public void setUp() {
        // Set up Extent Reports with ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Food Runner App Automation Tests Report");
        sparkReporter.config().setReportName("Automation Tests Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void initializeDriver() throws MalformedURLException, InterruptedException {
        // Initialize test objects before each test
        defaultDriver = new DefaultDriver();
        driver = defaultDriver.setUp();
        mainScreen = new MainScreen(driver);
        swipeUtils = new SwipeUtils(driver);  // Initialize SwipeUtils with driver
    }

    //In this test, We verify that App is opened. And unfiltered list of restaurants are shown.
    @Test
    public void testMainScreenIsDisplayedWithUnfilteredListOfRestaurants() {
        test = extent.createTest("testMainScreenIsDisplayed", "Verify that the Main Screen is displayed");
        try {
            test.log(Status.INFO, "Verifying if Main Screen is displayed");
            Assert.assertTrue(mainScreen.isMainScreenDisplayed());
            test.log(Status.PASS, "Main Screen is displayed successfully");
            Assert.assertTrue(mainScreen.isWayneChadBroskiBurgerRestauarantIsDisplayed());
            Assert.assertTrue(mainScreen.isYumasCandyShopIsDisplayed());
            Assert.assertTrue(mainScreen.isEmiliasFancyFoodIsDisplayed());
            test.log(Status.PASS, "First 3 restaurants in the list are shown");
            swipeUtils.swipe(SwipeUtils.Direction.UP, 1000);
            swipeUtils.swipe(SwipeUtils.Direction.UP, 1000);
            test.log(Status.INFO, "Swiped up on the home screen");
            Assert.assertTrue(mainScreen.isHenriksMuddyWaterRestauarantIsDisplayed());
            Assert.assertTrue(mainScreen.isPontusExpiredYogurtIsDisplayed());
            Assert.assertTrue(mainScreen.isDoortjeBedAndBreakfastIsDisplayed());
            test.log(Status.INFO, "Last 3 restaurants are displayed in the list");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Main Screen is not displayed");
        }
    }

    @Test
    public void testApplyFastFoodFilter() {
        test = extent.createTest("testApplyFastFoodFilter", "Verify that Fast Food filter can be applied");
        try {
            test.log(Status.INFO, "Verifying if Main Screen is displayed");
            Assert.assertTrue(mainScreen.isMainScreenDisplayed());
            test.log(Status.PASS, "Main Screen is displayed successfully");
            test.log(Status.INFO, "Applying Fast Food filter");
            mainScreen.applyFastFoodFilter();
            test.log(Status.INFO, "Fast Food filter applied successfully");
            test.log(Status.PASS, "Fast Food restaurant is displayed after applying the filter");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Failed to apply Fast Food filter");
        }
    }

    //Apply Top Rated Filter and verify correct list of restaurants are shown.
    @Test
    public void testApplyTopRatedFilterAndVerifyResults() {
        test = extent.createTest("testApplyTopRatedFilter", "Verify that the Top Rated filter can be applied and results are correct");
        try {
            test.log(Status.INFO, "Verifying if Main Screen is displayed");
            Assert.assertTrue(mainScreen.isMainScreenDisplayed());
            test.log(Status.PASS, "Main Screen is displayed successfully");

            test.log(Status.INFO, "Applying Top Rated filter");
            mainScreen.applyTopRatedFilter();
            test.log(Status.INFO, "Top Rated filter applied successfully");

            test.log(Status.INFO, "Verifying if the Top Rated filter is selected");
            Assert.assertTrue(mainScreen.isTopRatedFilterSelected());
            test.log(Status.PASS, "Top Rated filter is selected");

            test.log(Status.INFO, "Verifying the displayed restaurants after applying the Top Rated filter");
            Assert.assertTrue(mainScreen.isWayneChadBroskiBurgerRestauarantIsDisplayed());
            Assert.assertTrue(mainScreen.isYumasCandyShopIsDisplayed());
            Assert.assertTrue(mainScreen.isEmiliasFancyFoodIsDisplayed());
            test.log(Status.INFO, "Swiping up to view more restaurants");
            swipeUtils.swipe(SwipeUtils.Direction.UP, 1000);
            test.log(Status.INFO, "Swipe action completed");
            Assert.assertTrue(mainScreen.isGuillaumeCroissantIsDisplayed());
            test.log(Status.PASS, "Top rated restaurants are displayed");
            test.log(Status.INFO, "Verify that a restaurant not listed as Top Rated does not appear.");
            Assert.assertFalse(mainScreen.isDoortjeBedAndBreakfastIsDisplayed());
            test.log(Status.PASS, "Doortje's Bed And Breakfast is not displayed as it is not top-rated");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed due to assertion error: " + e.getMessage());
            throw e;  // rethrow to ensure test fails correctly
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e;  // rethrow to ensure test fails correctly
        }
    }

    @Test
    public void testApplyMultipleFiltersAndCheckState() {
        test = extent.createTest("applyMultipleFiltersAndCheckState", "Verify the state of filters when multiple filters are applied");
        try {
            test.log(Status.INFO, "Verifying if Main Screen is displayed");
            Assert.assertTrue(mainScreen.isMainScreenDisplayed());
            test.log(Status.PASS, "Main Screen is displayed successfully");

            boolean expectedStateOfFilter = true;
            test.log(Status.INFO, "Applying Fast Food filter");
            mainScreen.applyFastFoodFilter();
            test.log(Status.INFO, "Applying Top Rated filter");
            mainScreen.applyTopRatedFilter();

            test.log(Status.INFO, "Verifying if multiple filters are selected");
            Assert.assertEquals(expectedStateOfFilter, mainScreen.areMultipleFiltersSelected(), "Multiple filters should be selected.");
            test.log(Status.PASS, "Multiple filters are correctly applied and their state is as expected");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed due to assertion error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        }
    }

    // This test first verifies that the restaurant list is displayed without any filters applied.
// Then, it applies a filter and checks if the restaurant list is updated accordingly.
// Finally, the filter is deselected to ensure the list updates again after removing the filter.

    @Test
    public void testVerifyFilterIsDeselected() throws InterruptedException {
        test = extent.createTest("verifyFilterIsDeselected", "Verify that the filters can be deselected correctly");
        try {
            test.log(Status.INFO, "Verifying if Main Screen is displayed");
            Assert.assertTrue(mainScreen.isMainScreenDisplayed());
            test.log(Status.PASS, "Main Screen is displayed successfully");

            test.log(Status.INFO, "Checking if initial restaurants are displayed");
            Assert.assertTrue(mainScreen.isWayneChadBroskiBurgerRestauarantIsDisplayed());
            Assert.assertTrue(mainScreen.isYumasCandyShopIsDisplayed());
            Assert.assertTrue(mainScreen.isEmiliasFancyFoodIsDisplayed());
            test.log(Status.PASS, "Initial restaurants are displayed successfully");

            test.log(Status.INFO, "Swiping up to view more restaurants");
            swipeUtils.swipe(SwipeUtils.Direction.UP, 1000);
            swipeUtils.swipe(SwipeUtils.Direction.UP, 1000);
            test.log(Status.INFO, "Swiped up to reveal additional restaurants");

            test.log(Status.INFO, "Checking if additional restaurants are displayed");
            Assert.assertTrue(mainScreen.isHenriksMuddyWaterRestauarantIsDisplayed());
            Assert.assertTrue(mainScreen.isPontusExpiredYogurtIsDisplayed());
            Assert.assertTrue(mainScreen.isDoortjeBedAndBreakfastIsDisplayed());
            test.log(Status.PASS, "All expected restaurants are displayed successfully");

            test.log(Status.INFO, "Applying Fast Food filter");
            mainScreen.applyFastFoodFilter();
            test.log(Status.INFO, "Fast Food filter applied. Verifying if restaurants are displayed as expected");
            Assert.assertTrue(mainScreen.isWayneChadBroskiBurgerRestauarantIsDisplayed());
            test.log(Status.PASS, "Wayne Chad Broski Burger Restaurant is displayed after applying the Fast Food filter.");
            test.log(Status.INFO, "Click Fast Food Filter again to deselect it.");
            mainScreen.applyFastFoodFilter();
            test.log(Status.INFO, "Fast food filter de-selected. And list of restaurants should be updated");
            Assert.assertTrue(mainScreen.isYumasCandyShopIsDisplayed());
            test.log(Status.PASS, "Yuma's Candy Shop is displayed after de-selecting the Fast Food filter.");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed due to assertion error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        }
    }


    @Test
    public void testEstimateDeliveryTimeIsShownOnRestaurantCard() {
        test = extent.createTest("testEstimateDeliveryTimeIsShownOnRestaurantCard", "Verify that estimated delivery time is shown on restaurant card");

        try {
            test.log(Status.INFO, "Verifying if the estimated delivery time is displayed");
            boolean isEstimateTimeDisplayed = mainScreen.verifyEstimateDeliveryTimeIsShownOnRestaurantCard();
            Assert.assertTrue(isEstimateTimeDisplayed);
            test.log(Status.PASS, "Estimated delivery time is displayed successfully on the restaurant card");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed due to assertion error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        }
    }

    @Test
    public void testStarRatingIsShownOnRestaurantCard() {
        test = extent.createTest("testStarRatingIsShownOnRestaurantCard", "Verify that star ratings are shown on restaurant card");

        try {
            test.log(Status.INFO, "Verifying if the star rating is displayed");
            boolean isStarRatingDisplayed = mainScreen.verifyStarRatingIsShownOnRestaurantCard();
            Assert.assertTrue(isStarRatingDisplayed);
            test.log(Status.PASS, "Star Ratings are displayed on the restaurant card");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Test failed due to assertion error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e;  // Rethrow to ensure the test fails correctly
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Quit the driver after each test
        }
    }

    @AfterClass
    public void flushTheReports() {
        // Ensure the report is generated and saved
        if (extent != null) {
            extent.flush();
        }
    }
}