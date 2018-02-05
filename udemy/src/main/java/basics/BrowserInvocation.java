package basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		//driver.close();
		
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
		BI.launchFirfox();
		//BI.launchIE();
		driver.get("http://www.airindia.in/");
		System.out.println(driver.getTitle());
		
		
		WebElement datePicker = driver.findElement(By.id("_depdateeu1"));
		datePicker.click();
		
		List<WebElement> date = driver.findElements(By.xpath(".//a[@class='ui-state-default']"));
		int size = driver.findElements(By.className("ui-state-default")).size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			String dateText = driver.findElements(By.xpath(".//a[@class='ui-state-default']")).get(i).getText();
			System.out.println(dateText);
			if (dateText.equalsIgnoreCase("23")) {
				driver.findElements(By.xpath(".//a[@class='ui-state-default']")).get(i).click();
				break;
			}
		}
		
	}

}
