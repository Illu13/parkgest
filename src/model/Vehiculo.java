package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String matricula;

	//bi-directional many-to-many association to Cliente
	@ManyToMany(mappedBy="vehiculos")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to RegistroParking
	@OneToMany(mappedBy="vehiculo")
	private List<RegistroParking> registroParkings;

	public Vehiculo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<RegistroParking> getRegistroParkings() {
		return this.registroParkings;
	}

	public void setRegistroParkings(List<RegistroParking> registroParkings) {
		this.registroParkings = registroParkings;
	}

	public RegistroParking addRegistroParking(RegistroParking registroParking) {
		getRegistroParkings().add(registroParking);
		registroParking.setVehiculo(this);

		return registroParking;
	}

	public RegistroParking removeRegistroParking(RegistroParking registroParking) {
		getRegistroParkings().remove(registroParking);
		registroParking.setVehiculo(null);

		return registroParking;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.matricula;
	}

}