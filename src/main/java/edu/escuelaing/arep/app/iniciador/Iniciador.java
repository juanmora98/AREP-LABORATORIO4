package edu.escuelaing.arep.app.iniciador;

import java.io.IOException;

import edu.escuelaing.arep.app.servidor.Servidor;


/**
 * Hello world!
 *
 */
public class Iniciador 
{
    public static void main( String[] args )
    {
        Servidor server = new Servidor(ManejadorAnotaciones.getPathClase());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}