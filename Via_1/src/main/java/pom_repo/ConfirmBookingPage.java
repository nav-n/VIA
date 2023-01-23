package pom_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmBookingPage {
	
	public ConfirmBookingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "room1Adult1Title") private WebElement TitleDropdown;
	@FindBy(id = "Adult1FirstName") private WebElement AdultFirstName;
	@FindBy(id = "Adult1Age") private WebElement AdultAge;
	@FindBy(name = "isdCode") private WebElement IsdCode;
	@FindBy(id = "contactMobile") private WebElement mobileNumber;
	@FindBy(id = "contactEmail") private WebElement emailId;
	@FindBy(id = "read_terms_label") private WebElement termsCheckBox;
	@FindBy(xpath = "//button[text()='Proceed to Booking']") private WebElement proceedToBookingButton;
	@FindBy(id = "confirmProceedPayBtn") private WebElement confirmProceedbutton;
	@FindBy(id = "paymentCTA") private WebElement payNowButton;
	@FindBy(xpath = "//div[text()='Please select a valid option / enter a valid card']") private WebElement alertErrorMessage;
	@FindBy(xpath = "//div[@data-paymode='UPI']") private WebElement UpiPayment;
	@FindBy(xpath = "//div[@data-paymode='NB']") private WebElement netBankingPayment;
	@FindBy(xpath = "//div[@data-paymode='WALLET']") private WebElement walletPayment;
	@FindBy(xpath = "//div[@data-paymode='DC']") private WebElement debitCardPayment;
	@FindBy(xpath = "//div[@data-paymode='CC']") private WebElement creditCardPayment;
	
	public WebElement getTitleDropdown() {
		return TitleDropdown;
	}
	public WebElement getAdultFirstName() {
		return AdultFirstName;
	}
	public WebElement getAdultAge() {
		return AdultAge;
	}
	public WebElement getIsdCode() {
		return IsdCode;
	}
	public WebElement getMobileNumber() {
		return mobileNumber;
	}
	public WebElement getEmailId() {
		return emailId;
	}
	public WebElement getTermsCheckBox() {
		return termsCheckBox;
	}
	public WebElement getProceedToBookingButton() {
		return proceedToBookingButton;
	}
	public WebElement getConfirmProceedbutton() {
		return confirmProceedbutton;
	}
	public WebElement getPayNowButton() {
		return payNowButton;
	}
	public WebElement getAlertErrorMessage() {
		return alertErrorMessage;
	}
	public WebElement getUpiPayment() {
		return UpiPayment;
	}
	public WebElement getNetBankingPayment() {
		return netBankingPayment;
	}
	public WebElement getWalletPayment() {
		return walletPayment;
	}
	public WebElement getDebitCardPayment() {
		return debitCardPayment;
	}
	public WebElement getCreditCardPayment() {
		return creditCardPayment;
	}
}
