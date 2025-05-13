package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String dni;

	private String email;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to Localidad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="localidadId")
	private Localidad localidad;

	//bi-directional many-to-one association to Tarifa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idtarifa")
	private Tarifa tarifa;

	//bi-directional many-to-many association to Vehiculo
	@ManyToMany
	@JoinTable(
		name="cliente_vehiculo"
		, joinColumns={
			@JoinColumn(name="cliente_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="vehiculo_id")
			}
		)
	private List<Vehiculo> vehiculos;

	public Cliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Tarifa getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

}