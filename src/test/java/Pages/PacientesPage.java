package Pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Objects.*;

public class PacientesPage {
	WebDriver driver;

	// Datos del paciente

	@FindBy(id = "CTLEMPENOMBRE")
	WebElement Name;

	@FindBy(id = "CTLEMPEAPELLIDO")
	WebElement LastName;

	@FindBy(id = "CTLEMPEFECNAC")
	WebElement birthday;

	@FindBy(id = "CTLEMPENACODIGO")
	WebElement nacionality;

	@FindBy(id = "CTLEMPEPCORC")
	WebElement province;

	@FindBy(id = "CTLEMPESEXO")
	WebElement bornSex;

	@FindBy(id = "CTLEMPESEXOLEGAL")
	WebElement optedSex;

	@FindBy(id = "CTLESCICODIGO_ESTADO_CIVIL")
	WebElement estadoCivil;

	@FindBy(id = "CTLEMPENIVEST")
	WebElement nivelDeEstudio;

	// Cobertura

	@FindBy(name = "BTNSINCOBERTURA")
	WebElement btnSinCobertura;

	@FindBy(name = "IMAGE3")
	WebElement btnCobertura;

	// DNI
	@FindBy(name = "IMAGE1")
	WebElement btnDNI;

	// Telefonos
	@FindBy(name = "IMAGE4")
	WebElement btnTelefono;

	// Domicilios
	@FindBy(name = "IMAGE2")
	WebElement btnDomicilio;

	// Email
	@FindBy(name = "IMAGE5")
	WebElement btnEmail;

	// Submit
	@FindBy(name = "CONFIRMAR")
	WebElement btnConfirmar;
		
	public PacientesPage(WebDriver driver) {
		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(factory, this);

	}

	public void IngresarDatosCargaRapida(Paciente paciente) {
		this.LastName.sendKeys(paciente.apellido);
		this.Name.sendKeys(paciente.nombre);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeleniumHelper.loadDate(this.birthday, paciente.fechaDeNacimiento);
				
		SeleniumHelper.loadComboTekhne(this.nacionality, paciente.nacionalidad.toUpperCase());
		SeleniumHelper.loadComboTekhne(this.province, paciente.provincia.toUpperCase());
		SeleniumHelper.loadComboTekhne(this.bornSex, paciente.sexo.toUpperCase());
		SeleniumHelper.loadComboTekhne(this.optedSex, paciente.sexoAdopcion.toUpperCase());
		SeleniumHelper.loadComboTekhne(this.estadoCivil, paciente.estadoCivil.toUpperCase());
		SeleniumHelper.loadComboTekhne(this.nivelDeEstudio, paciente.nivelEstudio.toUpperCase());

		if (paciente.coberturas.isEmpty()) {
			btnSinCobertura.click();
		} else {
			CargarCoberturas(paciente.coberturas);
		}

		CargarDocumentos(paciente.documentos);
		CargarTelefonos(paciente.telefonos);
		CargarDomicilios(paciente.domicilios);
		CargarEmails(paciente.emails);
	}
	
	public void ConfirmarForm()
	{
		btnConfirmar.click();
	}
	
	public void CancelarFoto()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#gxp0_cls")))).click();
	}

	public void AceptarGenerarcionHistorial()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Acceptar crear historial de usuario
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#jsmsgbox_btnOk")))).click();
		
	}
	
	public String ObtenerNumeroHistoriaClinica() {
		
		WebElement HC = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("span_CTLEMPHCNRO"))));
		
		
		  System.out.println(HC.getTagName()); 
		  System.out.println(HC.getText());
		  System.out.println(HC.getAttribute("innerHTML"));
		 
		
		return HC.getAttribute("innerHTML") ;
	}
	
	private void CargarCoberturas(List<Cobertura> coberturas) {

		Iterator<Cobertura> iterator = coberturas.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			i++;
			String selectorIndex = String.format("%04d", i);

			btnCobertura.click();

			Cobertura cobertura = iterator.next();

			WebElement codCobertura = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(By.id("CTLTPACOCODCOD_" + selectorIndex)));

			SeleniumHelper.loadComboTekhne(codCobertura, cobertura.codCobertura);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement txtNumAfiliado = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.cssSelector("#CTLTPACONROAFI_" + selectorIndex))));

			SeleniumHelper.loadTextBoxTekhne(txtNumAfiliado, cobertura.numAfiliado);
			SeleniumHelper.loadComboTekhne(driver.findElement(By.id("CTLTPACOCODPLA_" + selectorIndex)),
					cobertura.plan);
			SeleniumHelper.loadDate(driver.findElement(By.id("CTLTPACOFECALT_" + selectorIndex)), cobertura.fechaAlta);

		}

	}

	private void CargarDocumentos(List<Documento> documentos) {
		Iterator<Documento> iterator = documentos.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			i++;
			String selectorIndex = String.format("%04d", i);

			btnDNI.click();
			
			Documento documento = iterator.next();
			
			WebElement tipoDoc = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(By.id("CTLEMDCTDCODIGO_" + selectorIndex)));

			SeleniumHelper.loadComboTekhne(tipoDoc, documento.tipo);
			
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMDCNUMERO_" + selectorIndex)), documento.numero);
			
		}
	}
	
	private void CargarTelefonos(List<Telefono> telefonos) {
		Iterator<Telefono> iterator = telefonos.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			i++;
			String selectorIndex = String.format("%04d", i);

			btnTelefono.click();
			
			Telefono telefono = iterator.next();
			
			WebElement tipoTel = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(By.id("CTLEMTICOCODIGO_" + selectorIndex)));

			SeleniumHelper.loadComboTekhne(tipoTel, telefono.tipo);
			
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMTENUMCOM_" + selectorIndex)), telefono.numero);
			
			if (telefono.preferencia)
				SeleniumHelper.loadComboTekhne(driver.findElement(By.id("CTLEMTECONPRE_" + selectorIndex)), "Si");
		}
	}
	
	private void CargarDomicilios(List<Domicilio> domicilios) {
		Iterator<Domicilio> iterator = domicilios.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			i++;
			String selectorIndex = String.format("%04d", i);

			btnDomicilio.click();
			
			Domicilio domicilio = iterator.next();
			
			WebElement tipoDomicilio = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(By.id("CTLEMTICOCODIGO1_" + selectorIndex)));

			SeleniumHelper.loadComboTekhne(tipoDomicilio, domicilio.tipo);
			
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMDOCANOMBRE_" + selectorIndex)), domicilio.calle);
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMDONUMERO_" + selectorIndex)), domicilio.numero);
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMDOPISO_" + selectorIndex)), domicilio.piso);
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMDODEPTO_" + selectorIndex)), domicilio.depto);
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMDOCUERPO_" + selectorIndex)), domicilio.cuerpo);
			
			SeleniumHelper.loadComboTekhne(driver.findElement(By.id("CTLEMDOPRCODIGO_" + selectorIndex)), domicilio.provincia);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement ddlLocalidad = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.id("CTLEMDOLOCODIGO_" + selectorIndex))));
			
			SeleniumHelper.loadComboTekhne(ddlLocalidad, domicilio.localidad);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement ddlBarrio = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.id("CTLBARRIO_" + selectorIndex))));
			
			if (domicilio.barrio != null && domicilio.barrio.trim() != "")
				SeleniumHelper.loadComboTekhne(ddlBarrio, domicilio.barrio);
			
			SeleniumHelper.loadComboTekhne(driver.findElement(By.id("CTLEMDOCOPOSTAL_" + selectorIndex)), domicilio.codPostal);
			
		}
	}
	
	private void CargarEmails(List<CorreoElectronico> emails) {
		Iterator<CorreoElectronico> iterator = emails.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			i++;
			String selectorIndex = String.format("%04d", i);

			btnEmail.click();
			
			CorreoElectronico email = iterator.next();
			
			WebElement tipoEmail = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.elementToBeClickable(By.id("CTLEMTICOCODIGO2_" + selectorIndex)));

			SeleniumHelper.loadComboTekhne(tipoEmail, email.tipo);
			
			SeleniumHelper.loadTextBoxTekhne(driver.findElement(By.id("CTLEMEMDIRECCION1_" + selectorIndex)), email.direccion);
		}
	}
}
