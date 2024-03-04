
import java.util.Scanner;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class ClienteNoticias {
	
	//Para la creacion del mensaje
	static OMFactory fact = OMAbstractFactory.getOMFactory();
	static OMNamespace namesp = fact.createOMNamespace("http://Noticia", "ns1");
	
	public static void main(String [] args) throws AxisFault {
		
		
		System.out.println("\n\n  *****************************************************");
		System.out.println("  ********* BIENVENIDO AL CLIENTE DE NOTICIAS *********");
		System.out.println("  *****************************************************");
		
		
		//Creamos el cliente, los metadatos y los elementos para la creacion de los mensajes
		ServiceClient cliente = new ServiceClient();
	        Options opc = new Options();
	        OMElement metodo;
	        OMElement parametro;
		
	        //Creamos el bucle para el menu principal del servicio
	 	boolean continua = true;
		while (continua) {
			
			System.out.println("\n");
			System.out.println("1) Establecer titular.");
			System.out.println("2) Obtener titular.");
			System.out.println("3) Establecer descripcion.");
			System.out.println("4) Obtener descripcion.");
			System.out.println("5) Establecer URL.");
			System.out.println("6) Obtener URL.");
			System.out.println("7) Salir del cliente.");
			
			Scanner opcion = new Scanner(System.in);
			int eleccion = opcion.nextInt();
			
			Scanner entrada;
			
			switch (eleccion) {
			
			case 1: //ESTABLECER TITULAR
				
				//Configuramos los metadatos
				opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
		                opc.setAction("urn:setTitular");
		                cliente.setOptions(opc);
		        
		     
		                //Creamos el mensaje
				metodo = fact.createOMElement("setTitular", namesp);
				parametro = fact.createOMElement("titular", namesp);
				System.out.println("\n Introduce el titular:");
				entrada = new Scanner(System.in);
				parametro.setText(entrada.nextLine());
				metodo.addChild(parametro);
				
				//Enviamos el mensaje
				cliente.sendRobust(metodo);			
				System.out.println("\n Se ha establecido el titular.");
				break;

			case 2: //OBTENER TITULAR
				
				//Configuramos los metadatos
				opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
	              	        opc.setAction("urn:getTitular");
		                cliente.setOptions(opc);
				
				//Creamos el mensaje, lo enviamos y obtenemos la respuesta
				metodo = fact.createOMElement("getTitular", namesp);
				OMElement respuesta_titular = cliente.sendReceive(metodo);

				//Mostramos la respuesta
				System.out.println("Titular: " + respuesta_titular.getFirstElement().getText());
				break;
			
			case 3: //ESTABLECER DESCRIPCION
				
				//Configuramos los metadatos
				opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
	         	        opc.setAction("urn:setDescripcion");
	        	        cliente.setOptions(opc);
				
	        	        //Creamos el mensaje
				metodo = fact.createOMElement("setDescripcion", namesp);
				parametro = fact.createOMElement("descripcion", namesp);
				System.out.println("\n Introduce la descripcion:");
				entrada = new Scanner(System.in);
				parametro.setText(entrada.nextLine());
				metodo.addChild(parametro);
				
				//Enviamos el mensaje
                                cliente.sendRobust(metodo);
				System.out.println("\n Se ha establecido la descripcion.");
				break;
			
			case 4: //OBTENER DESCRIPCION
				
				//Configuramos los metadatos
				opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
		                opc.setAction("urn:getDescripcion");
		                cliente.setOptions(opc);
				
        		        //Creamos el mensaje, lo enviamos y obtenemos la respuesta
				metodo = fact.createOMElement("getDescripcion", namesp);
				OMElement respuesta_descripcion = cliente.sendReceive(metodo);
				
				//Mostramos la respuesta
				System.out.println("Descripcion: " + respuesta_descripcion.getFirstElement().getText());
				break;
			
			case 5: //ESTABLECER URL
				
				//Configuramos los metadatos
				opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
	         	        opc.setAction("urn:setUrl");
	        	        cliente.setOptions(opc);
		        
	        	        //Creamos el mensaje
				metodo = fact.createOMElement("setUrl", namesp);
				parametro = fact.createOMElement("url", namesp);		
				System.out.println("\n Introduce la URL:");
				entrada = new Scanner(System.in);				
				parametro.setText(entrada.nextLine());
				metodo.addChild(parametro);

				//Enviamos el mensaje
				cliente.sendRobust(metodo);
				System.out.println("\n Se ha establecido la url.");				
				break;
			
			case 6: //OBTENER URL
				
				//Configuramos los metadatos
				opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
	         	        opc.setAction("urn:getUrl");
	         	        cliente.setOptions(opc);
				
	        	        //Creamos el mensaje, lo enviamos y obtenemos la respuesta
				metodo = fact.createOMElement("getUrl", namesp);
				OMElement respuesta_url = cliente.sendReceive(metodo);
				
				//Mostramos la respuesta
				System.out.println("Descripcion: " + respuesta_url.getFirstElement().getText());
				break;
			
			case 7:
				
				System.out.println("\n Saliendo del cliente.");
				opcion.close();			
				continua = false;			
				break;
			
			default:
			
				System.out.println("\n Introduce una opción válida.");				
				break;
			
			}
			
		}
		
	}

}
