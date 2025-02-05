# Mobile Automation Test with Appium

This project demonstrates mobile UI testing using Appium for an Android application.

## Prerequisites

* **Java Development Kit (JDK):** Ensure you have JDK 8 or later installed.  Verify with `java -version`.
* **Android SDK:** Install the Android SDK and set the `ANDROID_HOME` environment variable. Also, make sure you have the necessary platform tools and build tools installed.
* **Appium Server:** Download and install the Appium Server.
* **Appium Java Client:** Include the Appium Java Client library in your project.
* **TestNG:** Include the TestNG testing framework in your project.
* **Android Emulator or Device:**  Have an Android emulator running or a physical device connected to your machine. Ensure developer mode and USB debugging are enabled on the physical device if used.
* **Target App:** The APK of the application under test. Replace `/path/to/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk` with the actual path in `LoginTest.java`.

## Setup and Installation

1. **Clone the repository:** `git clone <repository_url>`
2. **Import into IDE:** Import the project into your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. **Install Dependencies:** Ensure all dependencies (Appium Java Client, TestNG, etc.) are downloaded and included in the project.  Use your IDE's dependency management features (Maven or Gradle) to resolve and download them.
4. **Update App Path:**  Open `LoginTest.java` and replace `/path/to/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk` with the correct path to your application's APK file.
5. **Start Appium Server:** Launch the Appium Server.


## Running the Tests

1. **Open the project in your IDE.**
2. **Navigate to the `LoginTest.java` file.**
3. **Right-click on the file and select "Run" or use your IDE's test runner to execute the tests.** Alternatively, execute the tests from the command line using your build tool (e.g., `mvn test` for Maven, `gradlew test` for Gradle).
