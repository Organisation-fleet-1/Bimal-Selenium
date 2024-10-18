package hooks;


import io.cucumber.java.BeforeAll;
import Utility.DriverManager;
import io.cucumber.java.AfterAll;


public class Hooks {

	@BeforeAll
    public static void setupSuite() {
 		//DriverManager.getDriver();
    }

	@AfterAll
    public static void tearDownSuite() {
		//DriverManager.closeBrowser();
    }
}

