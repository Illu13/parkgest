package plateRecognizer;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.RegistroParkingController;
import controller.VehiculoController;
import model.RegistroParking;
import model.Vehiculo;

public class PlateRecognizer {

	private static final String API_KEY = "cc84f24c0e0db98f5479adf11ca9e8915e714a76";
	private static final String API_URL = "https://api.platerecognizer.com/v1/plate-reader/";

	public static void main(String[] args) {

		try {
			File carpeta = new File("fotosEntrada");
			File carpetaSalidas = new File("fotosSalida");

			while (true) {
				System.out.println("Revisando carpetas.");

				File[] entradas = carpeta.listFiles();
				File [] salidas = carpetaSalidas.listFiles();

				for (int i = 0; i < entradas.length; i++) {
					System.out.println("Revisando carpetas de entrada");
					File image = entradas[i];
					CloseableHttpClient httpClient = HttpClients.createDefault();
					HttpPost uploadFile = new HttpPost(API_URL);
					uploadFile.setHeader("Authorization", "Token " + API_KEY);

					MultipartEntityBuilder builder = MultipartEntityBuilder.create();
					builder.addPart("upload", new FileBody(image));
					HttpEntity multipart = builder.build();
					uploadFile.setEntity(multipart);

					CloseableHttpResponse response;
					response = httpClient.execute(uploadFile);
					HttpEntity responseEntity = response.getEntity();
					ObjectMapper mapper = new ObjectMapper();
					JsonNode jsonNode = mapper.readTree(responseEntity.getContent());
					System.out.println("Respuesta JSON: " + jsonNode.toString());
					JsonNode res = jsonNode.get("results");
					String matricula =  res.get(0).get("plate").toString();
					String matriculaProcesada = matricula.replaceAll("\"", "");
					String matriculaMayus = matriculaProcesada.toUpperCase();
					System.out.println("Resultados" + matriculaMayus);
					RegistroParking rp = new RegistroParking();
					rp.setPagado(0);
					Vehiculo v = VehiculoController.findVehiculoByMatricula(matriculaMayus);
					if (v != null) {
						rp.setVehiculo(v);
					} else {
						Vehiculo veh = new Vehiculo();
						veh.setMatricula(matriculaMayus);
						VehiculoController.insertar(veh);
						rp.setVehiculo(veh);
					}
					rp.setHoraEntrada(new Timestamp(new Date().getTime()));
					RegistroParkingController.insertar(rp);
					image.delete();
					response.close();
					httpClient.close();
				}
				
				for (int i = 0; i < salidas.length; i++) {
					System.out.println("Revisando carpetas de salida");
					File image = salidas[i];
					CloseableHttpClient httpClient = HttpClients.createDefault();
					HttpPost uploadFile = new HttpPost(API_URL);
					uploadFile.setHeader("Authorization", "Token " + API_KEY);

					MultipartEntityBuilder builder = MultipartEntityBuilder.create();
					builder.addPart("upload", new FileBody(image));
					HttpEntity multipart = builder.build();
					uploadFile.setEntity(multipart);

					CloseableHttpResponse response;
					response = httpClient.execute(uploadFile);
					HttpEntity responseEntity = response.getEntity();
					ObjectMapper mapper = new ObjectMapper();
					JsonNode jsonNode = mapper.readTree(responseEntity.getContent());
					System.out.println("Respuesta JSON: " + jsonNode.toString());
					JsonNode res = jsonNode.get("results");
					String matricula =  res.get(0).get("plate").toString();
					String matriculaProcesada = matricula.replaceAll("\"", "");
					String matriculaMayus = matriculaProcesada.toUpperCase();
					System.out.println("Resultados" + matriculaMayus);
					Vehiculo v = VehiculoController.findVehiculoByMatricula(matriculaMayus);
					RegistroParking rp = RegistroParkingController.encontrarUltimoRegistroVehiculo(v);
					rp.setPagado(1);
					rp.setHoraSalida(new Timestamp(new Date().getTime()));
					RegistroParkingController.update(rp);
					image.delete();
					response.close();
					httpClient.close();
				}


				Thread.sleep(1000);

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
