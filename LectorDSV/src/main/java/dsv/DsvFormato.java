package dsv;

// Clase para formatear la salida por pantalla

public class DsvFormato {

    public static String formatearCampo(String campo) {
        if (campo == null) return "";

        // Detectamos si necesita comillas (porque contiene separadores, espacios o comillas dobles)
        boolean necesitaComillas = campo.contains("|") || campo.contains(" ") || campo.contains("\"");

        // Escapamos comillas simples duplicándolas (' -> '')
        String resultado = campo.replace("'", "''");

        // Si necesita comillas, lo rodeamos con comillas simples
        if (necesitaComillas) {
            resultado = "'" + resultado + "'";
        }

        return resultado;
    }
}
