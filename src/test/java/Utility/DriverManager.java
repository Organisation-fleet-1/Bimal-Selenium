package Utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private DriverManager() {}
	public static WebDriver getDriver() { 
		if(driver.get() == null) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		    
		}
		return driver.get();
	}
public static void closeBrowser() {
	if (driver.get() != null) {
		driver.get().quit();
		driver.remove();
	}
}	

}
