package Bus;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

import Generic_Libraries.*;
import pom_repo.HomePage;

public class Bus_Oneway_005_Test extends BaseClass {
	public class Bus_Oneway_004Test extends BaseClass {
		@DataProvider()
		public String[][] data() {
			String info[][] = ReadData.multipleDataFromExcel("Naveen", "Bus_Oneway_005_Test");
			return info;
		}

		@Test(dataProvider = "data",groups = "functionality")
		public void searchFlights(String data[]) throws InterruptedException {
			explicitWait.until(ExpectedConditions.elementToBeClickable(homePage.getBusNavBar()));
			homePage.getBusNavBar().click();
			explicitWait.until(ExpectedConditions.elementToBeClickable(busPage.getSearchBusButton()));
			action = new Actions(driver);
			explicitWait.until(ExpectedConditions.visibilityOf(busPage.getSearchBusButton()));
			// date from calender popup.
			String date = data[1];
			int day = ReadData.readDayFromExcel(date);
			String month = ReadData.readMonthFromExcel(date);
			homePage.getDepartureCalenderTextBox().click();
			System.out.println(day + "-" + month);
			for (;;) {
				try {
					WebElement calendar = driver.findElement(
							By.xpath("//span[text()='" + month + "']/../../..//div[text()='" + day + "']"));
					explicitWait.until(ExpectedConditions.elementToBeClickable(calendar));
					// calendar.click();
					action.moveToElement(calendar).doubleClick().perform();
					break;
				} catch (Exception e) {
					homePage.getRightCalenderArrowIcon().click();
				}
			}
		}

	}
}