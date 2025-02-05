package helpers;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = "", // Package containing your step definitions
    plugin = {
        "pretty", // Readable output in the console
        "html:target/cucumber-reports/html-report.html", // HTML report
        "json:target/cucumber-reports/cucumber.json", // JSON report
        "junit:target/cucumber-reports/cucumber.xml"  // JUnit report
    },
    monochrome = true, // Optional: Display console output in monochrome
    dryRun = false, // Set to true to check if all steps have definitions
    tags = "@regression and not @wip" // Optional: Run scenarios based on tags
)


public class TestRunner {

}

