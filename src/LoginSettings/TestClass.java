package LoginSettings;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


public class TestClass implements ITestListener {
	String filePath = "D:\\SCREENSHOTS";
	WebDriver driver;
	@Override
	public void onFinish(ITestContext arg0) {
		
		System.out.println("Method Finished: " + arg0.getName());
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		
		
		System.out.println("Method Started: " + arg0.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		
		
		  try {
			  
			  if (arg0.getMethod().getConstructorOrMethod().getMethod() != null)
				{
				Method testCase = arg0.getMethod().getConstructorOrMethod().getMethod();
				
				
				
				 ITestContext context = arg0.getTestContext();
				
				
				 	
					String comment=(String) arg0.getTestContext().getAttribute("Comment");
					
					
				}
				else
				{
					System.out.println("Testcase id not defined");
				}
		   
			
			} catch (Exception e ) {
				
				e.printStackTrace();
			}
		System.out.println("Method Failed: " + arg0.getName());
	}
	
    public void takeScreenShot(String methodName) {
    	
    	TakesScreenshot screenshot=(TakesScreenshot)driver;
   	 File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name 
           try {
				FileHandler.copy(scrFile, new File(filePath+methodName+".png"));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
   }

	@Override
	public void onTestSkipped(ITestResult arg0) {
		
		
				
				System.out.println("Method Skipped: " + arg0.getName());
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
		
		System.out.println("Test Started for method: " + arg0.getName());
		
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		
		
		  try {
			  
			  if (arg0.getMethod().getConstructorOrMethod().getMethod() != null)
				{
				Method testCase = arg0.getMethod().getConstructorOrMethod().getMethod();
				
				
				
				int status=(int) arg0.getTestContext().getAttribute("Status");
				String comment=(String) arg0.getTestContext().getAttribute("Comment");
				
								
				
				
				}
				else
				{
					System.out.println("No Test Case ID");
				}
		   
			
			} catch (Exception e ) {
				
				//e.printStackTrace();
			}
		System.out.println("Method Success: " + arg0.getName());
		
	}
}

