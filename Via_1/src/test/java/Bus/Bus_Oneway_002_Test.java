package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Libraries.*;
import pom_repo.HomePage;

public class Bus_Oneway_002_Test extends BaseClass {
	@DataProvider()
	public String[][] data() {
		String info[][] = ReadData.multipleDataFromExcel("Naveen", "Bus_Oneway_002_Test");
		return info;
	}

	@Test(dataProvider = "data", groups = "functionality")
	public void negativeFromTextField(String data[]) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
		homePage.getBusNavBar().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
		/*String expectedTitle = driver.getTitle();
		Assert.assertEquals(ReadData.fromPropertyFile("actualBusPageTitle"), expectedTitle,
				"Bus Page Is not Displayed");
		Reporter.log("Landed Succesfully to the Bus Home Page of the application", true);*/
		// Step1:Clicking on FromTextField.
		busPage.getFromTextBox().clear();
		// Step2:Typing invalid characters.
		String fromPlace = data[1];
		busPage.getFromTextBox().sendKeys(fromPlace);
		String enteredValue = busPage.getFromTextBox().getAttribute("value");
		Assert.assertEquals(enteredValue, fromPlace, "FromTextField data is not entered properly");
		Reporter.log("Data Entered Successfully in fromTextField in homePage", true);
		busPage.getSearchBusButton().click();
		Assert.assertEquals(fromPlace, enteredValue, "FromTextField data is not entered properly");
		String errormessage = busPage.getFromErrorMessage().getText();
		Assert.assertEquals(errormessage, ReadData.fromPropertyFile("errorMessageForFromTextField"),
				"Error Message is not properly displayed");
		Reporter.log(errormessage);
		Reporter.log("Error message is displayed",true);
	}
}