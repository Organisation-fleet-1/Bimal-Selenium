package Steps;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import Pages.FindCenterPage;
import Pages.HomePage;
import Utility.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FindCenter_Steps {
	WebDriver driver;

	private Properties properties = new Properties();
	
	public FindCenter_Steps() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("PropertyFile/config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Property file not found in the classpath");
            }
            // Load the properties file
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	@Before
	public void browserSetup() {
		driver = DriverManager.getDriver();
	}
	@After
	public void tearDown() {
		DriverManager.closeBrowser();
	}
	
	@When("click on the Find a Center option")
	public void click_on_the_Find_a_Center_option() {
		FindCenterPage findCenter = new FindCenterPage(driver);
		findCenter.clickOnFindCenter();
	}	
	
	@Then("the url should contain {string}")
	public void the_url_should_contain(String url) {
		// Get the current URL
		String currentUrl = driver.getCurrentUrl();
		boolean bool = currentUrl.contains(url);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(bool, "URL does not contain " + url + " after clicking 'Find a Center'");
		softAssert.assertAll();		
	}	
	@When("type {string} into the search box and press enter")
	public void type_input_and_press_enter(String inputString) {
		FindCenterPage findCenter = new FindCenterPage(driver);
		findCenter.findACenter(inputString);
	}	
	
	@Then("the number of found centers should match the number displayed in the list")
	public void check_number_of_center_found() {
		FindCenterPage findCenter = new FindCenterPage(driver);
		boolean bool = findCenter.verifyNumberOfCenters();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(bool, "the number of found centers does not match the number displayed in the list");
		softAssert.assertAll();			
	}	
	@When("click on the first center in the list")
	public void click_o_first_center() {
		FindCenterPage findCenter = new FindCenterPage(driver);
		findCenter.clickOnFirstCdenter();
	}	
	@Then("the center name and address should match with the details on the popup")
	public void verify_center_address() {
		FindCenterPage findCenter = new FindCenterPage(driver);
		boolean bool = findCenter.verifyCenterNameAndAdress();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(bool, "the center name and address does not match with the details on the popup");
		softAssert.assertAll();			
	}		
	
}
