package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Libraries.BaseClass;
import Generic_Libraries.ReadData;
import pom_repo.ConfirmBookingPage;

public class Bus_Confirmation_008_Test extends BaseClass {

	@DataProvider()
	public String[][] data() {
		String info[][] = ReadData.multipleDataFromExcel("Naveen", "Bus_Confirmation_008_Test");
		return info;
	}

	@Test(dataProvider = "data")
	public void oneWayFromTextField(String data[]) throws InterruptedException {
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
		
		//To Place
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
		
		//calendar
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
		
		//search button
		explicitWait.until(ExpectedConditions.visibilityOf(busPage.getSearchBusButton()));
		busPage.getSearchBusButton().click();
		
		
		//Bus Result
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
		try {
			WebElement droppingPoint = driver.findElement(By.xpath("//h3[text()='Choose Dropping Point']"));
			Select selectDroppingPoint = new Select(busResultPage.getDroppingPoint());
			droppingPoint.click();
			selectDroppingPoint.selectByIndex(3);
		}catch(NoSuchElementException e) {
			
		}
		busResultPage.getProceedWithSeatsButton().click();
		//User Details
		Select selectTitle = new Select(confirmpage.getTitleDropdown());
		if(data[4].equalsIgnoreCase("mr")) {
			selectTitle.selectByIndex(1);
		}else if(data[4].equalsIgnoreCase("mrs")) {
			selectTitle.selectByIndex(2);
		}else if(data[4].equalsIgnoreCase("miss")) {
			selectTitle.selectByIndex(3);
		}
		String actualName = data[5];
		confirmpage.getAdultFirstName().sendKeys(actualName);
		String expectedName = confirmpage.getAdultFirstName().getAttribute("value");
		Assert.assertEquals(actualName, expectedName,"Invalid name");
		Reporter.log("firstname entered correctly");
		String actualAge = data[6];
		confirmpage.getAdultAge().sendKeys(actualAge);
		String expectedAge = confirmpage.getAdultAge().getAttribute("value");
		Assert.assertEquals(actualAge, expectedAge,"Invalid age");
		Reporter.log("Age entered correctly");
		String phoneNumber = data[7];
		confirmpage.getMobileNumber().sendKeys(phoneNumber);
		String expectedphoneNumber = confirmpage.getMobileNumber().getAttribute("value");
		Assert.assertEquals(phoneNumber, expectedphoneNumber,"Invalid phoneNumber");
		Reporter.log("phoneNumber entered correctly");
		String actualEmail = data[8];
		confirmpage.getEmailId().sendKeys(actualEmail);		
		String expectedEmail = confirmpage.getEmailId().getAttribute("value");
		Assert.assertEquals(actualEmail, expectedEmail,"Invalid EmailID");
		Reporter.log("Email entered correctly");
		confirmpage.getTermsCheckBox().click();
		confirmpage.getProceedToBookingButton().click();
		Reporter.log("Clicked on Proceed button");
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getConfirmProceedbutton()));
		confirmpage.getConfirmProceedbutton().click();
		Reporter.log("clicked on confirm button");
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getPayNowButton()));
		String paymentType = data[8];
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
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getPayNowButton()));
		confirmpage.getPayNowButton().click();	
		Reporter.log("clicked on pay now button");
		}

}
