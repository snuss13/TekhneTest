package StepDefinition;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;


import Objects.SeleniumHelper;
import Pages.CentersPage;
import Pages.LoginPage;
import io.cucumber.java.es.*;


public class LoginSteps {
	WebDriver driver = null;
	LoginPage login;

	@Dado("que el usuario esta en la pagina de login")
	public void que_el_usuario_esta_en_la_pagina_de_login() {
		driver = SeleniumHelper.initDriver();
		
		login = new LoginPage(driver);
		
	}

	@Cuando("ingresa el usuario y la clave")
	public void ingresa_el_usuario_y_la_clave() {
		login.IngresarCredenciales("Admin", "Admin");
	}

	@Cuando("presiona Enter")
	public void presiona_enter() {
		login.PressEnter();
	}

	@Entonces("sera redirigido a la pagina de seleccion de entidad")
	public void sera_redirigido_a_la_pagina_principal() {
				
		CentersPage centerSelection = new CentersPage(driver);
		
		assertEquals("Selección de Centro de Atención", centerSelection.getCentersPageTitle());  
		
		driver.close();
	}

	
// /////////////// Validacion Usuario Erroneo /////////////////////////////////////////////////
	
	@Cuando("ingresa un {string} invalido y una {string} erronea")
	public void ingresa_un_invalido_y_una_erronea(String user, String pass) {
		login.IngresarCredenciales(user, pass);
	}
	
	@Cuando("presiona Ingresar")
	public void presiona_ingresar() {
	    login.ClickLogin();
	}
	
	@Entonces("recibira un error {string}")
	public void recibira_un_error(String errorMsg) {
	    String returnedMsg = login.getLoginErrorMessage();
	    
		assertEquals("Login de usuario incorrecto ha fallado", errorMsg, returnedMsg);
		
		driver.close();
	}
}
