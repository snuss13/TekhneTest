package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu {
	WebDriver driver;

	public Menu(WebDriver driver) {
		this.driver = driver;
	}

	public void irAdministracionPacientes() {
		By pacientesLocator = By.cssSelector(
				"#MPW0071MENU > table > tbody > tr:nth-child(23) > td > table > tbody > tr > td:nth-child(2)");
		By pacienteSubMenu = By.cssSelector(
				"#tUSMenu_P_00006 > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(2)");
		By pacientesAdministracion = By.cssSelector(
				"#tUSMenu_S_00006_THPACIENTE > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(2)");

		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.elementToBeClickable(pacientesLocator)).click();

		driver.findElement(pacienteSubMenu).click();

		 new WebDriverWait(driver, Duration.ofSeconds(30))
		  .until(ExpectedConditions.elementToBeClickable(pacientesAdministracion)).
		  click();
		 
	}

	public boolean isMenuVisible() {
		/*
		 * int intents = 0;
		 * 
		 * while (intents < 5) { try { if (this.isMenuLoaded()) return true; } catch
		 * (TimeoutException ex) { String errorMsg =
		 * this.driver.findElement(By.cssSelector("body h1")).getText();
		 * 
		 * if (errorMsg.equals("Service Unavailable")) driver.navigate().refresh(); else
		 * throw (ex); } intents++; } return false; }
		 * 
		 * private boolean isMenuLoaded() {
		 */

		By menuLocator = By.id("MPW0071SECTION_MENU_HEADER");

		WebElement menu = new WebDriverWait(this.driver, Duration.ofSeconds(60))
				.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));

		return menu.isDisplayed();
	}
}
