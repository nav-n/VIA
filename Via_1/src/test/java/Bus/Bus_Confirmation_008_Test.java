package Bus;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Generic_Libraries.BaseClass;
import Generic_Libraries.ReadData;
import pom_repo.ConfirmBookingPage;

public class Bus_Confirmation_008_Test extends BaseClass {

	@Test(priority = 6)
	public void userDetails() {
		Select select = new Select(confirmpage.getTitleDropdown());
		select.selectByIndex(1);
		confirmpage.getAdultFirstName().sendKeys(ReadData.fromExcel("Naveen", 25, 1));
		confirmpage.getAdultAge().sendKeys(ReadData.fromExcel("Naveen", 25, 2));
		confirmpage.getMobileNumber().sendKeys(ReadData.fromExcel("Naveen", 25, 3));
		confirmpage.getEmailId().sendKeys(ReadData.fromExcel("Naveen", 25, 4));
		confirmpage.getTermsCheckBox().click();
		confirmpage.getProceedToBookingButton().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getConfirmProceedbutton()));
		confirmpage.getConfirmProceedbutton().click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(confirmpage.getPayNowButton()));
		String paymentType = ReadData.fromExcel("Naveen", 25, 5);
		if (paymentType.equalsIgnoreCase("netbanking")) {
			confirmpage.getNetBankingPayment().click();
		}else if(paymentType.equalsIgnoreCase("creditcard")) {
			confirmpage.getCreditCardPayment().click();
		}else if(paymentType.equalsIgnoreCase("debitcard")) {
			confirmpage.getDebitCardPayment().click();
		}else if(paymentType.equalsIgnoreCase("wallet")) {
			confirmpage.getWalletPayment().click();
		}else if(paymentType.equalsIgnoreCase("UPI")) {
			confirmpage.getUpiPayment().click();
		}
		confirmpage.getPayNowButton().click();
	}

}
