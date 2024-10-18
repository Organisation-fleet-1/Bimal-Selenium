package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	
	private By topSearchIcon = By.xpath("//a[@aria-controls='subnav-search-desktop-top']");
	private By txt_search = By.xpath("//input[@id='search-field']");
	private By search_btn = By.xpath("//button[@type='submit']");
	private By search_result = By.xpath("//h3[@class='title']");
	private By accept_all_cookies = By.xpath("//button[@id='onetrust-accept-btn-handler']");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean searchItem(String searchItem) {
		this.driver.findElement(topSearchIcon).click();
		
		WebElement txt_search_element = this.driver.findElements(txt_search).get(1);
		WebElement btn_search_element = this.driver.findElements(search_btn).get(1);
		
		// Wait for the element to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(txt_search_element));

        //checks search field is visible ornt
        if(!txt_search_element.isDisplayed() || !btn_search_element.isDisplayed())
        	return false;
		
		txt_search_element.click();  //clicks on search button on top

		txt_search_element.sendKeys(searchItem);  //enter the searched text
		btn_search_element.click();  //click on search button after entering text
		return true;
	}
	
	public String verifySearchResult(String expectedSearchedItem) {
			List<WebElement> elements  = this.driver.findElements(search_result);
			String searchFirstItemText = elements.get(0).getText();  //get the first result text
			return searchFirstItemText;
	}
	public void handleCookies() {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		// Wait for the cookie consent button to be clickable
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(accept_all_cookies)); 

        // Click the button to accept cookies
        acceptCookiesButton.click();		
	}
}
