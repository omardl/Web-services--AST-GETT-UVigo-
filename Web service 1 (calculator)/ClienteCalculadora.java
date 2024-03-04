
import java.util.Scanner;
import javax.xml.namespace.QName;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class ClienteCalculadora {

	public static void main(String [] args) throws AxisFault {
		
		//Creamos un cliente RPC y referenciamos el Endpoint
		RPCServiceClient cliente = new RPCServiceClient();
		cliente.getOptions().setTo(new EndpointReference("http://localhost:8080/axis2/services/ServicioCalculadora"));
		
		
		System.out.println("\n\n  ***************************************************");
		System.out.println("  ******** BIENVENIDO AL CLIENTE CALCULADORA ********");
		System.out.println("  ***************************************************");
		
		
		//Creamos un bucle para el menu principal
		boolean continua = true;
		
		while (continua) {
			
			System.out.println("\n");
			System.out.println("1) Sumar.");
			System.out.println("2) Restar.");
			System.out.println("3) Multiplicar.");
			System.out.println("4) Dividir.");
			System.out.println("5) Salir del cliente. \n ");
			
			Scanner eleccion = new Scanner(System.in);
			int opcion = eleccion.nextInt();
			
			switch (opcion) {

			case 1: //SUMA
				
				//Recogemos los parametros de entrada
				System.out.println("\n Introduce el primer sumando: ");
				int s1 = new Scanner(System.in).nextInt();				
				System.out.println("\n Introduce el segundo sumando: ");
				int s2 = new Scanner(System.in).nextInt();
				
				//Creamos los parametros necesarios para hacer el envio mediante el metodo invokeBlocking y lo enviamos
				QName suma = new QName("http://ws.apache.org/axis2", "sumar");
				Object [] argumentos_suma = new Object[]  {s1, s2};
				Class [] resultado_suma = new Class [] {Integer.class};
				Object [] respuesta_suma = cliente.invokeBlocking(suma, argumentos_suma, resultado_suma);
				
				//Salta un error si la respuesta llega nula
				if (respuesta_suma[0] == null) {				
					System.out.println("\n El servicio no está funcionando.");
					return;
				}
				
				//Mostramos el resultado
				System.out.println("\n El resultado es: " + respuesta_suma[0]);				
				break;

			case 2: //RESTA
				
				//Recogemos los parametros de entrada
				System.out.println("\n Introduce el minuendo: ");
				int min = new Scanner(System.in).nextInt();				
				System.out.println("\n Introduce el sustraendo: ");
				int sus = new Scanner(System.in).nextInt(); 
				
				//Creamos los parametros necesarios para hacer el envio mediante el metodo invokeBlocking y lo enviamos
				QName resta = new QName("http://ws.apache.org/axis2", "restar");
				Object [] argumentos_resta = new Object[]  {min, sus};
				Class [] resultado_resta = new Class [] {Integer.class};			
				Object [] respuesta_resta = cliente.invokeBlocking(resta, argumentos_resta, resultado_resta);
				
				//Salta un error si la respuesta llega nula
				if (respuesta_resta[0] == null) {				
					System.out.println("\n El servicio no está funcionando.");					
					return;					
				}
				
				//Mostramos el resultado
				System.out.println("\n El resultado es: " + respuesta_resta[0]);
				break;

			case 3: //MULTIPLICACION
				
				//Recogemos los parametros de entrada
				System.out.println("\n Introduce el multiplicando : ");
				int m1 = new Scanner(System.in).nextInt();				
				System.out.println("\n Introduce el multiplicador: ");
				int m2  = new Scanner(System.in).nextInt();
				
				//Creamos los parametros necesarios para hacer el envio mediante el metodo invokeBlocking y lo enviamos
				QName multiplicacion = new QName("http://ws.apache.org/axis2", "multiplicar");
				Object [] argumentos_multiplicacion = new Object [] {m1, m2};
				Class [] resultado_multiplicacion = new Class [] {Integer.class};			
				Object [] respuesta_multiplicacion = cliente.invokeBlocking(multiplicacion, argumentos_multiplicacion, resultado_multiplicacion);
				
				//Salta un error si la respuesta llega nula
				if(respuesta_multiplicacion[0] == null) {
					System.out.println("\n El servicio no está funcionando.");
					return;
				}
				
				//Mostramos el resultado
				System.out.println("\n El resultado es: " + respuesta_multiplicacion[0]);
				break;

			case 4: //DIVISION
				
				//Recogemos los parametros de entrada
				System.out.println("\n Introduce el numerador: ");
				int num = new Scanner(System.in).nextInt();			
				System.out.println("\n Introduce el denominador: ");
				int den = new Scanner(System.in).nextInt();
				
				//Creamos los parametros necesarios para hacer el envio mediante el metodo invokeBlocking y lo enviamos
				QName division = new QName("http://ws.apache.org/axis2", "dividir");
				Object [] argumentos_division = new Object [] {num, den};
				Class [] resultado_division = new Class [] {Float.class};				
				Object [] respuesta_division = cliente.invokeBlocking(division, argumentos_division, resultado_division);
				
				//Salta un error si la respuesta llega nula
				if(respuesta_division[0] == null) {
					System.out.println("\n El servicio no está funcionando.");
					return;
				}
				
				//Mostramos el resultado
				System.out.println("\n El resultado es: " + respuesta_division[0]);
				break;

			case 5:
				
				System.out.println("\n Saliendo del cliente...");
				eleccion.close();
				continua = false;
				break;
				
			default:

				System.out.println("\n Introduce una opcion correcta.");
				break;

			}
		}
		
		return;
		
	}

}
