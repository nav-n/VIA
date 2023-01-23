package Bus;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Generic_Libraries.*;
import pom_repo.HomePage;

public class Bus_Oneway_004Test extends BaseClass{

	@Test
	public void negativeToTextField(){
		explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
		homePage.getBusNavBar().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
		/*String expectedBusPageTitle = driver.getTitle();
		Assert.assertEquals(ReadData.fromPropertyFile("actualBusPageTitle"), expectedBusPageTitle,
				"Bus Page Is not Displayed");
		Reporter.log("Landed Succesfully to the Bus Home Page of the application", true);*/
		// Step1:Clicking on FromTextField.
		String fromPlace = ReadData.fromExcel("Naveen", 1, 1);
		busPage.getFromTextBox().sendKeys(fromPlace);
		String enteredFromValue = busPage.getFromTextBox().getAttribute("value");
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
		Assert.assertEquals(enteredFromValue, fromPlace,"FromTextField data is not entered properly");
		Reporter.log("Data entered successfully in fromTextBox",true);
		// Step2:Typing invalid characters.
		busPage.getToTextBox().click();
		String toPlace = ReadData.fromExcel("Naveen", 10, 1);
		busPage.getToTextBox().sendKeys(toPlace);
		String enteredToValue = busPage.getToTextBox().getAttribute("value");
		Assert.assertEquals(enteredToValue, toPlace,"ToTextField data is not entered properly");
		Reporter.log("Data Entered Successfully in ToTextField", true);
		busPage.getSearchBusButton().click();
		String errormessage = busPage.getToErrorMessage().getText();
		Assert.assertEquals(errormessage, ReadData.fromPropertyFile("errorMessageForToTextField"),"Error Message is not properly displayed");
		Reporter.log(errormessage);
		Reporter.log("Error message is displayed",true);
	}
}