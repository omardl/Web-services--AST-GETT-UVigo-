
import java.util.Scanner;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class Cliente_servicio_3 {
	
	//Para la cracion de los mensajes
	static OMFactory fact = OMAbstractFactory.getOMFactory();
	static OMNamespace namesp = fact.createOMNamespace("http://TercerServicio", "tns");

	public static void main(String [] args) throws AxisFault {
		
		System.out.println("\n\n  ****************************************************************");
		System.out.println("  ****************************************************************");
		System.out.println("  **** BIENVENIDO AL CLIENTE DEL SERVICIO 3: SERVICIO MUSICAL ****");
		System.out.println("  ****************************************************************");
		System.out.println("  ****************************************************************");
		
		System.out.println(" \n\n Para acotar los resultados de busqueda, cualquier resultado de canciones, artistas o álbumes mostrará, como máximo, 50 resultados.");
		System.out.println(" Se mostrarán, si el número de resultados excede los 50, los más escuchados.");
		

		//Creamos el cliente para el servicio, los metadatos con el Endpoint
		ServiceClient cliente = new ServiceClient();
	        Options opc = new Options();
		opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/ServicioMusical"));
		
		
		//Creamos el bucle del menu principal 
		boolean continua_bucle = true;
		while(continua_bucle) {
				
			System.out.println("\n\n 1) Buscar canciones por titulo.");
			System.out.println(" 2) Buscar canciones por album.");
			System.out.println(" 3) Buscar canciones por artista.");
			System.out.println(" 4) Salir del cliente.");
			
			Scanner entrada = new Scanner(System.in);
			Scanner opcion = new Scanner(System.in);
			int eleccion = opcion.nextInt();
			
			switch(eleccion) {
			
			case 1:
			
				//Llamamos a la funcion setCancion
				OMElement metodo1 = fact.createOMElement("setCancion", namesp);
				OMElement parametro1 = fact.createOMElement("can", namesp);
				opc.setAction("urn:setCancion");
		                cliente.setOptions(opc);
				
				System.out.println("\n Introduce el titulo de la cancion:");
		
				parametro1.setText(entrada.nextLine());
				metodo1.addChild(parametro1);
				cliente.sendRobust(metodo1);
				
				
				
				//Llamamos a la funcion getBusqueda				
				OMElement metodo2 = fact.createOMElement("getBusqueda", namesp);
				OMElement parametro2 = fact.createOMElement("tipo_busqueda", namesp);
				opc.setAction("urn:getBusqueda");
		                cliente.setOptions(opc);
				parametro2.setText("1");
				metodo2.addChild(parametro2);			
				OMElement respuesta1 = cliente.sendReceive(metodo2);

				System.out.println(respuesta1.getFirstElement().getText());
				
				break;
			
			case 2:
				
				//Llamamos a la funcion setAlbum
				OMElement metodo3 = fact.createOMElement("setAlbum", namesp);
				OMElement parametro3 = fact.createOMElement("album", namesp);
				opc.setAction("urn:setAlbum");
		                cliente.setOptions(opc);
				
				System.out.println("\n Introduce el titulo del album:");
					
				parametro3.setText(entrada.nextLine());
				metodo3.addChild(parametro3);
				cliente.sendRobust(metodo3);
				
				
				//Llamamos a la funcion getBusqueda
				OMElement metodo4 = fact.createOMElement("getBusqueda", namesp);
				OMElement parametro4 = fact.createOMElement("tipo_busqueda", namesp);
				opc.setAction("urn:getBusqueda");
		                cliente.setOptions(opc);
				parametro4.setText("2");
				metodo4.addChild(parametro4);
				OMElement respuesta2 = cliente.sendReceive(metodo4);

				System.out.println(respuesta2.getFirstElement().getText());
				
				break;
			
			case 3:
				
				//Llamamos a la funcion setArtista
				OMElement metodo5 = fact.createOMElement("setArtista", namesp);
				OMElement parametro5 = fact.createOMElement("artista", namesp);
				opc.setAction("urn:setArtista");
		                cliente.setOptions(opc);
				
				System.out.println("\n Introduce el titulo de la cancion:");
		
				parametro5.setText(entrada.nextLine());
				metodo5.addChild(parametro5);
				cliente.sendRobust(metodo5);
				
				
				//Llamamos a la funcion getBusqueda
				OMElement metodo6 = fact.createOMElement("getBusqueda", namesp);
				OMElement parametro6 = fact.createOMElement("tipo_busqueda", namesp);
				opc.setAction("urn:getBusqueda");
		                cliente.setOptions(opc);
				parametro6.setText("3");
				metodo6.addChild(parametro6);
				OMElement respuesta3 = cliente.sendReceive(metodo6);

				System.out.println(respuesta3.getFirstElement().getText());
				break;
				
			case 4:
			
				System.out.println(" ...saliendo del cliente...");
				continua_bucle = false;
				opcion.close();
				entrada.close();
				break;
			
			default:
				
				System.out.println("Introduce una opcion correcta");
				break;
			
			}      
			
		}
		
		return;
		
	}
	
}
