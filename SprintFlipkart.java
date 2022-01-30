package SprintIrctc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class SprintFlipkart {
	String baseurl="https://www.irctc.co.in/nget/train-search";
	String baseurl2="https://www.flipkart.com/";
	WebDriver driver; 

	@Test (priority=1)
	public void handlingPopups() throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);

		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Website lauched");
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		System.out.println("PopUps are clicked");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshotFile, new File("./screenshot/"+"scr-"+System.currentTimeMillis()+".png"));
		Thread.sleep(2000);
		String originalTitle = driver.getTitle();
		String expectedTitle = "IRCTC Next Generation eTicketing System";
		Assert.assertEquals( originalTitle, expectedTitle);
		System.out.println("Titles are matched ");
		driver.close();

	}
	@Test (priority=2)
	public void booking() throws InterruptedException, IOException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		Thread.sleep(2000);


		driver.findElement(By.xpath("//input[@class='ng-tns-c58-8 ui-inputtext ui-widget ui-state-default ui-corner-all ui-autocomplete-input ng-star-inserted']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='ERODE JN - ED']")).click();
		System.out.println("From Location Entered");
		driver.findElement(By.cssSelector(".ng-tns-c58-9.ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ui-autocomplete-input.ng-star-inserted")).click();
		driver.findElement(By.xpath("//span[normalize-space()='MGR CHENNAI CTL - MAS']")).click();
		System.out.println("To Location Entered");
		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".ng-tns-c59-10.ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).click();
		driver.findElement(By.cssSelector(".ui-datepicker-next-icon.pi.pi-chevron-right.ng-tns-c59-10")).click();
		driver.findElement(By.xpath("//a[normalize-space()='21']")).click();
		System.out.println("Date selected");
		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".ui-dropdown-trigger-icon.ui-clickable.ng-tns-c66-11.pi.pi-chevron-down")).click();
		driver.findElement(By.xpath("//li[@aria-label='Second Sitting (2S)']")).click();
		System.out.println("Class selected");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".ui-dropdown-trigger-icon.ui-clickable.ng-tns-c66-12.pi.pi-chevron-down")).click();
		driver.findElement(By.xpath("//li[@aria-label='LOWER BERTH/SR.CITIZEN']")).click();
		driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable']")).click();
		System.out.println("Dropdown selected");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[normalize-space()='Flexible With Date']")).click();
		System.out.println("Flexible with date checked");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		System.out.println("Search button clicked");
		File screenshotFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshotFile1, new File("./screenshot/"+"scr-"+System.currentTimeMillis()+".png"));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[class='tbis-div'] div:nth-child(1) div:nth-child(1) app-train-avl-enq:nth-child(1) div:nth-child(1) div:nth-child(5) div:nth-child(1) table:nth-child(1) tr:nth-child(1) td:nth-child(2) div:nth-child(1) div:nth-child(2)")).click();
		driver.findElement(By.xpath("//strong[normalize-space()='AVAILABLE-0031']")).click();
		driver.findElement(By.xpath("//button[@class='btnDefault train_Search ng-star-inserted']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='I Agree']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Yes']")).click();
		System.out.println("Train selected");
		driver.close();

	}  
	@Test(priority=3)
	public void AddCart() throws InterruptedException, IOException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to(baseurl2);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
		FileInputStream fis = new FileInputStream("C:\\Users\\emarella\\eclipse-workspace\\sprintProject\\reg.properties");
		Properties prop = new Properties();
		prop.load(fis);
		driver.findElement(By.cssSelector("input[class='_2IX_2- VJZDxU']")).sendKeys(prop.getProperty("username"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(prop.getProperty("password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']//span[contains(text(),'Login')]")).click();
		System.out.println("User logged in sucessfully");
		File screenshotFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshotFile2, new File("./screenshot/"+"scr-"+System.currentTimeMillis()+".png"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys(prop.getProperty("test"),Keys.ENTER);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(3000);
		driver.navigate().to(prop.getProperty("test1"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='ADD TO CART']")).click();
		System.out.println("Product added to the cart");
		Thread.sleep(3000);
		driver.close();
	}

	@Test(priority=4)
	public void placeOrder() throws IOException, InterruptedException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to(baseurl2);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
		FileInputStream fis = new FileInputStream("C:\\Users\\emarella\\eclipse-workspace\\sprintProject\\reg.properties");
		Properties prop = new Properties();
		prop.load(fis);
		driver.findElement(By.cssSelector("input[class='_2IX_2- VJZDxU']")).sendKeys(prop.getProperty("username"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(prop.getProperty("password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']//span[contains(text(),'Login')]")).click();
		System.out.println("User logged in");
		driver.findElement(By.cssSelector(".KK-o3G")).click();
		System.out.println("Cart icon selected");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Place Order']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("._1P2-0f")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("name");
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("username");
		driver.findElement(By.xpath("//input[@name='landmark']")).sendKeys("Opposite to school");
		driver.findElement(By.xpath("//span[normalize-space()='Work (Delivery between 10 AM - 5 PM)']")).click();
		driver.findElement(By.xpath("//input[@name='alternatePhone']")).sendKeys("8888888888"); 
		driver.findElement(By.xpath("//input[@name='pincode']")).sendKeys("600100");
		driver.findElement(By.cssSelector("input[name='addressLine2']")).sendKeys("Main road");

		driver.findElement(By.xpath("//textarea[@name='addressLine1']")).sendKeys("Navodaya Nagar");
		//driver.findElement(By.xpath("//input[@name='city']")).sendKeys("city");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("._2KpZ6l._8NNVow")).submit();
		System.out.println("Address captured");
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(3000);
		File screenshotFile3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshotFile3, new File("./screenshot/"+"scr-"+System.currentTimeMillis()+".png"));
		driver.findElement(By.xpath("//button[normalize-space()='Deliver Here']")).click();
		System.out.println("Delivery address selected");
		driver.findElement(By.xpath("//button[normalize-space()='CONTINUE']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Accept & Continue']")).click();
		driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
		driver.findElement(By.cssSelector("label[for='UPI_COLLECT'] div[class='_1XFPmK']")).click();
		System.out.println("Mode of payment selected as UPI");
		driver.findElement(By.cssSelector("input[name='upi-id']")).sendKeys("9652768870@apl"); 
		driver.findElement(By.cssSelector("._2-Y9bv")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PAY ₹41,499')]")).click();
		System.out.println("UPI request sent to the mobile");
		File screenshotFile4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshotFile4, new File("./screenshot/"+"scr-"+System.currentTimeMillis()+".png"));
		Thread.sleep(3000);
		System.out.println("Payment screenshot captured succesfully");
		driver.close();
	}
	@AfterMethod
	public void closeBrowser() {
		System.out.println("browser closed");

	}
}
