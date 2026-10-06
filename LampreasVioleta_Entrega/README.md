# 🐟 Lampreas Violeta — Entrega

Ampliación de un sistema de gestión desarrollado en Java, incorporando nuevas entidades, sus correspondientes DAOs y funcionalidades adicionales de persistencia y exportación de datos.

La ampliación mantiene el acceso a la base de datos mediante JDBC y el patrón DAO, e incorpora la serialización de datos en formato JSON mediante Jackson.

## 🎯 Objetivos

Los principales objetivos de la ampliación son:

- Extender el modelo de datos existente.
- Incorporar nuevas entidades al sistema.
- Implementar los DAOs correspondientes.
- Mantener las relaciones entre las diferentes entidades.
- Ampliar el programa principal con nuevas opciones de menú.
- Incorporar la exportación e importación de datos mediante JSON.

## 🧱 Nuevas entidades

Se incorporan las siguientes entidades al modelo:

### Repartidor

Representa a la persona encargada del reparto de los pedidos.

Relación:

- Un `Repartidor` puede estar asociado a muchos `Pedido` → **1:N**.

### Comercial

Representa al comercial responsable de la gestión de los clientes.

Relación:

- Un `Comercial` puede estar asociado a muchos `Cliente` → **1:N**.

## 🔗 Relaciones del modelo

Además de las nuevas relaciones, se mantienen y consolidan las relaciones existentes:

- `Pedido` → `DetallePedido`: **1:N**
- `DetallePedido` → `Pedido`: **N:1**
- `DetallePedido` → `Producto`: **N:1**

El modelo permite trabajar con las relaciones entre las diferentes entidades y comprobar el funcionamiento de las claves foráneas.

## 🗂️ Persistencia y DAOs

Para cada nueva entidad se implementa su correspondiente DAO siguiendo el patrón utilizado en el sistema original.

Las interfaces DAO incluyen operaciones CRUD:

- `insert`
- `findById`
- `findAll`
- `update`
- `delete`

Las implementaciones utilizan **JDBC** para realizar las operaciones sobre la base de datos relacional.

Los DAOs existentes también se adaptan cuando es necesario para mantener la coherencia del modelo y sus relaciones.

## 🧭 Programa principal

El programa principal se amplía para integrar las nuevas entidades dentro del menú de la aplicación.

Entre las operaciones disponibles se incluyen:

- Alta de repartidores.
- Alta de comerciales.
- Consulta por identificador.
- Listado completo de registros.
- Eliminación de registros.
- Gestión de pedidos.
- Consulta de pedidos junto con sus líneas asociadas.
- Visualización de los datos persistidos.

La lógica del menú delega las operaciones de acceso a datos en los DAOs correspondientes, manteniendo separada la interacción con el usuario de la persistencia.

## 📤 Exportación e importación JSON

Se incorpora una funcionalidad de exportación de los datos mediante **Jackson**.

A través de `ObjectMapper`, la aplicación puede generar un archivo JSON con una instantánea de la información almacenada en la base de datos.

La aplicación también permite importar posteriormente los datos desde JSON.

Durante la importación se respeta el orden necesario para mantener la integridad de las relaciones entre tablas y sus claves foráneas.

Si existen claves primarias duplicadas, la operación de importación falla.

## 🛠️ Tecnologías y conceptos

- Java
- JDBC
- Patrón DAO
- Base de datos relacional
- Operaciones CRUD
- Relaciones entre entidades
- Claves primarias y foráneas
- Integridad referencial
- JSON
- Jackson (`ObjectMapper`)
- Aplicaciones de consola

## 📚 Enunciado

[Enunciado completo](https://github.com/user-attachments/files/24588464/Ampliacion.del.sistema.de.gestion.de.Lampreas.Violeta.con.nuevas.entidades.pdf)

## 🎓 Contexto académico

Proyecto desarrollado durante la asignatura **Acceso a Datos de 2º DAM** como ampliación de un sistema de gestión desarrollado previamente en Java.

## 👤 Autor

**Álvaro Medina**