package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	By userSelector = By.cssSelector("#MPW0024vUSUACODUSU");
	By passSelector = By.id("MPW0024vPASSWORD");
	By loginBtnSelector = By.cssSelector("#MPW0024BTN_LOGIN");
	
	public LoginPage (WebDriver driver)
	{
		this.driver = driver;
		
		new WebDriverWait(driver, Duration.ofSeconds(60))
        .until(ExpectedConditions.titleIs("Inicio"));
	}

	public void LoguearUsuarioAdministrador()
	{
		this.IngresarCredenciales("Admin", "Admin");
		
		
		this.PressEnter();
	}
	
	public void IngresarCredenciales(String user, String pass)
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement userAccount = new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.visibilityOfElementLocated(userSelector));
		
		userAccount.sendKeys(user);
	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			driver.findElement(passSelector).sendKeys(pass);
		} catch (ElementNotInteractableException e) {
			new WebDriverWait(driver, Duration.ofSeconds(30))
			        .until(ExpectedConditions.visibilityOfElementLocated(passSelector)).sendKeys(pass);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void PressEnter ()
	{
		driver.findElement(passSelector).sendKeys(Keys.ENTER);
	}
	
	public void ClickLogin()
	{
		driver.findElement(loginBtnSelector).click();
	}
	
	public String getLoginErrorMessage ()
	{
		WebElement loginError = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.visibilityOfElementLocated(By.className("gx-warning-message")));

		return loginError.getText();
	}
}
