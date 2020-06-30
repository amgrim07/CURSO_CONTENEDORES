package co.com.usb.anuncios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "empresa", schema = "public")
public class Empresa {
	
	@NotNull
	private Long empId;
	@NotNull
	@NotEmpty
    @Size(max = 255)
	private String nit;
	@NotNull
	@NotEmpty
    @Size(max = 255)
	private String nombre;
	@NotNull
	@NotEmpty
    @Size(max = 255)
	private String direccion;
	@NotNull
	@NotEmpty
    @Size(max = 255)
	private String telefono;
	@NotNull
	@NotEmpty
    @Size(max = 255)
	private String correo;
	@NotNull
	@NotEmpty
    @Size(max = 1)
	private String activo;
	
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Empresa(Long empId, String nit, String nombre, String direccion, String telefono, String correo,
			String activo) {
		super();
		this.empId = empId;
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.activo = activo;
	}

	@Id
    @Column(name = "EMP_ID", unique = true, nullable = false)
	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	@Column(name = "NIT", nullable = false)
	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Column(name = "telefono", nullable = false)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "correo", nullable = false)
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "activo", nullable = false)
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
}
