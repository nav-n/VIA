package Generic_Libraries;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom_repo.BusPage;
import pom_repo.BusResultPage;
import pom_repo.ConfirmBookingPage;
import pom_repo.HomePage;

public class BaseClass {
	public WebDriver driver;
	public Actions action;
	public WebDriverWait explicitWait;
	public HomePage homePage;
	public BusPage busPage;
	public BusResultPage busResultPage;
	public ConfirmBookingPage confirmpage;
	public JavascriptExecutor js;

	@Parameters("browserName")
	
	@BeforeMethod
	public void browserSetUp(@Optional("chrome") String bname) {
		// Step1:Launching a browser
		if (bname.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Reporter.log("Empty Chrome Browser is launched Successfully", true);
		} else if (bname.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			Reporter.log("Empty Edge Browser is launched Successfully", true);
		} else if (bname.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Reporter.log("Empty FireFox Browser is launched Successfully", true);
		} else if (bname.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			Reporter.log("Empty Opera Browser is launched Successfully", true);
		} else {
			throw new InvalidBrowserValueException();
		}
		driver.manage().window().maximize();
		
		Reporter.log("Browser Window is maximized", true);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Step2:Navigating to the url of the application.
		driver.navigate().to(ReadData.fromPropertyFile("url").toString());
		action = new Actions(driver);
		action.click().perform();
		driver.findElement(By.xpath("//button[text()='No thanks!']")).click();
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(ReadData.fromPropertyFile("actualHomePageTitle"), expectedTitle,
				"Home Page Is not Displayed");
		Reporter.log("Landed Succesfully to the Home Page of the application", true);
		
		//Initialisation
		js = (JavascriptExecutor)driver;
		homePage=new HomePage(driver);
		busPage = new BusPage(driver);
		busResultPage = new BusResultPage(driver);
		confirmpage = new ConfirmBookingPage(driver);
		
		//TimeOut
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void browserTermination() {
		driver.quit();
	}

}

class InvalidBrowserValueException extends RuntimeException {
	InvalidBrowserValueException() {
		super("Invalid Browser value is passed");
	}
}

