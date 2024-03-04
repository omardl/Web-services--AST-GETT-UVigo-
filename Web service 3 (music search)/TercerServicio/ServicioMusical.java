package TercerServicio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.AxisFault;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ServicioMusical {
	
	static String cancion;
        static String artista;
        static String album;
		 

        public void setCancion(String can) {	
            this.cancion = can;
            return;	
        }


        public void setArtista(String art) {
            this.artista = art;
            return;		
        }
	

        public void setAlbum(String alb) {	
            this.album = alb;            
            return;	
        }
	
	
	
        public String getBusqueda(String tipo_busqueda) throws AxisFault {
		
		
        	try {
					
        		//Se crea la conexion con el servicio de busqueda de Itunes			 
                        URL url = null;
        		switch(tipo_busqueda) { 
        		case "1": //BUSQUEDA POR TITULO
        			url = new URL("https://itunes.apple.com/search?term=" + this.cancion.replaceAll("\\s","") + "&media=music&entity=song&attribute=songTerm");
        			break;
        		case "2": //BUSQUDA POR ALBUM
        			url = new URL("https://itunes.apple.com/search?term=" + this.album.replaceAll("\\s","") + "&media=music&entity=song&attribute=albumTerm");
        			break;
        		case "3": //BUSQUEDA POR ARTISTA				
        			url = new URL("https://itunes.apple.com/search?term=" + this.artista.replaceAll("\\s","") + "&media=music&entity=song&attribute=artistTerm");				
        			break;
        		}						
        		HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();	
        		conexion.setRequestMethod("GET");
	

        		//Se leen las respuestas (JSON) y se guardan en un JsonArray	
        		JsonReader lectura_json = Json.createReader(url.openStream()); 
                        JsonObject respuesta_json = lectura_json.readObject();
                        JsonArray respuestas = respuesta_json.getJsonArray("results");


                         //Creamos el cliente y los metadatos para la funcion setTitular del servicio de noticias
                         ServiceClient cliente = new ServiceClient();
    	                 Options opc = new Options();	   
    	                 opc.setTo(new EndpointReference("http://localhost:8080/axis2/services/Noticia"));
                         opc.setAction("urn:setTitular");
                         cliente.setOptions(opc);
                     
                         //Se crea el mensaje para llamar a la funcion setTitular 
                         OMFactory fact = OMAbstractFactory.getOMFactory();
                         OMNamespace namesp = fact.createOMNamespace("http://Noticia", "ns1");
                         OMElement metodo = fact.createOMElement("setTitular", namesp);
                         OMElement parametro = fact.createOMElement("titular", namesp);

                         switch(tipo_busqueda) { 
        		 case "1": //BUSQUEDA POR TITULO
        			parametro.setText("Resultado de la busqueda de canciones con el titulo " + this.cancion + ":");
        			break;
        		 case "2": //BUSQUDA POR ALBUM        		 	
                                parametro.setText("Resultado de la busqueda de canciones en el album " + this.album + ":");
        			break;
        		 case "3": //BUSQUEDA POR ARTISTA				
        			parametro.setText("Resultado de la busqueda de canciones de " + this.artista + ":");			
        			break;
        		 }
         
                         metodo.addChild(parametro);
            
                         //Se envia el mensaje
	                 cliente.sendRobust(metodo);
             
                 

                         //Modificamos los metadatos para llamar a la funcion setDescripcion
                         opc.setAction("urn:setDescripcion");
                         cliente.setOptions(opc);     
                 								
                         //Se crea la descripcion añadiendo cada array del Jsonarray a un String			
                         metodo = fact.createOMElement("setDescripcion", namesp);		
                         parametro = fact.createOMElement("descripcion", namesp);
				
                         String Descripcion = "\n";
			
                         for (int i = 0; i < respuestas.size(); i++) {
                                	 
                	        JsonObject linea = respuestas.getJsonObject(i);            	 
            	
                        	switch(tipo_busqueda) {    		    			
                        	case "1": //BUSQUEDA POR TITULO		
                		        Descripcion = Descripcion + "\nCancion: " + linea.getString("trackName") + " -- Fecha: " + linea.getString("releaseDate") + " -- Genero: " + linea.getString("primaryGenreName") + " -- Artista: " + linea.getString("artistName") + " -- Album: " + linea.getString("collectionName");     			
                		        break;                 		
                        	case "2": //BUSQUEDA POR ALBUM				
                	        	Descripcion = Descripcion + "\nAlbum: " + linea.getString("collectionName") + " -- Fecha: " + linea.getString("releaseDate") + " -- Genero: " + linea.getString("primaryGenreName") + " -- Artista: " + linea.getString("artistName") + " -- Cancion: " + linea.getString("trackName");     			   
                	        	break;
                	        case "3": //BUSQUEDA POR ARTISTA		
                		        Descripcion = Descripcion + "\nArtista: " + linea.getString("artistName") + " -- Cancion: " + linea.getString("trackName") + " -- Fecha: " + linea.getString("releaseDate") + " -- Genero: " + linea.getString("primaryGenreName") + " -- Album: " + linea.getString("collectionName");    			
                		        break;
                	        }
            	  	          
                         }
                 
                         parametro.setText(Descripcion);				              
                         metodo.addChild(parametro);     

                         //Enviamos el mensaje      
                         cliente.sendRobust(metodo);
                
                         //Devolvemos el resultado de la busqueda en un String
                         return Descripcion;
		       		
        	         } catch (MalformedURLException e) {
		
        	         	return "Error interno del servicio.";
				
        	         } catch (IOException e) {
		
                 		return "Error interno del servicio.";
					
                 	 }
		
                 }
	
}
         
           