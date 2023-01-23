package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Libraries.BaseClass;
import Generic_Libraries.ReadData;
import pom_repo.*;

public class Bus_Oneway_Place extends BaseClass {
	@DataProvider()
	public String[][] data(){
		String info[][]=ReadData.multipleDataFromExcel("Naveen","Bus_Oneway_Place");
		return info;
	}

	@Test(dataProvider = "data",priority = 1)
	public void oneWayFromTextField() throws InterruptedException {
		// Step1:Clicking on FromTextField.
		explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
		homePage.getBusNavBar().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
		busPage.getFromTextBox().clear();
		String fromPlace = ReadData.fromExcel("naveen", 20, 1);
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

	@Test(dataProvider = "data",priority = 2)
	public void oneWayToTextField() {
		// Step1:Clicking on FromTextField.'
		String toPlace = ReadData.fromExcel("Naveen", 20, 2);
		busPage.getToTextBox().clear();
		busPage.getToTextBox().sendKeys(toPlace);
		String enteredValue = busPage.getToTextBox().getAttribute("value");
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
		Assert.assertEquals(enteredValue, toPlace, "FromTextField data is not entered properly");
		Reporter.log("Data Entered Successfully in toTextField in homePage", true);
	}

	@Test(dataProvider = "data",priority = 3)
	public void searchBus() throws InterruptedException {
		action = new Actions(driver);
		explicitWait.until(ExpectedConditions.visibilityOf(busPage.getSearchBusButton()));
		// date from calender popup.
		int day = ReadData.dayFromSystem(8);
		String month = ReadData.monthFromSystem(0);
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
	}

	@Test(dataProvider = "data",priority = 4)
	public void searchButton() {
		explicitWait.until(ExpectedConditions.visibilityOf(busPage.getSearchBusButton()));
		busPage.getSearchBusButton().click();
	}

	@Test(dataProvider = "data",priority = 5)
	public void selectSeat() {
		explicitWait.until(ExpectedConditions.visibilityOf(busResultPage.getNextDayLink()));
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(ReadData.fromPropertyFile("actualBusResultsTitle"), expectedTitle,
				"Bus Result Page Is not Displayed");
		Reporter.log("Landed Succesfully to the Bus Result Page of the application", true);
		busResultPage.getViewSeats().click();
		for (;;) {
			try {
				explicitWait.until(ExpectedConditions.elementToBeClickable(busResultPage.getAvailableSeat()));
				busResultPage.getAvailableSeat().click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,250)");
			}
		}
		js.executeScript("window.scrollBy(0,300)");
		/*String enteredSeatValue = busResultPage.getAvailableSeat().getAttribute("value");
		Assert.assertEquals(enteredSeatValue, busResultPage.getAvailableSeat(),"Select a valid seat" );
		Reporter.log("Selected the seat",true);*/
		Select select = new Select(busResultPage.getBoardingPoint());
		select.selectByIndex(5);
		Reporter.log("Selected the boarding point",true);
		busResultPage.getProceedWithSeatsButton().click();
	}

	@Test(dataProvider = "data",priority = 6)
	public void userDetails() {
		Select select = new Select(confirmpage.getTitleDropdown());
		select.selectByIndex(1);
		confirmpage.getAdultFirstName().sendKeys(ReadData.fromExcel("Naveen", 20, 4));
		confirmpage.getAdultAge().sendKeys(ReadData.fromExcel("Naveen", 20, 5));
		confirmpage.getMobileNumber().sendKeys(ReadData.fromExcel("Naveen", 20, 6));
		confirmpage.getEmailId().sendKeys(ReadData.fromExcel("Naveen", 20, 7));
		confirmpage.getTermsCheckBox().click();
		confirmpage.getProceedToBookingButton().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getConfirmProceedbutton()));
		confirmpage.getConfirmProceedbutton().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getPayNowButton()));
		String paymentType = ReadData.fromExcel("Naveen", 20, 8);
		if (paymentType.equalsIgnoreCase("netbanking")) {
			confirmpage.getNetBankingPayment().click();
		} else if (paymentType.equalsIgnoreCase("creditcard")) {
			confirmpage.getCreditCardPayment().click();
		} else if (paymentType.equalsIgnoreCase("debitcard")) {
			confirmpage.getDebitCardPayment().click();
		} else if (paymentType.equalsIgnoreCase("wallet")) {
			confirmpage.getWalletPayment().click();
		} else if (paymentType.equalsIgnoreCase("UPI")) {
			confirmpage.getUpiPayment().click();
		}
		confirmpage.getPayNowButton().click();
	}

}