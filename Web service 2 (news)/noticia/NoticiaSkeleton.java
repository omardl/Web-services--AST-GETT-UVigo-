/**
 * NoticiaSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */


package noticia;


/**
 *  NoticiaSkeleton java skeleton for the axisService
 */


public class NoticiaSkeleton {

    //Creamos las variables titular, descripcion y url
    static String Titular;
    static String Descripcion;
    static String URL;


    /**
     * Auto generated method signature
     *
     * @param getTitular
     * @return getTitularResponse
     */

    
    public noticia.GetTitularResponse getTitular(noticia.GetTitular getTitular) {

        //Obtenemos el titular de la noticia y lo devolvemos
        noticia.GetTitularResponse respuesta = new noticia.GetTitularResponse();
        respuesta.set_return(Titular);
        return respuesta;         

    }


    /**
     * Auto generated method signature
     *
     * @param setTitular
     * @return
     */


    public void setTitular(noticia.SetTitular setTitular) {

        //Introducimos el valor del titular de la noticia
        Titular = setTitular.getTitular();
        return;

    }


    /**
     * Auto generated method signature
     *
     * @param setDescripcion
     * @return
     */


    public void setDescripcion(noticia.SetDescripcion setDescripcion) {

        //Introducimos el valor de la descripcion de la noticia
        Descripcion = setDescripcion.getDescripcion();
        return;

    }


    /**
     * Auto generated method signature
     *
     * @param getDescripcion
     * @return getDescripcionResponse
     */


    public noticia.GetDescripcionResponse getDescripcion(noticia.GetDescripcion getDescripcion) {

        //Obtenemos el valor de la descripcion de la noticia y lo devolvemos
        GetDescripcionResponse respuesta = new GetDescripcionResponse();
        respuesta.set_return(Descripcion);
        return respuesta;
        
    }


    /**
     * Auto generated method signature
     *
     * @param getUrl
     * @return getUrlResponse
     */


    public noticia.GetUrlResponse getUrl(noticia.GetUrl getUrl) {

        //Obtenemos el valor del URL de la noticia y lo devolvemos
        GetUrlResponse respuesta = new GetUrlResponse();
        respuesta.set_return(URL);
        return respuesta;

    }


    /**
     * Auto generated method signature
     *
     * @param setUrl
     * @return
     */


    public void setUrl(noticia.SetUrl setUrl) {

        //Introducimos el valor del URL de la noticia
        URL = setUrl.getUrl();
        return;

    }

}
