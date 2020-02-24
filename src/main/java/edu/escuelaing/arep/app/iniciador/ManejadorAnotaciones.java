package edu.escuelaing.arep.app.iniciador;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.app.iniciador.Anotaciones.AnotacionServer;
import edu.escuelaing.arep.app.iniciador.Anotaciones.AnotacionWeb;

public class ManejadorAnotaciones {

    public Map<String, Method> getPathClase() {

        List<Class> clasesServidor = new ArrayList<>();

        VerificadorClasesUtiles(clasesServidor);
        
        Map<String, Method> URLHandler = new HashMap<String, Method>();
        
        GeneradorPath(clasesServidor, URLHandler);

        return URLHandler;
    }

    public void VerificadorClasesUtiles(List<Class> clasesServidor){

        Class[] listaClases;

        try {

            listaClases = getClases("edu.escuelaing.arep");
            for (Class clase : listaClases) {
                if (clase.isAnnotationPresent(AnotacionServer.class)){
                    clasesServidor.add(clase);
                }  
            }

        } catch (Exception e) {

            System.err.println(e);

        }
    }

    public void GeneradorPath(List<Class> clasesServidor, Map<String, Method> URLHandler){

        AnotacionServer anotacionServer;
        AnotacionWeb anotacionWeb;

        for (Class clase : clasesServidor) {
            anotacionServer = (AnotacionServer) clase.getAnnotation(AnotacionServer.class);
            for (Method metodo : clase.getMethods()) {
                if (metodo.isAnnotationPresent(AnotacionWeb.class)) {
                    anotacionWeb = (AnotacionWeb) metodo.getAnnotation(AnotacionWeb.class);
                    URLHandler.put(anotacionServer.path() + anotacionWeb.path(), metodo);
                }
            }
        }
    }

    private static Class[] getClases(String direccion) throws ClassNotFoundException, IOException{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = direccion.replace('.', '/');
        Enumeration recursos = classLoader.getResources(path);
        List<File> ListaFiles = new ArrayList<File>();
        while (recursos.hasMoreElements()) {
            URL recursoURL = (URL) recursos.nextElement();
            ListaFiles.add(new File(recursoURL.getFile()));
        }
        ArrayList clases = new ArrayList();
        for (File archivo : ListaFiles) {
            clases.addAll(EncontrarClases(archivo, direccion));
        }
        return (Class[]) clases.toArray(new Class[clases.size()]);
    }

    private static Collection EncontrarClases(File directorio, String direccion) throws ClassNotFoundException {
        List clases = new ArrayList();
			if (!directorio.exists()) {
					return clases;
			}
			File[] listaFiles = directorio.listFiles();
			for (File file : listaFiles) {
					if (file.isDirectory()) {
							assert !file.getName().contains(".");
							clases.addAll(EncontrarClases(file, direccion + "." + file.getName()));
					} else if (file.getName().endsWith(".class")) {
							clases.add(Class.forName(direccion + '.' + file.getName().substring(0, file.getName().length() - 6)));
					}
			}
			return clases;
    }




}