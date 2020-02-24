package edu.escuelaing.arep.app.Pojos;


import edu.escuelaing.arep.app.iniciador.Anotaciones.AnotacionServer;
import edu.escuelaing.arep.app.iniciador.Anotaciones.AnotacionWeb;


@AnotacionServer(path = "/inicial")
public class webPrueba {

    @AnotacionWeb(path = "/saludoBienvenida")
    public static String Hello() {
        return "<h1>Hola, bienvenido a la parte de prueba de los pojos</h1>";
    }
}