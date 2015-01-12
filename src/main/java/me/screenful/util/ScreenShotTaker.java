package me.screenful.util;



import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



public class ScreenShotTaker {
	
	// to run on heroku add SCREENSHOT_URL variable in heroku config
		private static String url = System.getenv("SCREENSHOT_URL");	
	private static String emailID = System.getenv("SCREENSHOT_EMAIL");
	
	
public static void main(String[] args) throws IOException {
		
		//WebDriver driver = new FirefoxDriver();
	
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		//caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"C:\\phantomjs-1.9.8-windows\\phantomjs.exe");
		WebDriver driver = new PhantomJSDriver(caps);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	
		// Command line argument
		url = args[0];
		
		driver.get(url); 
		System.out.println(url);
		File srcFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(srcFile, new File(
				"snap1.png"), true);
		driver.close();
		
		// Command line argument
		emailID = args[1];
		System.out.println("args[0] value is" + emailID);
		
		//PostMarkEmailer.PostmarkEmail(emailID);
		EmailSender.SendEmail(emailID);
		
		
		//AttachmentSender.sendFromGMail(emailID);
	}
}

