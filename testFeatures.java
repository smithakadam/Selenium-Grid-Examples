package anzBankTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testFeatures {

	public static WebDriver driver;

	@Test
	public void testFeaBen() throws InterruptedException, MalformedURLException {

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprof = profile.getProfile("myProfile");

		System.setProperty("webdriver.gecko.driver", "C:/Selenium/geckodriver-v0.13.0-win64/geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "C:/Selenium/chromedriver_win32/chromedriver.exe");

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability(FirefoxDriver.BINARY,
				new File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe").getAbsolutePath());

		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver(new URL(" http://localhost:5566/wd/hub"), capability);

		driver = new FirefoxDriver(myprof);
		testfeatures();
		
		//For Chrome -testing Grid
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver_win32/chromedriver.exe");

		DesiredCapabilities capability1 = DesiredCapabilities.chrome();
		//capability1.setCapability(ChromeDriver, new File ("C:/Program Files (x86)/Mozilla Firefox/firefox.exe").getAbsolutePath());

		capability1.setBrowserName("chrome");

		driver = new RemoteWebDriver(new URL(" http://localhost:5568/wd/hub"), capability1);
		testfeatures();
		
		
		//For IE- testing Grid
		
		System.setProperty("webdriver.ie.driver", "C:/Selenium/IEDriverServer_x64_3.0.0/IEDriverServer.exe");
		DesiredCapabilities capability2 = DesiredCapabilities.internetExplorer();
		capability2.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capability2.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);


		//capability2.setBrowserName("Internet Explorer");
		
		driver = new RemoteWebDriver(new URL(" http://localhost:5570/wd/hub"), capability2);
		testfeatures();

	}

	public void testfeatures() throws InterruptedException {
		driver.manage().window().maximize();

		driver.get("http://www.anz.com/personal/ways-bank/internet-banking/");
		Thread.sleep(3000);
		driver.findElement(By.linkText("Log on to ANZ Internet Banking")).click();

		System.out.println("frame count is : " + driver.findElements(By.tagName("iframe")).size());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.anz.com/INETBANK/login.asp");

		driver.findElement(By.xpath(".//*[@id='crn']")).sendKeys("olfr56");
		driver.findElement(By.xpath(".//*[@id='Password']")).sendKeys("hufnin");
		WebDriverWait ww = new WebDriverWait(driver, 20);
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SignonButton']"))).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();

		driver.findElement(By.linkText("Need help logging on?")).click();
		driver.switchTo().defaultContent();
		//driver.findElement((By.id("txtSiteSearch"))).sendKeys("internet");

		// driver.quit();

	}

}
