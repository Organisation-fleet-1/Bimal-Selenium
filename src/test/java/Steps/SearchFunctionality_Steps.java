package Steps;

import Utility.DriverManager;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;

import java.util.Properties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchFunctionality_Steps {
	WebDriver driver;
	HomePage homePage;

	private Properties properties = new Properties();
	
	public SearchFunctionality_Steps() {
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
	
	@Given("Open browser with url and navigate to the BH home page")
	public void Open_browser_with_url_and_navigate_to_the_BH_home_page() {
		String baseUrl = properties.getProperty("base.url");
		driver.get(baseUrl);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(driver.getTitle().contains("Welcome to Bright Horizons"));
		
		//handle cookies 
		homePage = new HomePage(driver);
		homePage.handleCookies();
	   
		softAssert.assertAll();
	}

	@When("search the provided input item {string}")
	public void search_the_provided_input_item(String inputItem) {
		homePage = new HomePage(driver);
		boolean isSearch = homePage.searchItem(inputItem);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isSearch, "Search field is not visible");
		softAssert.assertAll();
	}

	@Then("verify expected first search result should match {string}")
	public void verify_expected_first_search_result_should_match(String searchResult) {
		homePage = new HomePage(driver);
		String searchFirstItemText = homePage.verifySearchResult(searchResult);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(searchFirstItemText, searchResult, 
			    "Expected first element \"" + searchResult + "\" but found \"" + searchFirstItemText + "\"");
		softAssert.assertAll();
	}
}
