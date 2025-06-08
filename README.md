# ACTIVIDAD USUARIOS Y NOTAS UT6/UT7 DE PROGRAMACIÓN

# DESCRIPCIÓN DEL PROYECTO:
Este proyecto consiste en una API Rest básica de un block de notas en formato aplicación con conexión a una base de datos, login y validaciones de Usuario y testeo previo, en esta actividad se documentan:

* Entidades: Usuarios y notas

* Relaciones: 
    - @OneToMany (Uno a muchos) un usuario tiene una o varias notas.

    - @ManyToOne (Muchos a uno) varias notas pertenecen a un usuario.

* Repositorios: UsuarioReporitory y NotaRepository donde se generan las operaciones básicas: guardar, buscar, actualizar y eliminar.

* Servicios: Donde se gestiona la lógica de negocio de la aplicación, tenemos varias partes:

    - CrudService: Se presenta como una interfaz genérica que define los métodos CRUD básicos que una entidad debe tener, actúa como un controlador del AbstractCrudService ejecutando las instrucciones que debe ejecutar el mismo siguiendo la lógica del AbstractCrudService.

    - AbstractCrudService: Se presebta como una clase abstracta que implenmenta CrudService y contiene la lógica común que es usada por los servicios concretos que llevan a cabo funciones independientes.

    - UsuarioService y NotaService: Se precentan como interfaces que se conectan con los controladores de la aplicación, en donde se almacenan las instrucciones que UsuarioServiceImpl y NotaServiceImpl deberán ejecutar conforme a la lógica que se encuentra en estos.

    - UsuarioServiceImpl y NotaServiceImpl: Es donde se almacena la lógica de negocio, en estas clases se recogen los métodos y validaciones para que las consultas a la base de datos funcionen correctamente, extienden de AbstractCrudService y se les implementa UsuarioService y NotaService que a su vez actúan como controladores de estos.

* Controladores: NotaController, UsuarioControllerV1/UsuarioControllerV2: Actúan como el cerebro de la aplicación, tanto NotaController como UsuarioControllerV1 controlan las consultas realizadas a la base de datos, se llama a la interfaz UsuarioService y NotaService y estas implementan la lógica de UsuarioServiceImpl y NotaServiceImpl, también se hacen validaciones a la hora de ejecutar dichas consultas para asegurar la robustez y consistencia de los datos, por ptro lado, UsuarioControllerV2 lo usamos para gestionar el login y guardar las validaciones de los emails, contraseñas y un hashing que encripte dichas credenciales cuando el usuario inicie sesión.

* Application.Properties: La usamos para gestionar la conexión a la base de datos, es desde donde conectaremos nuestra API con nuestro SGBD preferido para que nos cree la base de datos y nos la permita gestionar.

    - Ejemplo de prueba:

        spring.application.name=UsuariosYNotas
    
        spring.datasource.url=jdbc:mysql://localhost:3306/UsuariosYNotasBD
        spring.datasource.username=root
        spring.datasource.password=tu_contraseña
        
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
        
        server.port=8080

# INSTRUCCIONES DE EJECUCIÓN:

1. Crear y activar una base de datos en MySQL Workbench o tu SGBD favorito con un
   nombre identificativo como por ejemplo: "UsuariosYNotasBD".

2. Activar el programa XAMPP y los servicios de Apache y MySQL con puerto al
   MySQL recomendado: 3306 (default).

3. En tu IDE favorito, debes tener una extensión de herramientas llamada
   Spring Boot Extension Pack y descargarte todos los paquetes para que funcione
   la API

4. Configurar en el archivo que esta dentro de las carpetas main > resources >
   application.properties y configurar la conexión anterior llamada "Ejemplo de prueba".

5. Ejecutar el programa en el ejecutable que está dentro de la carpeta main > java
   el ejecutable se llama UsuarioYNotasApplication.java > darle a "Run".

6. En tu IDE favorito, ir al apartado de Spring Dashboard y visualizar los los resultados,
   debe salir una página en blanco con un error 404 (Not Found) por defecto.

7. Probar con postman o con tu app de testeo de API favorita y ejecutar las sentencias
   básicas GET, POST, UPDATE y DELETE.

# FRAMEWORKS A UTILIZAR:

SpringBoot inicializer: https://start.spring.io/index.html

Postman: https://www.postman.com/downloads/

# REPOSITORIO DE GITHUB:

Enlace a GitHub: https://github.com/Adano89859/UsuariosYNotas.git

# PRUEBAS Y TESTING FINAL (POSTMAN):

