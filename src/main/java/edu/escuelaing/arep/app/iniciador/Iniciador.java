package edu.escuelaing.arep.app.iniciador;

import java.io.IOException;

import edu.escuelaing.arep.app.servidor.Servidor;


/**
 * Hello world!
 *
 */
public class Iniciador 
{

    /**
     * Metodo iniciador de el servidor
     * @param args
     */
    public static void main( String[] args )
    {
        Servidor server = new Servidor(ManejadorAnotaciones.getPathClase());
        try {
            server.Start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}