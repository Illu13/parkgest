package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the registro_parking database table.
 * 
 */
@Entity
@Table(name="registro_parking")
@NamedQuery(name="RegistroParking.findAll", query="SELECT r FROM RegistroParking r")
public class RegistroParking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="hora_entrada")
	private Timestamp horaEntrada;

	@Column(name="hora_salida")
	private Timestamp horaSalida;

	private int pagado;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	public RegistroParking() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getHoraEntrada() {
		return this.horaEntrada;
	}

	public void setHoraEntrada(Timestamp horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Timestamp getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(Timestamp horaSalida) {
		this.horaSalida = horaSalida;
	}

	public int getPagado() {
		return this.pagado;
	}

	public void setPagado(int i) {
		this.pagado = i;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}