package StepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Objects.Cobertura;
import Objects.CorreoElectronico;
import Objects.Documento;
import Objects.Domicilio;
import Objects.Paciente;
import Objects.SeleniumHelper;
import Objects.Telefono;
import Pages.CentersPage;
import Pages.LoginPage;
import Pages.Menu;
import Pages.PacientesListaPage;
import Pages.PacientesPage;
import io.cucumber.java.es.*;
import junit.framework.Assert;

public class AltaPacienteSteps {
	WebDriver driver = null;
	PacientesPage pacientesForm;

	@Dado("un  usuario correctamente logueado")
	public void un_usuario_correctamente_logueado() {
		driver = SeleniumHelper.initDriver();

		LoginPage login = new LoginPage(driver);
		login.LoguearUsuarioAdministrador();
	}

	@Dado("que selecciono centro medico {int}")
	public void que_selecciono_centro_medico(Integer centroMedicoCode) {

		CentersPage center = new CentersPage(driver);
		center.selectCenter(centroMedicoCode);
	}

	@Dado("va a la pantalla de carga rapida de paciente")
	public void va_a_la_pantalla_de_carga_rapida_de_paciente() {
		Menu menu = new Menu(driver);

		menu.isMenuVisible();

		menu.irAdministracionPacientes();

		new PacientesListaPage(this.driver).NuevoPacienteCargaRapida();
	}

	@Cuando("completo los datos del formulario")
	public void completo_los_datos_del_formulario() {
		pacientesForm = new PacientesPage(driver);
		
		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss"));
		
		Paciente paciente = new Paciente();

		paciente.nombre = "Pac Test";
		paciente.apellido = "Test_" + timeStamp;

		paciente.fechaDeNacimiento = LocalDate.of(1981, 02, 15);
		paciente.nacionalidad = "Argentina";
		paciente.provincia = "Rio Negro";
		paciente.sexo = "INDISTINTO";
		paciente.sexoAdopcion = "MASCULINO";
		paciente.estadoCivil = "CASADO";
		paciente.nivelEstudio = "SECUNDARIO";

		// Cobrtura
		Cobertura cob = new Cobertura();
		cob.codCobertura = "IPROSS";
		cob.numAfiliado = timeStamp;
		cob.plan = "UNICO";
		cob.fechaAlta = LocalDate.now();

		paciente.coberturas.add(cob);

		// Documento de Identidad
		Documento doc = new Documento();
		doc.tipo = "DNI";
		doc.numero = timeStamp;

		paciente.documentos.add(doc);

		// telefonos
		Telefono tel = new Telefono();

		tel.tipo = "PERSONAL";
		tel.numero = "1544446666";
		// tel.horaDesde = "9:00";
		// tel.horaHasta = "18:00"; confirmar formato
		tel.preferencia = true;

		paciente.telefonos.add(tel);

		// Domicilio
		Domicilio domicilio = new Domicilio();

		domicilio.tipo = "FAMILIAR";
		domicilio.calle = "Calle " + timeStamp;
		domicilio.numero = "1234";
		domicilio.piso = "1";
		domicilio.depto = "B";
		domicilio.cuerpo = "4";
		domicilio.provincia = "BUENOS AIRES";
		domicilio.localidad = "SAN ISIDRO";
		// domicilio.barrio = "";
		domicilio.codPostal = "1642";

		paciente.domicilios.add(domicilio);

		// email
		CorreoElectronico email = new CorreoElectronico();
		email.tipo = "PERSONAL";
		email.direccion = timeStamp + "s@ttttt.com";

		paciente.emails.add(email);

		pacientesForm.IngresarDatosCargaRapida(paciente);
		
		//Confirmar alta
		pacientesForm.ConfirmarForm();
				
		pacientesForm.AceptarGenerarcionHistorial();
		
		String historiaClinicaNum = pacientesForm.ObtenerNumeroHistoriaClinica();

				
		//Confirmar alta
		pacientesForm.ConfirmarForm();
		
		//Cancelar foto
		pacientesForm.CancelarFoto();
		
		 //Validar registro span_EMPEAPELLI_0001
		
		
	}

	@Entonces("se debe generar un nuevo numero de Historia Clinica")
	public void se_debe_generar_un_nuevo_numero_de_historia_clinica() {
		String HC_Grilla = new PacientesListaPage(driver).GetGridValue(By.id("span_EMPENROHC_0001"));
		System.out.println(HC_Grilla);
		
		assertNotEquals("Historis Clinica No debe ser vacio", "", HC_Grilla);
	}

}
