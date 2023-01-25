package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Libraries.*;
import pom_repo.HomePage;

public class Bus_Oneway_001_Test extends BaseClass {
	@DataProvider()
	public String[][] data() {
		String info[][] = ReadData.multipleDataFromExcel("Naveen", "Bus_Oneway_001_Test");
		return info;
	}

	@Test(dataProvider = "data", groups = "functionality")
	public void oneWayFromTextField(String data[]) throws InterruptedException {
		// Step1:Clicking on FromTextField.
		explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
		homePage.getBusNavBar().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
		/*String expectedTitle = driver.getTitle();
		Assert.assertEquals(ReadData.fromPropertyFile("actualBusPageTitle"), expectedTitle,
				"Bus Page Is not Displayed");
		Reporter.log("Landed Succesfully to the Bus Home Page of the application", true);*/
		busPage.getFromTextBox().clear();
		String fromPlace = data[1];
		busPage.getFromTextBox().sendKeys(fromPlace);
		String enteredValue = busPage.getFromTextBox().getAttribute("value");
		if (fromPlace.equalsIgnoreCase("Bangalore")) {
			busPage.getBangaloreSearchSuggestion().click();
		} else if (fromPlace.equalsIgnoreCase("mumbai")) {
			busPage.getMumbaiSearchSuggestion().click();
		} else if (fromPlace.equalsIgnoreCase("hyderabad")) {
			busPage.getHyderabadSearchSuggestion().click();
		} else if (fromPlace.equalsIgnoreCase("chennai")) {
			busPage.getChennaiSearchSuggestion().click();
		} else if (fromPlace.equalsIgnoreCase("goa")) {
			busPage.getGoaSearchSuggestion().click();
		}
		Assert.assertEquals(enteredValue, fromPlace, "FromTextField data is not entered properly");
		Reporter.log("Data Entered Successfully in fromTextField in homePage", true);
	}
}