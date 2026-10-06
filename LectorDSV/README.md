# 📄 DSV con tuberías y comillas simples

Implementación de un algoritmo para leer y procesar un formato DSV (Delimiter-Separated Values) personalizado.

El formato utiliza `|` como separador de campos y comillas simples para delimitar aquellos campos que contienen el propio separador o espacios relevantes.

## 🎯 Objetivo

El objetivo del ejercicio es diseñar un algoritmo capaz de interpretar correctamente cada línea del archivo respetando las reglas específicas del formato.

La solución debe realizar el procesamiento carácter a carácter, sin utilizar `split()` directamente.

## 📄 Formato del archivo

El archivo utiliza:

- `|` como separador de campos.
- Comillas simples `'...'` para delimitar campos cuando es necesario.
- `''` para representar una comilla simple dentro de un campo.
- La primera línea contiene las cabeceras.
- Las líneas posteriores contienen los registros.

Por ejemplo, un campo que contiene el separador puede aparecer delimitado mediante comillas simples.

## ⚙️ Funcionamiento

El algoritmo debe:

1. Leer la línea de cabecera y almacenar los nombres de los campos.
2. Recorrer las líneas de datos.
3. Analizar cada carácter de la línea.
4. Detectar cuándo comienza y termina un campo entrecomillado.
5. Ignorar los separadores que se encuentren dentro de un campo entrecomillado.
6. Interpretar `''` como una única comilla simple.
7. Añadir correctamente el último campo al finalizar cada línea.

## 🔎 Casos contemplados

La implementación tiene en cuenta diferentes situaciones especiales:

- Campos que contienen `|`.
- Campos delimitados por comillas simples.
- Comillas simples internas escapadas mediante `''`.
- Espacios dentro de campos.
- Espacios fuera de los campos.
- Último campo de cada registro.

## 🛠️ Conceptos trabajados

- Java
- Lectura de archivos de texto.
- Procesamiento carácter a carácter.
- Análisis de cadenas.
- Algoritmos de parsing.
- Gestión de delimitadores.
- Tratamiento de caracteres escapados.

## 🎓 Contexto académico

Ejercicio desarrollado durante la asignatura **Acceso a Datos de 2º DAM**, centrado en el diseño de un algoritmo para interpretar un formato DSV personalizado.

## 👤 Autor

**Álvaro Medina**