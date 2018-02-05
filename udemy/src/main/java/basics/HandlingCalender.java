package basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HandlingCalender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "E:/geckodriver.exe");
	

		driver= new FirefoxDriver();
		driver.get("https://www.google.com/‎");
		driver.manage().window().maximize();
		WebElement datePicker = driver.findElement(By.xpath(".//*[@title='Departing']"));
		List<WebElement> date = datePicker.findElements(By.xpath("\\*[contains(text(),'ui-state-default')])"));
		for (int i = 0; i < date.size(); i++) {
			String dateText = datePicker.findElement(By.xpath("\\*[contains(text(),'ui-state-default')])")).getText();
			if (dateText.equalsIgnoreCase("23")) {
				datePicker.findElement(By.xpath("\\*[contains(text(),'ui-state-default')])")).click();
			}
		}

	}

}
