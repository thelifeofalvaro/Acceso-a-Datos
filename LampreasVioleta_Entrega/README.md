[Enunciado completo](https://github.com/user-attachments/files/24588464/Ampliacion.del.sistema.de.gestion.de.Lampreas.Violeta.con.nuevas.entidades.pdf)

📌Descripción general:

Este proyecto consiste en la ampliación del sistema de gestión de Lampreas Violeta, desarrollado inicialmente en Java durante las clases, incorporando nuevas entidades, 
sus DAOs correspondientes y una funcionalidad de exportación de datos a JSON utilizando la librería Jackson. El sistema permite la gestión de pedidos, clientes y productos, 
y ha sido extendido para incluir nuevas figuras relacionadas con el proceso de venta y distribución.

🎯 Objetivos del proyecto:

Los objetivos principales de esta ampliación son:
- Extender el modelo de datos del sistema.
- Aplicar el patrón DAO para la persistencia de nuevas entidades.
- Mejorar el programa principal con nuevas opciones de menú.
- Incorporar una funcionalidad de exportación a JSON usando Jackson.

🧱 Modelo de datos y nuevas entidades
Se han incorporado las siguientes clases al modelo:
- Repartidor: Representa a la persona encargada de repartir los pedidos.
  Relación 1:N con Pedido (un repartidor puede repartir muchos pedidos).
- Comercial: Representa al comercial responsable de la gestión de clientes.
  Relación 1:N con Cliente.

Además, se han consolidado las relaciones existentes:

- Pedido: Relación 1:N con DetallePedido.
- DetallePedido: Relación N:1 con Pedido.
- DetallePedido:Relación N:1 con Producto.

🗂️ Persistencia y DAOs

Para cada entidad nueva se ha implementado su correspondiente DAO, siguiendo el patrón usado en el archivo original:

Interfaces DAO con métodos CRUD:

- insert
- findById
- findAll
- update
- delete

Implementaciones concretas utilizando JDBC y base de datos relacional.

Persistencia adicional mediante ficheros (JSON).

Los DAOs existentes han sido adaptados cuando ha sido necesario para mantener la coherencia del modelo y sus relaciones.

🧭 Programa principal y menú

El programa principal ha sido actualizado para integrar completamente las nuevas entidades dentro del flujo de la aplicación.
- Se han incorporado nuevas opciones que permiten:
    Alta de nuevas entidades (Comercial, Repartidor).
    Consulta por identificador.
    Listado completo de registros.
    Eliminación de registros.

El menú sigue una estructura clara y numerada, permitiendo al usuario interactuar con el sistema desde consola de forma intuitiva.

Ejemplo de operaciones disponibles: Gestión de repartidores, Gestión de comerciales, Listado de pedidos con sus líneas asociadas, Visualización de datos persistidos

La lógica del menú delega las operaciones en los DAOs correspondientes, manteniendo una separación clara entre la lógica de presentación (menú), lógica de negocio y 
acceso a datos.

📤 Exportación de datos a JSON
Se ha implementado una funcionalidad de exportación de datos a JSON utilizando la librería Jackson (ObjectMapper), que genera automática del fichero en la carpeta del proyecto, 
siendo también compatibilidad con importación posterior. Ambas se realizan desde el menú lo que permite al usuario generar el fichero JSON bajo demanda.
