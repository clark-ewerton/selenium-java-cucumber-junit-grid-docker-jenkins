package runner;
import static com.clark.config.ConfigurationManager.configuration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.clark.driver.DriverManager;
import com.clark.driver.TargetFactory;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        tags = {"~@ignore"},
        		plugin = { "pretty", "json:json/cucumber.json"}
        )
public class CucumberRunnerTest {
	
	@BeforeClass
    public static void beforeSuite() {
        WebDriver driver = new TargetFactory().createInstance("chrome");
        
        DriverManager.setDriver(driver);

        DriverManager.getDriver().get(configuration().url());
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @AfterClass
    public static void postCondition() {
        DriverManager.quit();
    }

}
