# 🍎 Frutería Bonana

Aplicación Java de consola para gestionar un inventario de frutas en memoria y persistirlo mediante archivos de texto plano.

## 🎯 Objetivo

El proyecto consiste en desarrollar una aplicación sencilla para que una frutería pueda guardar y recuperar su inventario sin utilizar una base de datos.

La aplicación permite trabajar con un listado de frutas en memoria y exportarlo o importarlo mediante un archivo `.txt`.

## ⚙️ Funcionalidades

La aplicación dispone de un menú interactivo por consola con las siguientes opciones:

1. Añadir una fruta en memoria.
2. Listar las frutas almacenadas.
3. Exportar el inventario a un archivo TXT.
4. Importar el inventario desde un archivo TXT.
5. Salir.

Los datos se almacenan inicialmente en memoria y pueden persistirse en `data/frutas.txt`.

## 🧱 Modelo de datos

Cada fruta se representa mediante la clase `Fruta`, con los siguientes atributos:

- `id`: identificador de la fruta.
- `nombre`: nombre de la fruta.
- `precioKg`: precio por kilogramo.
- `stockKg`: stock disponible en kilogramos.

La clase también incorpora los métodos necesarios para trabajar con estos datos, como constructores, getters, setters y `toString()`.

## 📄 Formato del archivo

El inventario se almacena en un archivo de texto plano utilizando `;` como separador.

Cada línea representa una fruta siguiendo el formato:

    id;nombre;precioKg;stockKg

Ejemplo:

    1;Manzana Fuji;2.95;120
    2;Plátano de Canarias;3.30;80
    3;Pera "Conferencia";2.10;60

El archivo utiliza codificación UTF-8.

## ✅ Validaciones

La aplicación comprueba los datos introducidos antes de añadir una fruta:

- El nombre no puede estar vacío y debe tener al menos 2 caracteres.
- El precio por kilogramo debe ser mayor o igual que 0.
- El stock debe ser mayor o igual que 0.

También se muestran mensajes claros en consola para indicar operaciones realizadas correctamente o errores de validación.

## 🛠️ Tecnologías y conceptos

- Java
- Programación orientada a objetos
- Colecciones en memoria
- Lectura y escritura de archivos
- Archivos de texto
- Codificación UTF-8
- Validación de datos
- Aplicaciones de consola

## 🎓 Contexto académico

Proyecto desarrollado durante la asignatura **Acceso a Datos de 2º DAM**, como ejercicio de persistencia mediante archivos de texto plano.

## 👤 Autor

**Álvaro Medina**