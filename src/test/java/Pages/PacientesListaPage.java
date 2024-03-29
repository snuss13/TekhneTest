package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PacientesListaPage {
	WebDriver driver;

	// btnCargaRapida
	@FindBy(name = "CARGARAPIDA")
	WebElement btnCargaRapida;

	public PacientesListaPage(WebDriver driver) {
		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(factory, this);

	}

	public void NuevoPacienteCargaRapida() {
		btnCargaRapida.click();
	}

	public String GetGridValue(By element) {
		return driver.findElement(element).getText();
	}

}
