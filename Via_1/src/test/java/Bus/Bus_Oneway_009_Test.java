package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Libraries.BaseClass;
import Generic_Libraries.ReadData;

public class Bus_Oneway_009_Test extends BaseClass {

	@DataProvider()
	public String[][] data() {
		String info[][] = ReadData.multipleDataFromExcel("Naveen", "Bus_Oneway_009_Test");
		return info;
	}

	@Test(dataProvider = "data",groups = "functionality")
	public void oneWaySystemTesting(String data[]) throws InterruptedException {
		// Step1:Clicking on FromTextField.
		explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
		homePage.getBusNavBar().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
		busPage.getFromTextBox().clear();
		String fromPlace = data[1];
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

		Assert.assertEquals(enteredFromValue, fromPlace, "FromTextField data is not entered properly");
		Reporter.log("Data Entered Successfully in fromTextField in homePage", true);

		// To Place
		String toPlace = data[2];
		busPage.getToTextBox().clear();
		busPage.getToTextBox().sendKeys(toPlace);
		String enteredToValue = busPage.getToTextBox().getAttribute("value");
		if (toPlace.equalsIgnoreCase("Bangalore")) {
			busPage.getBangaloreSearchSuggestion().click();
		} else if (toPlace.equalsIgnoreCase("mumbai")) {
			busPage.getMumbaiSearchSuggestion().click();
		} else if (toPlace.equalsIgnoreCase("hyderabad")) {
			busPage.getHyderabadSearchSuggestion().click();
		} else if (toPlace.equalsIgnoreCase("chennai")) {
			busPage.getChennaiSearchSuggestion().click();
		} else if (toPlace.equalsIgnoreCase("goa")) {
			busPage.getGoaSearchSuggestion().click();
		}
		Assert.assertEquals(enteredToValue, toPlace, "FromTextField data is not entered properly");
		Reporter.log("Data Entered Successfully in toTextField in homePage", true);

		// calendar
		action = new Actions(driver);
		explicitWait.until(ExpectedConditions.visibilityOf(busPage.getSearchBusButton()));
		// date from calender popup.

		String date = data[3];
		int day = ReadData.readDayFromExcel(date);
		String month = ReadData.readMonthFromExcel(date);
		homePage.getDepartureCalenderTextBox().click();
		System.out.println(day + "-" + month);
		for (;;) {
			try {
				WebElement calendar = driver
						.findElement(By.xpath("//span[text()='" + month + "']/../../..//div[text()='" + day + "']"));
				explicitWait.until(ExpectedConditions.elementToBeClickable(calendar));
				// calendar.click();
				action.moveToElement(calendar).doubleClick().perform();
				break;
			} catch (Exception e) {
				homePage.getRightCalenderArrowIcon().click();
			}
		}

		// search button
		explicitWait.until(ExpectedConditions.visibilityOf(busPage.getSearchBusButton()));
		busPage.getSearchBusButton().click();

		// Bus Result
		explicitWait.until(ExpectedConditions.visibilityOf(busResultPage.getNextDayLink()));
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(ReadData.fromPropertyFile("actualBusResultsTitle"), expectedTitle,
				"Bus Result Page Is not Displayed");
		Reporter.log("Landed Succesfully to the Bus Result Page of the application", true);
		busResultPage.getViewSeats().click();
		boolean hideSeat = busResultPage.getHideseats().isDisplayed();
		if(hideSeat) {
			Reporter.log("clicked on the view seats button",true);
		}
		else {
			Reporter.log("view seats button not clicked",true);
		}
	}
}
