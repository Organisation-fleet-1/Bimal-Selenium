package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindCenterPage {
	WebDriver driver;
	
	private By topFindACenterButton =  By.xpath("//*[@class='nav-shared txt-nav-hierarchy nav-top js-nav-shared js-nav-top']//a[contains(text(),'Find a Center')]");
	private By address_input = By.id("addressInput");
	private By progress_bar = By.xpath("//*[@class='elipsesLoader']");
	private By number_of_centers = By.xpath("//*[@class='centerDetails results']");
	private By center_table_obj = By.xpath("//*[@class='centerResult infoWindow track_center_select covidOpen']");
	private By center_name = By.xpath("//*[@class='centerResult__name']");
	private By center_address = By.xpath("//*[@class='centerResult__address']");
	private By tooltip_center_name = By.xpath("//*[@class='mapTooltip__headline']");
	private By tolltip_address = By.xpath("//*[@class='mapTooltip__address']");
	private By floating_button = By.xpath("//button[@class='ot-floating-button__open']");
	private By pac_container = By.xpath("//div[contains(@class,'pac-container')]");
	private By mapContainer = By.xpath("//*[@class='map-container']");
	
	
	public FindCenterPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnFindCenter() {
		WebElement top_find_a_center_button = this.driver.findElements(topFindACenterButton).get(1);
		top_find_a_center_button.click();
	
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(mapContainer));
		wait.until(ExpectedConditions.visibilityOfElementLocated(floating_button));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("elipsesLoader")));
	}
	public void findACenter(String inputString) {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		// Wait for address input box to be visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(progress_bar));		
		
		WebElement findCenterInputBox = this.driver.findElement(address_input);
		
		findCenterInputBox.sendKeys(inputString);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(pac_container));

		findCenterInputBox.sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(pac_container));
	}	
	
	public boolean verifyNumberOfCenters() {
		WebElement tnoOfCenters = this.driver.findElement(number_of_centers);
		List<WebElement> noOfCentersFromTable = this.driver.findElements(center_table_obj);
		String noOfCenters = tnoOfCenters.getText();
		String numberOfCenters = noOfCenters.split(" ")[0];
		String totalCenters = noOfCentersFromTable.size() + "";
        if(numberOfCenters.equals(totalCenters))
        	return false;
        return true;
 	}	
	
	public void clickOnFirstCdenter() {
		WebElement firstCenter = this.driver.findElements(center_table_obj).get(0);
		firstCenter.click();
		
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip_center_name));
 	}	
	public boolean verifyCenterNameAndAdress() {
		WebElement firstCenter = this.driver.findElements(center_table_obj).get(0);
	 	String centerName = firstCenter.findElement(center_name).getText().trim();;
	 	String centerAddress = firstCenter.findElement(center_address).getText().trim();;
	 	
	 	WebElement tooltipCenterName = this.driver.findElement(tooltip_center_name);
	 	WebElement tooltipAddress = this.driver.findElement(tolltip_address);
		
	 	String tCenterName = tooltipCenterName.getText();
	 	String tCenterAddress = tooltipAddress.getText().trim();
	 	
	 	String tModifiedCenterAddress = tCenterAddress.replace("\n", " ").trim();
	 	
	 	if(tCenterName.equals(centerName) && tModifiedCenterAddress.equals(centerAddress))
	 		return true;
	 	
	 	return false;
 	}	
}
