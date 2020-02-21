package edu.escuelaing.arep.app.iniciador;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class Iniciador 
{
    public static void main( String[] args )
    {
        HttpServer server = new HttpServer(LoadClasses.getPathClass());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}