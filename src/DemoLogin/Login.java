package DemoLogin;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LoginSettings.Settings;
import LoginSettings.TestClass;


@Listeners(TestClass.class)
public class Login {
	
	static WebDriver driver;
	static String Screenshotpath;
	
	static String sEmail;
	static String sPwd;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("BeforeTest");
		Settings.read();
		
		Screenshotpath =Settings.getScreenshotPath();
		sEmail =Settings.getUserName();
		sPwd =Settings.getPassword();
	}
	//Login page
		@Test
		@Parameters({"sDriverPath", "sDriver" })
		public static void Login(String sDriverPath, String sDriver) throws Exception {
			
			Settings.read();
			String sURL = Settings.getsURL();
			
			
			if(sDriver.equalsIgnoreCase("webdriver.chrome.driver"))
			{
				System.setProperty(sDriver, sDriverPath);
				driver= new ChromeDriver();
			}
			else if(sDriver.equalsIgnoreCase("webdriver.gecko.driver"))
			{
				System.setProperty(sDriver, sDriverPath);
				driver = new FirefoxDriver();
				
			}
			else if(sDriver.equalsIgnoreCase("webdriver.ie.driver"))
			{
			  System.setProperty(sDriver, sDriverPath);
			  driver= new InternetExplorerDriver();
			}
			else
			{
			  System.setProperty(sDriver, sDriverPath);
			  driver= new EdgeDriver();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			//Login Credentials
			driver.get(sURL);
			
		}
		@Test(priority=1)
		public void LoginButton() throws InterruptedException
		{
			//click on login button
			driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
			Thread.sleep(1000);
			
			//verify welcome text
			String data=driver.findElement(By.xpath("//h1[contains(.,'Welcome, Please Sign In!')]")).getText();
			System.out.println(data);
			
			if(data.equals("Welcome, Please Sign In!"))
			{
				System.out.println("welcome verify test is passed");
			}
			else
			{
				System.out.println("welcome verify test is failed");
			}
		}
		@Parameters({"Email", "Pwd" })
		@Test(priority=2)
		public void credentials(String Email, String Pwd) throws InterruptedException
		{
			//click on email text box
			driver.findElement(By.xpath("//input[@id='Email']")).click();
			
			//enter email
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(Email);
			
			//click on pwd text box
			driver.findElement(By.xpath("//input[@id='Password']")).click();
			
			//enter pwd
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(Pwd);
			
			//click on login button
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			Thread.sleep(2000);
		}
		@Test(priority=3)
		public void validateUser()
		{
			//copy value of user id
			String userdata=driver.findElement(By.xpath("//a[contains(text(),'atest@gmail.com')]")).getText();
			System.out.println(userdata);
			
			if(userdata.contains("atest"))
			{
				System.out.println("user is login");
			}
			else
			{
				System.out.println("user is not login");
			}
		}
		@Test(priority=4)
		public void clearCart() throws InterruptedException
		{
			//click on shopping cart 
			driver.findElement(By.xpath("//li[@id='topcartlink']/a/span")).click();
			
			//get qty for cart
			String qty=driver.findElement(By.xpath("//li[@id='topcartlink']/a/span[2]")).getText();
			System.out.println(qty);
			
			//click on chekbox selected product
			driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
			
			//click on qty text box
			driver.findElement(By.xpath("//td[5]/input")).click();
			Thread.sleep(1000);
			
			//remove the value
			WebElement cartvalue=driver.findElement(By.xpath("//td[5]/input"));
			cartvalue.click();
			Thread.sleep(1000);
			cartvalue.clear();
			//cartvalue.sendKeys(Keys.CONTROL, +"a");
			//cartvalue.sendKeys(Keys.DELETE);
			
			//click on update cart
			driver.findElement(By.xpath("//input[@name='updatecart']")).click();
			Thread.sleep(2000);
			
			//verify qty for cart
			if(qty.equals("0"))
			{
				System.out.println("cart is clear");
			}
			else
			{
				System.out.println("cart is not clear");
			}
			
			
		}
		@Test(priority=22)
		public void Logout() throws InterruptedException
		{
			//click on logout
			driver.findElement(By.xpath("//a[contains(@href, '/logout')]")).click();
			Thread.sleep(2000);
		}
		@AfterMethod
		public void tearDown(ITestResult result) {

			final String dir = System.getProperty("user.dir");
			String screenshotPath;
			//System.out.println("dir: " + dir);
			if (!result.getMethod().getMethodName().contains("Logout")) {
				if (ITestResult.FAILURE == result.getStatus()) {
					this.capturescreen(driver, result.getMethod().getMethodName(), "FAILURE");
					Reporter.setCurrentTestResult(result);

					Reporter.log("<br/>Failed to execute method: " + result.getMethod().getMethodName() + "<br/>");
					// Attach screenshot to report log
					screenshotPath = dir + "/" + Screenshotpath + "/ScreenshotsFailure/"
							+ result.getMethod().getMethodName() + ".png";

				} else {
					this.capturescreen(driver, result.getMethod().getMethodName(), "SUCCESS");
					Reporter.setCurrentTestResult(result);

					// Attach screenshot to report log
					screenshotPath = dir + "/" + Screenshotpath + "/ScreenshotsSuccess/"
							+ result.getMethod().getMethodName() + ".png";

				}

				String path = "<img src=\" " + screenshotPath + "\" alt=\"\"\"/\" />";
				// To add it in the report
				Reporter.log("<br/>");
				Reporter.log(path);
				
				
			}

		}

		public void capturescreen(WebDriver driver, String screenShotName, String status) {
			try {
				
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				if (status.equals("FAILURE")) {
					FileHandler.copy(scrFile,
							new File(Screenshotpath + "/ScreenshotsFailure/" + screenShotName + ".png"));
					Reporter.log(Screenshotpath + "/ScreenshotsFailure/" + screenShotName + ".png");
				} else if (status.equals("SUCCESS")) {
					FileHandler.copy(scrFile,
							new File(Screenshotpath + "./ScreenshotsSuccess/" + screenShotName + ".png"));

				}

				System.out.println("Printing screen shot taken for className " + screenShotName);

			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

}
