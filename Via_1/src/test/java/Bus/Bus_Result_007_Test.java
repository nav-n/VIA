package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Generic_Libraries.BaseClass;

public class Bus_Result_007_Test extends BaseClass{
	
	@Test(priority = 5)
	public void selectSeat() {
		explicitWait.until(ExpectedConditions.visibilityOf(busResultPage.getNextDayLink()));
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
		Select select = new Select(busResultPage.getBoardingPoint());
		select.selectByIndex(5);
		busResultPage.getProceedWithSeatsButton().click();
	}
}