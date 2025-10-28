package Ejemplo1;

import dsv.DsvUtils;
import dsv.DsvFormato;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Programa principal para leer un DSV (separador '|', comillas simples)
 * y convierte cada línea en una lista para mostrarla por pantalla
 */

public class Main {

    //Ruta del archivo
    private static final Path RUTA = Paths.get("data","datos.dsv");

    public static void main(String[] args) {

        //Comprobamos que existe ruta y archivo
        try {
            Files.createDirectories(RUTA.getParent());
        } catch (IOException e) {
            System.err.println("No se pudo asegurar: " + e.getMessage());
        }

        // Creamos un arrayList para guardar cada linea
        List<String> tabla = new ArrayList<>();

        // Entramos al archivo para leer linea a linea

        try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {

            String linea;

            linea = br.readLine();

            //Si no hay cabecera, cerramos ejecución
            if(linea == null){
                System.err.println("DOCUMENTO VACÍO");
                return;
            }

            //Creamos linea de titulos/cabecera
            
            List<String> headers = DsvUtils.parseDSVLine(linea);
            tabla.add(headers);

            //Creamos linea de datos
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                List<String> campos = DsvUtils.parseDSVLine(linea);
                tabla.add(campos);
            }
            //Creamos la tabla
            for (List<String> fila : tabla) {
                for (int i = 0; i < fila.size(); i++) {
                    String campo = DsvFormato.formatearCampo(fila.get(i));

                    // Si no es el último campo, añadir separador " | "
                    if (i < fila.size() - 1) {
                        System.out.print(campo + " | ");
                    } else {
                        System.out.print(campo);
                    }
                }
                System.out.println(); // salto de línea entre filas
            }
        } catch (NoSuchFileException e){

            System.err.println("No aparece el archivo: " + RUTA.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
