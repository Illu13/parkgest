package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

	public static void crearFactura(Cliente c) {
		PdfWriter writer;
		Document document;
		Date fechaActual = new Date();
		double diferenciaHoras = 0;

		// Definir formato
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = formatter.format(fechaActual);

		
		List<Vehiculo> lv = c.getVehiculos();
		List<RegistroParking> lrp = new ArrayList<RegistroParking>();
		
		for(Vehiculo v : lv) {
			List<RegistroParking> rp = v.getRegistroParkings();
			lrp.addAll(rp);
		}
		
		for (RegistroParking rp : lrp) {
			System.out.println(rp.getVehiculo());
		}
		

//		 Formatear fecha
		try {
			writer = new PdfWriter("facturas/" + c.getNombre() + ".pdf");
			PdfDocument pdf = new PdfDocument(writer);
			document = new Document(pdf);

			// Encabezado
			Paragraph encabezado = new Paragraph("FACTURA de " + c.getNombre()).setTextAlignment(TextAlignment.CENTER)
					.setFontSize(20);
			document.add(encabezado);
			document.add(new Paragraph("Parking Comares"));
			document.add(new Paragraph("Cliente: " + c.getNombre()));
			document.add(new Paragraph("Fecha: " + fechaFormateada));
			float[] columnWidths = { 3, 1, 1, 1 };
			Table tabla = new Table(columnWidths);
			tabla.addHeaderCell("Matrícula");
			tabla.addHeaderCell("Horas");
			tabla.addHeaderCell("Precio por hora");
			tabla.addHeaderCell("Total");
			

			for(RegistroParking rp : lrp) {
		        long diferenciaMs = rp.getHoraSalida().getTime() - rp.getHoraEntrada().getTime();
		        diferenciaHoras += (double) diferenciaMs / (3600 * 1000);
			}
			
	        DecimalFormat df = new DecimalFormat("#.##");
	        	        String horasFormateadas = df.format(diferenciaHoras);
			
			float precioClienteHora = c.getTarifa().getPreciohora();
			double precioHora = diferenciaHoras * precioClienteHora;
			double iva = precioHora * 0.16;
	        String precioTotalFormateado = df.format(precioHora);
	        String ivaFormateado = df.format(iva);
	        String precioConImpuestos = df.format(precioHora + iva);

			
			tabla.addCell("Horas parking");
			tabla.addCell("" + horasFormateadas);
			tabla.addCell("" + precioClienteHora + "€");
			tabla.addCell("" + precioTotalFormateado  + "€");

			document.add(tabla);

			// Totales
			document.add(new Paragraph("\nSubtotal: " + precioTotalFormateado  + "€").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("IVA (16%): " + ivaFormateado + "€").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("Total: " + precioConImpuestos  + "€").setTextAlignment(TextAlignment.RIGHT));

			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
