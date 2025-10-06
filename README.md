## Proyecto: Fruteria Bonana - Enunciado

Una frutería de barrio quiere un programa de consola que le permita guardar y recuperar su listado de frutas en un archivo de texto plano. No hay base de datos. El dueño necesita:
• Exportar el inventario actual a un .txt.
• Importar desde un .txt para cargar el inventario (por ejemplo, al iniciar el día).
### Modelo de datos
Crea la clase Fruta con estos campos:
• int id
• String nombre (p. ej., “Manzana Fuji”, “Plátano de Canarias”)
• double precioKg (precio por kilogramo)
• int stockKg (stock disponible en kilogramos)
Puedes añadir constructor vacío y con parámetros, getters/setters y toString().
### Formato del archivo TXT
• Un registro por línea, con separador ; (punto y coma).  

• Orden de campos por línea: id;nombre;precioKg;stockKg
  
Codificación obligatoria: UTF-8.  

### Ejemplos de líneas válidas:  

1;Manzana Fuji;2.95;120  

2;Plátano de Canarias;3.30;80

3;Pera "Conferencia";2.10;60
### Requisitos funcionales (consola)
Menú :
1. Añadir fruta en memoria (pide datos por teclado).
2. Listar frutas en memoria (por pantalla).
3. Exportar a TXT → escribe el fichero data/frutas.txt con el formato indicado.
4. Importar desde TXT → lee el fichero data/frutas.txt y carga/reemplaza la lista en memoria.
5. Salir.
### Validaciones:
• nombre no vacío (trim().length() >= 2)  

• precioKg >= 0  

• stockKg >= 0
Mensajería clara por consola (éxito/errores).
