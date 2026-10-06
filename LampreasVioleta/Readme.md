# 🐟 Lampreas Violeta

Aplicación Java de consola desarrollada para demostrar el acceso a una base de datos relacional mediante JDBC y el patrón DAO (Data Access Object).

El proyecto permite trabajar con operaciones CRUD, relaciones entre entidades y persistencia de datos mediante una base de datos relacional y archivos JSON.

## 🎯 Objetivo

El objetivo principal es aplicar diferentes conceptos de acceso a datos sobre un sistema de gestión, separando la lógica de la aplicación del acceso a la base de datos mediante el patrón DAO.

El proyecto permite comprobar:

- Conexión con una base de datos.
- Operaciones CRUD.
- Relaciones entre entidades.
- Integridad referencial mediante claves foráneas.
- Persistencia de datos.
- Exportación e importación de información en JSON.

## ⚙️ Funcionalidades

La aplicación dispone de un menú interactivo desde el que se pueden realizar diferentes operaciones:

- Comprobar la conexión con la base de datos.
- Listar registros de las diferentes entidades.
- Insertar nuevos registros.
- Buscar registros por identificador.
- Realizar operaciones CRUD.
- Consultar pedidos junto con sus líneas asociadas.
- Exportar la información completa de la base de datos a JSON.
- Importar información desde un archivo JSON.
- Vaciar la base de datos.

## 🧱 Relaciones entre entidades

El proyecto trabaja con diferentes tipos de relaciones entre las entidades de la base de datos:

- Relaciones **1:1**.
- Relaciones **1:N**.
- Relaciones **N:M**.

Estas relaciones permiten comprobar el funcionamiento de las claves foráneas y la integridad referencial durante las operaciones realizadas desde la aplicación.

## 🗂️ Patrón DAO

El acceso a los datos se organiza mediante el patrón **DAO (Data Access Object)**.

Cada entidad dispone de una interfaz DAO con operaciones como:

- `insert`
- `findById`
- `findAll`
- `update`
- `delete`

Las implementaciones concretas utilizan **JDBC** para comunicarse con la base de datos relacional.

De esta forma, la lógica del menú y de la aplicación queda separada de las operaciones de acceso y persistencia de datos.

## 📤 Exportación e importación JSON

La aplicación permite generar una instantánea completa de la base de datos en formato JSON.

La exportación incluye las diferentes entidades y sus relaciones.

También se permite importar posteriormente esa información desde JSON. Durante la importación se respeta el orden necesario para mantener las relaciones mediante claves foráneas.

En caso de existir claves primarias duplicadas, la operación de importación falla.

## 🛠️ Tecnologías y conceptos

- Java
- JDBC
- Base de datos relacional
- Patrón DAO
- Operaciones CRUD
- Claves primarias y foráneas
- Integridad referencial
- JSON
- Jackson
- Aplicación de consola

## 🎓 Contexto académico

Proyecto desarrollado durante la asignatura **Acceso a Datos de 2º DAM** para trabajar el acceso a bases de datos relacionales mediante Java y JDBC.

## 👤 Autor

**Álvaro Medina**