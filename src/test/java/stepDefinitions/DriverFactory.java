package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static boolean initialized = false;
	public static WebDriver driver;
	public static boolean isRodandoViaRunner = false;
	public static boolean isLogado = false;

	public static boolean iniciaDriver() {
		if (!initialized) {
			initialized = true;
			setupDriver();
			return true;
		}
		return false;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void fechaDriver() {
		driver.quit();
		initialized = false;
		setLogado(false);
	}

	public static boolean isInitialized() {
		return initialized;
	}

	private static void setupDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	public static void setIsRodandoViaRunnerTrue() {
		isRodandoViaRunner = true;
	}

	public static boolean isRodandoViaRunner() {
		return isRodandoViaRunner;
	}

	public static void setLogado(boolean isLogadoParametro) {
		isLogado = isLogadoParametro;
	}

	public static boolean isLogado() {
		return isLogado;
	}

}
