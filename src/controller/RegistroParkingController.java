package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import model.Cliente;
import model.RegistroParking;
import model.Vehiculo;

public class RegistroParkingController {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parkgest");

	public static boolean insertar(RegistroParking c) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(c);
			tx.commit(); // Mover commit dentro del try
			return true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback(); // Rollback si la transacción está activa
			}
			return false;
		} finally {
			em.close(); // Cerrar EntityManager siempre
		}
	}
	
	public static boolean update(RegistroParking c) {

		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
			tx.commit(); // Mover commit dentro del try
			return true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback(); // Rollback si la transacción está activa
			}
			return false;
		} finally {
			em.close(); // Cerrar EntityManager siempre
		}
	}
	
	public static RegistroParking encontrarUltimoRegistroVehiculo(Vehiculo v) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery(
			    "SELECT * FROM registro_parking WHERE id_vehiculo = ? ORDER BY hora_entrada DESC", 
			    RegistroParking.class
			);
			q.setParameter(1, v.getId());
			q.setMaxResults(1);
		RegistroParking es;
		try {
		es = (RegistroParking) q.getSingleResult();
	
		} catch (NoResultException e) {
			es = null;
		} finally {
			em.close();
		}
		return es;
	}

	public static void crearFactura(Cliente c) {
		PdfWriter writer;
		Document document;
		Date fechaActual = new Date();
		double diferenciaHoras = 0;

		// Definir formato
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = formatter.format(fechaActual);

		List<Vehiculo> lv = c.getVehiculos();
		Map<Vehiculo, List<RegistroParking>> registrosPorVehiculo = new HashMap<>();

		for (Vehiculo v : lv) {
		    // supongamos que tienes un método en tu DAO que te devuelve
		    // todos los registros de un vehículo concreto
		    List<RegistroParking> regs = v.getRegistroParkings();
		    registrosPorVehiculo.put(v, regs);
		}


		try {
			Date d = new Date();
			writer = new PdfWriter("C:/Users/isaac/Desktop/facturas/" + c.getNombre() + d.getTime() +".pdf");
			PdfDocument pdf = new PdfDocument(writer);
			document = new Document(pdf);

			// Encabezado
			Paragraph encabezado = new Paragraph("FACTURA de " + c.getNombre()).setTextAlignment(TextAlignment.CENTER)
					.setFontSize(20);
			document.add(encabezado);
			document.add(new Paragraph("Parking Comares"));
			document.add(new Paragraph("Cliente: " + c.getNombre()));
			document.add(new Paragraph("DNI del cliente: " + c.getDni()));
			document.add(new Paragraph("Teléfono del cliente: " + c.getTelefono()));
			document.add(new Paragraph("Localidad del cliente: " + c.getLocalidad().getNombre()));
			document.add(new Paragraph("Fecha: " + fechaFormateada));
			document.add(new Paragraph("CIF: A12345678").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("Dirección: Calle Juego de Pelota").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("Teléfono de la empresa: 612 345 678").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("Email: parkingcomares@gmail.com").setTextAlignment(TextAlignment.RIGHT));
			float[] columnWidths = { 3, 1, 1, 1 };
			Table tabla = new Table(columnWidths);
			tabla.addHeaderCell("Matrícula");
			tabla.addHeaderCell("Horas");
			tabla.addHeaderCell("Precio por hora");
			tabla.addHeaderCell("Total");
			DecimalFormat df = new DecimalFormat("#.##");
			float precioClienteHora = c.getTarifa().getPreciohora();
			float precioTotal = 0;
			for (Map.Entry<Vehiculo, List<RegistroParking>> entry : registrosPorVehiculo.entrySet()) {
				diferenciaHoras = 0;
				float precioCocheConcreto = 0;
			    Vehiculo veh = entry.getKey();
			    List<RegistroParking> listaReg = entry.getValue();
			    System.out.println("Vehículo: " + veh.getMatricula() + " tiene " + listaReg.size() + " registros.");
			    for (RegistroParking rp : listaReg) {
					long diferenciaMs = rp.getHoraSalida().getTime() - rp.getHoraEntrada().getTime();
					diferenciaHoras += (double) diferenciaMs / (3600 * 1000);
					diferenciaHoras = Math.round(diferenciaHoras * 100.0) / 100.0;
					precioCocheConcreto = (float) (diferenciaHoras * precioClienteHora);
					System.out.println(diferenciaHoras);
				}
				String horasFormateadas = df.format(diferenciaHoras);
				String precioCocheConcretoString = df.format(precioCocheConcreto);
				tabla.addCell(veh.getMatricula());
				tabla.addCell("" + horasFormateadas);
				tabla.addCell("" + precioClienteHora + "€");
				tabla.addCell("" + precioCocheConcretoString + "€");
			    precioTotal += precioCocheConcreto;
			}

			

			double iva = precioTotal * 0.16;
			String precioTotalFormateado = df.format(precioTotal);
			String ivaFormateado = df.format(iva);
			String precioConImpuestos = df.format(precioTotal + iva);

		

			document.add(tabla);

			// Totales
			document.add(
					new Paragraph("\nSubtotal: " + precioTotalFormateado + "€").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("IVA (16%): " + ivaFormateado + "€").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("Total: " + precioConImpuestos + "€").setTextAlignment(TextAlignment.RIGHT));

			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
