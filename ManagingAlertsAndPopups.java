package alertspopupsframes;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.*;

public class ManagingAlertsAndPopups {
	WebDriver driver;
	String parentWindowHandle;
	@BeforeMethod
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "test\\resources\\geckodriver.exe");

		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		parentWindowHandle = driver.getWindowHandle();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
	}

////	@Test
	public void managingAlerts() throws InterruptedException {
		driver.get("https://nichethyself.com/tourism/home.html");
		WebElement user = driver.findElement(By.name("username"));
		user.sendKeys("stc123");
	//	driver.findElement(By.name("password")).sendKeys("12345");
		user.submit();
		
		Alert myAlert;
		try {
		myAlert = driver.switchTo().alert();
		String expectedAlertMessage = "Please enter Password";
		String actualAlertMessage = myAlert.getText();
		assertEquals(actualAlertMessage, expectedAlertMessage, "Alert Message Text is not correct !!");
		myAlert.accept(); //Clicks on OK button
		} catch (NoAlertPresentException e) {
			fail("The alert was expected but did not appear.");
		}
		//myAlert.dismiss(); //Clicks on Cancel Button
		driver.findElement(By.name("password")).sendKeys("12345");
		
	}
	
	@Test
	public void test() throws InterruptedException {
		String contactUsWindowHandle;
		driver.get("https://nichethyself.com/tourism/home.html");
		driver.findElement(By.xpath("//button[text()='Contact us!']")).click();
		try {
			driver.switchTo().window("Contact");
			driver.findElement(By.cssSelector("span.glyphicon-search")).click();
			
			try {
				Alert myAlert  = driver.switchTo().alert();
				myAlert.sendKeys("London");
				Thread.sleep(3000);
				myAlert.accept();
				contactUsWindowHandle = driver.getWindowHandle();
			}catch (NoAlertPresentException e) {
				fail("Prompt Alert did not appear.");
			}			
			//driver.close();
			System.out.println("After window close call");
		} catch (NoSuchWindowException e) {
			fail("Contact Us window did not appear.");
		}
		
		driver.switchTo().window(parentWindowHandle);
		//Above
		driver.findElement(By.xpath("//button[text()='Write to us!']")).click();
		
		Set<String> getAllOpenWindowHandles = driver.getWindowHandles();
		System.out.println("No. of open windows " + getAllOpenWindowHandles.size());
		
		for (String window: getAllOpenWindowHandles) {
			if (!window.equals(parentWindowHandle)) {
				try {
				driver.switchTo().window(window);				
				driver.findElement(By.name("name")).sendKeys("Selenium");
				driver.findElement(By.name("mobile")).sendKeys("09234834298");
				Thread.sleep(3000);
				String tagName = driver.findElement(By.name("name")).getTagName();
				driver.close();
				}catch (NoSuchWindowException e) {
					fail("Write to Us window did not exist");
				}
			}
		}
		
		driver.switchTo().window(parentWindowHandle);
		
	}

	@AfterMethod
	public void tearDown() throws Exception {
//		driver.quit();
	}

}
