package Bus;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Generic_Libraries.*;
import pom_repo.HomePage;

public class Bus_Oneway_006_Test extends BaseClass{
	@Test(groups = "functionality")
	public void searchButton() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
		homePage.getBusNavBar().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
		//click on search button
		busPage.getSearchBusButton().click();
		String errormessage = busPage.getFromErrorMessage().getText();
		Assert.assertEquals(errormessage, ReadData.fromPropertyFile("errorMessageForFromTextField"),
				"Error Message is not properly displayed");
		Reporter.log(errormessage);
		Reporter.log("Error message is displayed",true);
	}
}