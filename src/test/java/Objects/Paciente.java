package Objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
	
	public Paciente()
	{
		this.coberturas = new ArrayList<Cobertura>();
		this.domicilios = new ArrayList<Domicilio>();
		this.telefonos = new ArrayList<Telefono>();
		this.emails = new ArrayList<CorreoElectronico>();
		this.documentos = new ArrayList<Documento>();
		
	}
	
	//Datos del paciente
	public String nombre;
	public String apellido;
	public LocalDate fechaDeNacimiento;
	public String nacionalidad;
	public String provincia;
	public String sexo;
	public String sexoAdopcion;
	public String estadoCivil;
	public String nivelEstudio;
	
	public List<Cobertura> coberturas;
	public List<Documento> documentos;
	public List<Telefono> telefonos;
	public List<Domicilio> domicilios;
	public List<CorreoElectronico> emails;
	
	//
	
}
