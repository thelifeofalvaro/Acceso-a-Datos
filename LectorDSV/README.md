# DSV con tuberías y comillas simples
Se utilizará el carácter | como separador de campos en vez de comas, y las cadenas que contengan el propio separador irán entre comillas simples '...'.
Las comillas simples internas se escapan repitiéndolas ('').
## Observaciones importantes:
En la línea 3, el campo Juan|Carlos contiene el separador, por eso va entre '...'.
En las líneas 3 y 4, hay comillas simples internas escapadas: ''.
## Tarea: Diseño de un algoritmo para lectura de archivos DSV (Delimiter-Separated Values)
Se proporciona un archivo de texto con el siguiente formato personalizado:
Los campos están separados por el carácter |.
- Si un campo contiene el separador | o espacios relevantes, va entre comillas simples '...'.
- Las comillas simples dentro de un campo se representan con doble comilla simple ('').
- La primera línea del archivo contiene las cabeceras.
- El resto de líneas son los registros.
  Objetivo del ejercicio
Debes crear un algoritmo que:
Lea la línea de cabecera y almacene los nombres de los campos.
Recorra todas las líneas de datos y separe cada una en campos respetando las reglas anteriores.

## Requisitos del algoritmo
Tu solución debe contemplar:
- Lectura carácter a carácter (no usar "split" directamente).
- Reconocimiento de campos que empiezan y acaban con '...'.
- Detección y tratamiento de '' como una sola '.
- Separación correcta aunque haya | dentro de un campo entrecomillado.
- Inclusión del último campo al terminar cada línea.
- Manejo de espacios que están dentro o fuera de los campos.
