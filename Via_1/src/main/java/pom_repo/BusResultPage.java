package pom_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BusResultPage {

	public BusResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Previous Day")
	private WebElement previousDayLink;
	@FindBy(linkText = "Next Day")
	private WebElement nextDayLink;
	@FindBy(xpath = "//span[text()='Onward ']")
	private WebElement busCount;
	@FindBy(xpath = "(//button[text()=' View Seats '])[1]")
	private WebElement viewSeats;
	@FindBy(xpath = "(//button[text()='Hide Seats'])[1]")
	private WebElement hideseats;
	@FindBy(xpath = "(//button[contains(text(),'Proceed With Seats')])[1]")
	private WebElement proceedWithSeatsButton;
	@FindBy(xpath = "(//i[contains(@data-original-title,'Available For Booking')])[2]")
	private WebElement availableSeat;
	@FindBy(xpath = "//h3[text()='Choose Boarding Point']/..//select[contains(@class,'selectPickup selt-option')]")
	private WebElement boardingPoint;
	@FindBy(xpath = "//select[@class='selectDrop selt-option']")
	private WebElement droppingPoint;
	@FindBy(xpath = "//h3[text()='Choose Dropping Point']")
	private WebElement droppingPointText;

	public WebElement getDroppingPoint() {
		return droppingPoint;
	}

	public WebElement getAvailableSeat() {
		return availableSeat;
	}

	public WebElement getBoardingPoint() {
		return boardingPoint;
	}

	public WebElement getPreviousDayLink() {
		return previousDayLink;
	}

	public WebElement getNextDayLink() {
		return nextDayLink;
	}

	public WebElement getBusCount() {
		return busCount;
	}

	public WebElement getViewSeats() {
		return viewSeats;
	}

	public WebElement getHideseats() {
		return hideseats;
	}

	public WebElement getProceedWithSeatsButton() {
		return proceedWithSeatsButton;
	}

	public WebElement getDroppingPointText() {
		return droppingPointText;
	}
}