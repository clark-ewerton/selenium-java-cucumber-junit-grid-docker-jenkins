package com.clark.stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.clark.driver.DriverManager;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {

	@AfterStep
	public void addScreenshot(Scenario scenario) {

		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image");
		}
	}

}
