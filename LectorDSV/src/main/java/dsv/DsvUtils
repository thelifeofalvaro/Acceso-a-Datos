package dsv;

import java.util.ArrayList;
import java.util.List;

public class DsvUtils {

    /**
     * Parsea una línea DSV conforme a las reglas:
     * - separador: |
     * - campos entrecomillados con comillas simples '...'
     * - dentro de un campo entrecomillado, '' representa una sola comilla simple
     **/

    public static List<String> parseDSVLine(String linea) {
        List<String> campos = new ArrayList<>();
        if (linea == null) return campos;

        // Leemos el campo carácter a carácter
        StringBuilder campo = new StringBuilder();
        //Para saber si estamos entre comillas o no
        boolean enComillas = false;

        // Recorremos la línea carácter a carácter
        for (int i = 0; i < linea.length(); i++) {

            // Buscamos el caracter actual
            char ch = linea.charAt(i);

            // Si el caracter es comilla simple.
            if (ch == '\'') {
                if (!enComillas) {
                    // Si estamos entre comillas...
                    enComillas = true;
                } else {
                    //Comprobamos si es fin de linea
                    if (i + 1 < linea.length() && linea.charAt(i + 1) == '\'') {
                        // es un par de comillas '' => representa una comilla simple en el contenido
                        campo.append('\'');
                        i++;
                        // Buscamos la siguiente comilla (cierre)
                    } else {
                        // es la comilla de cierre: no la añadimos al campo, solo cambiamos estado
                        enComillas = false;
                    }
                }
            } else if (ch == '|' && !enComillas) {
                /** Fuera de las comillas y aparece el separador, se salta de campo
                * Se eliminan espacios fuera de las comillas, pero se conservan dentro
                 * con el .trim
                 * */
                campos.add(campo.toString().trim());
                campo.setLength(0);
                //Volvemos a 0 para leer el siguiente campo
            } else {
                // Si es cualquier otro carácter lo añadimos al campo
                campo.append(ch);
            }
        }

        //Al acabar la línea añadimos el último campo aunque esté vacío
        campos.add(campo.toString().trim());

        return campos;
    }
}
