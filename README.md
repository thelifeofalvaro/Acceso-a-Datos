# Acceso a Datos

Repositorio con los proyectos y ejercicios desarrollados durante la asignatura **Acceso a Datos de 2º DAM**.

Los trabajos abarcan diferentes formas de acceso, tratamiento y persistencia de datos mediante Java.

## 📂 Proyectos

### 🍎 Frutería Bonana

Aplicación de consola para gestionar un inventario de frutas en memoria y persistirlo mediante archivos de texto plano.

Incluye:

- Gestión de frutas desde consola.
- Exportación del inventario a TXT.
- Importación desde TXT.
- Validación de datos.
- Lectura y escritura de archivos UTF-8.

---

### 🐟 Lampreas Violeta — DemoRelaciones

Aplicación Java de consola orientada a demostrar el acceso a una base de datos relacional mediante **JDBC** y el patrón **DAO (Data Access Object)**.

Incluye:

- Operaciones CRUD.
- Gestión de relaciones entre entidades.
- Relaciones 1:1, 1:N y N:M.
- Persistencia mediante JDBC.
- Exportación e importación de datos en JSON.
- Uso de Jackson para la serialización.
- Gestión de claves foráneas e integridad referencial.

El proyecto también incorpora nuevas entidades como `Repartidor` y `Comercial`, junto con sus correspondientes DAOs.

---

### 📄 DSV con tuberías y comillas simples

Ejercicio de diseño de un algoritmo para interpretar un formato DSV personalizado utilizando `|` como separador y comillas simples para delimitar campos.

El algoritmo debe contemplar:

- Lectura de cabeceras.
- Procesamiento carácter a carácter.
- Campos entrecomillados.
- Separadores incluidos dentro de campos.
- Escapado de comillas simples mediante `''`.
- Tratamiento de espacios dentro y fuera de los campos.

El ejercicio se realiza sin utilizar `split()` directamente, implementando la lógica necesaria para interpretar el formato.

## 🛠️ Tecnologías y conceptos

- Java
- JDBC
- DAO
- Bases de datos relacionales
- CRUD
- JSON
- Jackson
- Lectura y escritura de archivos
- Procesamiento de texto
- Persistencia de datos

## 🎓 Contexto

Repositorio académico desarrollado durante **2º DAM** como parte de la asignatura **Acceso a Datos**.

## 👤 Autor

**Álvaro Medina**