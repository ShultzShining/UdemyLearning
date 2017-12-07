package basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserInvocation {
	
	private static WebDriver driver;
	public void launchFirfox(){
		System.setProperty("webdriver.gecko.driver","E:/geckodriver.exe");
		
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.close();
		
	}
	public void launchChrome(){
		System.setProperty("webdriver.chrome.driver","E:/chromedriver.exe");
		
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.close();
		
	}
	public void launchIE(){
		DesiredCapabilities capabilities= DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver","E:/IEDriverServer.exe");
		
		driver= new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.close();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BrowserInvocation BI= new BrowserInvocation();
		//BI.launchChrome();
		//BI.launchFirfox();
		BI.launchIE();

	}

}
