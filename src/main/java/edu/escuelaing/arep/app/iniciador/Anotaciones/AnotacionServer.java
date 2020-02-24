package edu.escuelaing.arep.app.iniciador.Anotaciones;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnotacionServer {
    String path() default "/";
}