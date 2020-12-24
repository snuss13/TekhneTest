package Objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumHelper {
	
	public static WebDriver initDriver() {
		WebDriver driver = null;
		
		String systemPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", systemPath + "/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://hc.tekhne.com.ar/ALTEA_SP_DESA_TEST_Ev3/servlet/uswbienvenido");
		
		return driver;
	}

	public static void loadComboTekhne (WebElement control, String value) {
		control.click();
		control.sendKeys(value);
		control.sendKeys(Keys.ENTER);
	}
	
	public static void loadTextBoxTekhne (WebElement control, String value) {
		control.click();
		control.sendKeys(value);
	}
	
	public static void loadDate (WebElement control, LocalDate value) {
		control.click();
		control.sendKeys(value.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
	}
}
