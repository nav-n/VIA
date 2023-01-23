package pom_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "SignIn")
	private WebElement signInNavBarButton;

	@FindBy(id = "selectedList")
	private WebElement countryDropDown;

	public WebElement getCountryDropDown() {
		return countryDropDown;
	}

	@FindBy(xpath = "//span[text()='SIGN UP']")
	private WebElement signUpButton;

	@FindBy(id = "emailIdSignUp")
	private WebElement emailIdTextBoxSingnUp;

	@FindBy(id = "nameSignUp")
	private WebElement nameTextBox;

	@FindBy(id = "passwordSignUp")
	private WebElement passwordTextBoxSignUp;

	@FindBy(id = "mobileNoSignUp")
	private WebElement phoneTextBox;

	public WebElement getBookButton() {
		return bookButton;
	}

	@FindBy(xpath = "(//button[contains(text(),'Book')])[1]")
	private WebElement bookButton;

	@FindBy(id = "signUpValidate")
	private WebElement createAccountButton;

	@FindBy(id = "loginIdText")
	private WebElement emailIdTextBoxLogin;

	@FindBy(id = "passwordText")
	private WebElement passwordTextBoxLogin;

	@FindBy(id = "loginValidate")
	private WebElement signInButton;

	@FindBy(xpath = "//span[text()='SIGN IN']")
	private WebElement signInLink;

	@FindBy(xpath = "//label[text()='One way']")
	private WebElement oneWayNavBar;

	@FindBy(id = "Hotels")
	private WebElement hotelsNavBar;

	@FindBy(id = "Holidays")
	private WebElement holidaysNavBar;

	@FindBy(id = "Bus")
	private WebElement busNavBar;

	@FindBy(id = "Trains")
	private WebElement trainsNavBar;

	public WebElement getHotelsNavBar() {
		return hotelsNavBar;
	}

	public WebElement getHolidaysNavBar() {
		return holidaysNavBar;
	}

	public WebElement getBusNavBar() {
		return busNavBar;
	}

	public WebElement getTrainsNavBar() {
		return trainsNavBar;
	}

	public WebElement getOneWayNavBar() {
		return oneWayNavBar;
	}

	public WebElement getRoundTripNavBar() {
		return roundTripNavBar;
	}

	public WebElement getMultiCityNavBar() {
		return multiCityNavBar;
	}

	public WebElement getFromTextBox() {
		return fromTextBox;
	}

	@FindBy(xpath = "//div[@id='viaAlert']/div/div")
	private WebElement viaHomePageAlertMessage;

	@FindBy(id = "departure")
	private WebElement departureCalenderTextBox;

	@FindBy(id = "return")
	private WebElement returnCalenderTextBox;

	@FindBy(xpath = "(//div[text()='+'])[1]")
	private WebElement plusIconAdult;

	public WebElement getSearchFlightButton() {
		return searchFlightButton;
	}

	@FindBy(xpath = "(//div[text()='+'])[2]")
	private WebElement plusIconChildren;

	@FindBy(id = "search-flight-btn")
	private WebElement searchFlightButton;

	public WebElement getRightCalenderArrowIcon() {
		return rightCalenderArrowIcon;
	}

	@FindBy(xpath = "(//span[contains(@class,'icon-Rightarrow')])[2]")
	private WebElement rightCalenderArrowIcon;

	@FindBy(xpath = "(//span[contains(@class,'icon-leftarrow')])[1]")
	private WebElement leftCalenderArrowIcon;

	@FindBy(id = "cheap_flight")
	private WebElement cheapflights;

	public WebElement getCheapflights() {
		return cheapflights;
	}

	public WebElement getLeftCalenderArrowIcon() {
		return leftCalenderArrowIcon;
	}

	public WebElement getDepartureCalenderTextBox() {
		return departureCalenderTextBox;
	}

	public WebElement getReturnCalenderTextBox() {
		return returnCalenderTextBox;
	}

	public WebElement getPlusIconAdult() {
		return plusIconAdult;
	}

	public WebElement getPlusIconChildren() {
		return plusIconChildren;
	}

	public WebElement getPlusIconInfants() {
		return plusIconInfants;
	}

	public WebElement getMinusIconAdult() {
		return minusIconAdult;
	}

	public WebElement getMinusIconChildren() {
		return minusIconChildren;
	}

	public WebElement getMinusIconInfants() {
		return minusIconInfants;
	}

	@FindBy(xpath = "(//div[text()='+'])[3]")
	private WebElement plusIconInfants;

	@FindBy(xpath = "(//div[text()='-'])[1]")
	private WebElement minusIconAdult;

	@FindBy(xpath = "(//div[text()='-'])[2]")
	private WebElement minusIconChildren;

	@FindBy(xpath = "(//div[text()='-'])[3]")
	private WebElement minusIconInfants;

	public WebElement getViaHomePageAlertMessage() {
		return viaHomePageAlertMessage;
	}

	public WebElement getToTextBox() {
		return toTextBox;
	}

	@FindBy(xpath = "//label[text()='Round trip']")
	private WebElement roundTripNavBar;

	@FindBy(id = "destination")
	private WebElement toTextBox;

	public WebElement getModifyButton() {
		return modifyButton;
	}

	@FindBy(xpath = "//div[text()='Modify']")
	private WebElement modifyButton;

	@FindBy(xpath = "//label[text()='Multi-city']")
	private WebElement multiCityNavBar;

	@FindBy(id = "source")
	private WebElement fromTextBox;

	public WebElement getSignInNavBarButton() {
		return signInNavBarButton;
	}

	public WebElement getSignUpButton() {
		return signUpButton;
	}

	public WebElement getEmailIdTextBoxSingnUp() {
		return emailIdTextBoxSingnUp;
	}

	public WebElement getNameTextBox() {
		return nameTextBox;
	}

	public WebElement getPasswordTextBoxSignUp() {
		return passwordTextBoxSignUp;
	}

	public WebElement getPhoneTextBox() {
		return phoneTextBox;
	}

	public WebElement getCreateAccountButton() {
		return createAccountButton;
	}

	public WebElement getEmailIdTextBoxLogin() {
		return emailIdTextBoxLogin;
	}

	public WebElement getPasswordTextBoxLogin() {
		return passwordTextBoxLogin;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public WebElement getSignInLink() {
		return signInLink;
	}

}
