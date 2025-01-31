# java_jdbc_pool

Este proyecto implementa un CRUD (Crear, Leer, Actualizar, Eliminar) utilizando un pool de conexiones para realizar operaciones con la base de datos.

## Características

- Uso de JDBC para la conexión a la base de datos.
- Implementación de un pool de conexiones para mejorar el rendimiento.
- Operaciones CRUD completas: crear, leer, actualizar y eliminar registros en la base de datos.

## Requisitos

- Java 8 o superior.
- Maven para la gestión de dependencias.

## Instalación

1. Clona el repositorio:
   ```sh
   git clone https://github.com/ProgramadorJunior20/java_jdbc_pool.git
1. Navega al directorio del proyecto:
   cd java_jdbc_pool
2. Compila el proyecto con Maven:
   mvn clean install

## Uso

1.  Configura la base de datos en el archivo
    src/main/resources/database.properties.
2. Ejecuta la aplicación:
   mvn exec:java -Dexec.mainClass="com.example.Main"
   
## Contribuir
Las contribuciones son bienvenidas. Siéntete libre de abrir un issue o enviar un pull request.

## Licencia 
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para obtener más detalles.

##
Asegúrate de ajustar cualquier detalle específico de tu proyecto o agregar secciones adicionales según sea necesario.
