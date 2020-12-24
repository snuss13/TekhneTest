package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CentersPage {
	WebDriver driver;
	By mainDivTitleLocator = By.cssSelector("#GROUPCENTROS > legend");
	By centerInUse = By.id("vSELECCIONAR_ACTION_0010");

	public CentersPage(WebDriver driver) {
		this.driver = driver;

	}

	public String getCentersPageTitle() {
		  WebElement pageDivTitle = new WebDriverWait(driver, Duration.ofSeconds(10))
				  .until(ExpectedConditions.visibilityOfElementLocated(mainDivTitleLocator));

		return pageDivTitle.getText();
	}
	
	public void selectCenter(int centerID)
	{
		/*
		 * By tdSelector = By.className("ReadonlyAttribute"); List<WebElement> lineas =
		 * this.driver.findElements(tdSelector);
		 */
		
		/* TO DO: poder elegir centro segun el ID - ver manejo de listas */
		
		new WebDriverWait(driver, Duration.ofSeconds(10))
		  .until(ExpectedConditions.elementToBeClickable(centerInUse)).click();
		
	}
}
