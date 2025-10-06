/*
Creamos la clase para la APP que gestiona por consola en invetario de la fruteria.
Incluye menú para: añadir, listar, exportar e importar frutas desde un archivo de texto UTF-8.
*/

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;

public class AppFruteria {

    //RUTA relativa del archivo dentro del repo/proyecto
    private static Path RUTA = Paths.get("data/frutas.txt");

    public static void main(String[] args) {

        // Listado de frutas
        List<Fruta> frutas = new ArrayList<>();

        // Scanner para leer desde consola
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("==== FRUTERÍA BONANA ====");
        System.out.println("⚠ Importa el ultimo stock antes de empezar la jornada");
        System.out.println("⚠ Exporta el stock antes de finalizar la jornada");

        //Bucle para el menú
        while (true) {
            System.out.println("==== MENÚ ====");
            System.out.println("1. Añadir producto");
            System.out.println("2. Listado stock");
            System.out.println("3. FINAL DEL DIA. Exportar stock actual");
            System.out.println("4. PRINCIPIO DEL DÍA. Importar stock.");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opcion:");
            String opcion = sc.nextLine();

            switch (opcion) {
                // Añadir Fruta
                case "1": {
                    try {
                        int id;
                        while (true) {
                            System.out.print("ID (único): ");
                            String entrada = sc.nextLine();
                            try {
                                id = Integer.parseInt(entrada);
                                if (id < 0) {
                                    System.out.println("El ID debe ser un número positivo.");
                                    continue;
                                }
                                boolean existe = false;
                                for (Fruta f : frutas) {
                                    if (f.getId() == id) {
                                        existe = true;
                                        break;
                                    }
                                }
                                if (existe) {
                                    System.out.println("Ya existe una fruta con ese ID.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("ID inválido. Escribe un número entero.");
                            }
                        }

                        String nombre;
                        while (true) {
                            System.out.print("Nombre fruta (mínimo 2 caracteres): ");
                            nombre = sc.nextLine().trim();
                            if (nombre.length() < 2) {
                                System.out.println("El nombre debe tener al menos 2 caracteres.");
                                continue;
                            }
                            break;
                        }

                        double precioKg;
                        while (true) {
                            System.out.print("Precio por kg (>= 0): ");
                            String entrada = sc.nextLine().trim().replace(',', '.');
                            try {
                                precioKg = Double.parseDouble(entrada);
                                if (precioKg < 0) {
                                    System.out.println("El precio no puede ser negativo.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Introduce un número válido.");
                            }
                        }

                        int stockKg;
                        while (true) {
                            System.out.print("Stock en kg (>= 0): ");
                            String entrada = sc.nextLine().trim();
                            try {
                                stockKg = Integer.parseInt(entrada);
                                if (stockKg < 0) {
                                    System.out.println("El stock no puede ser negativo.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Introduce un número entero válido.");
                            }
                        }

                        Fruta f = new Fruta(id, nombre, precioKg, stockKg);
                        frutas.add(f);
                        System.out.println("Fruta añadida con éxito: " + f);

                    } catch (Exception e) {
                        System.out.println("Error al añadir fruta: " + e.getMessage());
                    }
                    break;
                }

                // Listado Frutas
                case "2": {
                    if (frutas.isEmpty()) {
                        System.out.println("No hay frutas en memoria.");
                    } else {
                        System.out.println("\nListado del stock actual:");
                        for (Fruta f : frutas) {
                            System.out.println(f);
                        }
                    }
                    break;
                }
                //Exportar a .txt
                case "3": {
                    try {
                        if (RUTA.getParent() != null) {
                            Files.createDirectories(RUTA.getParent());
                        }

                        try (BufferedWriter bw = Files.newBufferedWriter(RUTA,
                                StandardCharsets.UTF_8,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING)) {

                            for (Fruta f : frutas) {
                                bw.write(f.getId() + ";" + f.getNombre() + ";" + f.getPrecioKg() + ";" + f.getStockKg());
                                bw.newLine();
                            }
                        }

                        System.out.println("Inventario exportado correctamente a: " + RUTA.toAbsolutePath());

                    } catch (IOException e) {
                        System.out.println("Error al exportar: " + e.getMessage());
                    }
                    break;
                }
                //Importar desde .txt
                case "4": {
                    if (!Files.exists(RUTA)) {
                        System.out.println("No se encontró el archivo: " + RUTA.toAbsolutePath());
                        break;
                    }

                    try {
                        List<String> lineas = Files.readAllLines(RUTA, StandardCharsets.UTF_8);
                        List<Fruta> listaLeida = new ArrayList<>();

                        for (String linea : lineas) {
                            if (linea.trim().isEmpty()) continue;
                            String[] partes = linea.split(";");
                            if (partes.length != 4) {
                                System.out.println("Línea ignorada (formato incorrecto): " + linea);
                                continue;
                            }
                            try {
                                int id = Integer.parseInt(partes[0].trim());
                                String nombre = partes[1].trim();
                                double precioKg = Double.parseDouble(partes[2].trim());
                                int stockKg = Integer.parseInt(partes[3].trim());

                                Fruta f = new Fruta(id, nombre, precioKg, stockKg);
                                listaLeida.add(f);
                            } catch (NumberFormatException e) {
                                System.out.println("Línea inválida, se omitirá: " + linea);
                            }
                        }

                        frutas.clear();
                        frutas.addAll(listaLeida);
                        System.out.println("Importación completada. Frutas cargadas: " + frutas.size());

                    } catch (IOException e) {
                        System.out.println("Error al importar: " + e.getMessage());
                    }
                    break;
                }
                // Salida/Cierre de programa
                case "5":
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    return;
                // Opciones inválidas
                default:
                    System.out.println("Opción no válida. Elige del 1 a 5.");

            }

        }
    }

}