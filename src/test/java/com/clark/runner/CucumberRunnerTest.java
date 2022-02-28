package com.clark.runner;
import static com.clark.config.ConfigurationManager.configuration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.clark.driver.DriverManager;
import com.clark.driver.TargetFactory;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.clark.stepDefinitions"},
        tags = "not @ignore",
        		plugin = { "pretty", "json:json/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        )
public class CucumberRunnerTest {
	
	@BeforeClass
    public static void beforeSuite() {
        WebDriver driver = new TargetFactory().createInstance("chrome");
        
        DriverManager.setDriver(driver);

        DriverManager.getDriver().get(configuration().url());
                
    }
	
    @AfterClass
    public static void postCondition() {
        DriverManager.quit();
    }

}
