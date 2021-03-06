package myrunner;
//This line added in main branch
import java.net.URL;
//This is the comment to explain origin/main
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import io.cucumber.testng.FeatureWrapper;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.nt"},
        //tags = "@Regression",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })

public class TestRunner extends AbstractTestNGCucumberTests {
	WebDriver driver;
   // private TestNGCucumberRunner testNGCucumberRunner; This is added from GitHub. Added again
  
       
	/*
	 * @BeforeClass(alwaysRun = true) public void setUpCucumber() {
	 * testNGCucumberRunner = new TestNGCucumberRunner(this.getClass()); }
	 */
    
   
}
