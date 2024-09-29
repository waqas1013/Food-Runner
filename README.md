# Screen Object Model Framework

## Overview

The Screen Object Model (SOM) is a powerful design pattern widely utilized in test automation, particularly in Mobile UI Automation. This framework significantly enhances test maintenance and reduces code duplication. In this framework, tests leverage the screen object class methods to interact with the UI of the application.

### Key Benefits
- **Maintainability:** When the UI of a page changes, only the code within the screen object needs to be updated, not the tests themselves.
- **Separation of Concerns:** Locators and test scripts are stored separately, allowing for a cleaner architecture and easier updates.

## Tools and Technologies Used

- **Programming Language:** Java
- **Testing Framework:** TestNG
- **Design Pattern:** Screen Object Model
- **Automation Tools:** Selenium, Appium
- **Build Tool:** Maven
- **Reporting Tool:** ExtentReport

## Prerequisites

Before you start, ensure you have the following installed on your system:

1. **Java**
    - Download the latest version of Java from.
    - Verify the installation by running the following command in your terminal:
      ```bash
      java -version
      ```

2. **Maven**
    - Download and install Maven from (https://maven.apache.org/download.cgi).
    - Set `M2_HOME` and `JAVA_HOME` in your `~/.bash_profile`.

3. **Appium**
    - Ensure that `npm` (Node Package Manager) is installed on your system. Verify it by running:
      ```bash
      npm -v
      ```
    - Install Appium using the following command:
      ```bash
      npm install -g appium
      ```
    - Verify the Appium installation:
      ```bash
      appium -v
      ```

4. **Start Appium Server**
    - Launch the Appium server on port 4723 by executing:
      ```bash
      appium
      ```
    - Ensure that the Android Emulator and iOS Simulators are properly configured on your system.

5. **IDE Installation**
    - Download and install an IDE of your choice (e.g., Eclipse or IntelliJ). The project is developed using IntelliJ IDEA.

## Project Setup

1. **Clone the Project**
    - Clone the project from GitHub or download it as a ZIP file.

2. **Android and iOS Setup**
    - You can use the Android APK provided in the project named **FoodRunner**. For iOS, build the project from the code I shared in the email. Run the app on the simulator to execute tests.

## Execution of Tests

### Run Individual Test
Individual tests can be executed from the `MainScreenTests` file.

- **To run tests for Android via Command Line:**
  ```bash
  mvn test -Dplatform=android

- **To run tests for iOS via Command Line:**
  ```bash
  mvn test -Dplatform=iOS

## Reporting Mechanism
The testing reports are generated using **ExtentReport**. You can access the reports by visiting the following file:

**/test.output/ExtentReport.html**