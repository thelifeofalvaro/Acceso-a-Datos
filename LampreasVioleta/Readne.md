# Proyecto Lampreas Violeta -  DemoRelaciones (DAOs + JDBC + JSON)

Este proyecto es una aplicación Java de consola cuyo objetivo es demostrar el funcionamiento de una base de datos relacional mediante el uso del patrón DAO (Data Access Object), JDBC y serialización JSON.

La aplicación permite:
- Probar la conexión con la base de datos
- Realizar operaciones CRUD básicas
- Comprobar relaciones entre tablas (1:1, 1:N y N:M)
- Exportar e importar una instantánea completa de la base de datos en formato JSON
- Está pensada como herramienta de prueba y validación de:
- Integridad referencial (claves foráneas)
- Correcto funcionamiento de los DAOs
- Persistencia de datos
  
## Funcionamiento de la aplicación
Al ejecutar la aplicación se muestra un menú interactivo por consola que permite:
- Listar registros de cada entidad
- Insertar nuevos registros
- Buscar registros por ID
- Ver pedidos con sus líneas asociadas
- Exportar todos los datos de la base de datos a un fichero JSON
- Importar datos desde un fichero JSON respetando el orden de claves foráneas
- Vaciar completamente la base de datos
- Exportación a JSON generando un fichero que contiene una instantánea completa de la BBDD, incluyendo todas las entidades y sus relaciones.
- Importación desde JSON ordenada y respetando el orden de las tablas, si existen PK duplicadas la operación falla
