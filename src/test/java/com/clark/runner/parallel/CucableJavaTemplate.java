package com.clark.runner.parallel;

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
		glue = {			
				"stepDefinitions"
				},
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
        plugin = {"json:json/[CUCABLE:RUNNER].json"},
        monochrome = true
        	
)
public class CucableJavaTemplate {
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