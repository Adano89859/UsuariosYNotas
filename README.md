# PROYECTO USUARIOS Y NOTAS UT6/UT7 DE PROGRAMACIÓN

# DESCRIPCIÓN DEL PROYECTO:
Este proyecto consiste en una API Rest básica de un block de notas en formato aplicación con conexión a una base de datos, Log-in, validaciones de credenciales de usuario, hashing de contraseñas y testeo previo final, en esta actividad se documentan:

* Entidades: Usuarios y notas

* Relaciones: 
    - @OneToMany (Uno a muchos) un usuario tiene una o varias notas.

    - @ManyToOne (Muchos a uno) varias notas pertenecen a un usuario.
      
    - @JsonIgnore: Utilizado para evitar redundancias y bucles infinitos JSON en la base de datos.

* Repositorios: UsuarioReporitory y NotaRepository donde se generan las operaciones básicas: guardar, buscar, actualizar y eliminar.

* Servicios: Donde se gestiona la lógica de negocio de la aplicación, tenemos varias partes:

    - CrudService: Se presenta como una interfaz genérica que define los métodos CRUD básicos que una entidad debe tener, actúa como un controlador del AbstractCrudService ejecutando las instrucciones que debe         ejecutar el mismo siguiendo la lógica del AbstractCrudService.

    - AbstractCrudService: Se presebta como una clase abstracta que implenmenta CrudService y contiene la lógica común que es usada por los servicios concretos que llevan a cabo funciones independientes.

    - UsuarioService y NotaService: Se precentan como interfaces que se conectan con los controladores de la aplicación, en donde se almacenan las instrucciones que UsuarioServiceImpl y NotaServiceImpl deberán        ejecutar conforme a la lógica que se encuentra en estos.

    - UsuarioServiceImpl y NotaServiceImpl: Es donde se almacena la lógica de negocio, en estas clases se recogen los métodos y validaciones para que las consultas a la base de datos funcionen correctamente,           extienden de AbstractCrudService y se les implementa UsuarioService y NotaService que a su vez actúan como controladores de estos.
      
    - PassService: Un servicio utilizado para proporcionar métodos de hashing y validación de credenciales de usuario.
      
    - SecurityConfig: Configuración previa de seguridad que se implementa en PassService y le da soporte al mismo.

* Controladores: NotaController, UsuarioControllerV1/UsuarioControllerV2: Actúan como el cerebro de la aplicación, tanto NotaController como UsuarioControllerV1 controlan las consultas realizadas a la base de      datos, se llama a la interfaz UsuarioService y NotaService y estas implementan la lógica de UsuarioServiceImpl y NotaServiceImpl, también se hacen validaciones a la hora de ejecutar dichas consultas para         asegurar la robustez y consistencia de los datos, por ptro lado, UsuarioControllerV2 lo usamos para gestionar el login y guardar las validaciones de los emails, contraseñas y un hashing que encripte dichas       credenciales cuando el usuario inicie sesión.
  
* SigIn: Clase donde se implementa el Log-in y valida las credenciales introducidas por el usuario.

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
   application.properties y configurar la conexión comentada anteriormente
   llamada "Ejemplo de prueba".

6. Ejecutar el programa en el ejecutable que está dentro de la carpeta main > java
   el ejecutable se llama UsuarioYNotasApplication.java > darle a "Run".

7. En tu IDE favorito, ir al apartado de Spring Dashboard y visualizar los los resultados,
   debe salir una página en blanco con un error 404 (Not Found) por defecto.

8. Probar con postman o con tu app de testeo de API favorita y ejecutar las sentencias
   básicas GET, POST, UPDATE y DELETE.

# FRAMEWORKS UTILIZADOS:

SpringBoot inicializer: https://start.spring.io/index.html

Postman: https://www.postman.com/downloads/

# REPOSITORIO GITHUB:
Enlace a GitHub: https://github.com/Adano89859/UsuariosYNotas.git

# PRUEBAS Y TESTING FINAL (POSTMAN):
* Prueba de conexión:
![image](https://github.com/user-attachments/assets/a246bd2f-746c-415f-bae6-a2405e11a8d4)

* Creación de usuarios:
![image](https://github.com/user-attachments/assets/db75458c-176a-47e5-9f10-8acf36de6c23)
![image](https://github.com/user-attachments/assets/b479a807-2680-47a2-97f2-0a493fdd34df)

* Control de errores en usuarios y notas:
  - Si no ponemos algún campo requerido salta error 400 (Bad Request).
  - Si dejamos uno, varios o todos los campos vacíos también saltará el error.
  - Si asociamos un usuario o nota exitente con otros no existentes también dará error.
  - Si la contraseña es no tiene el mínimo de 8 caracteres también dará error.
  - Si el email no tiene un formato correcto dará error.
  - Si tenemos duplicados de usuarios o notas también dará error.
  - Si la nota no pertenece a un usuario también dará error.
 ![image](https://github.com/user-attachments/assets/9798e6ca-c89a-4d7a-a11d-fe951c9c5ec5)
 ![image](https://github.com/user-attachments/assets/20a55acd-0b1b-47c5-94d7-918f16a827b6)
 ![image](https://github.com/user-attachments/assets/e89a7f9b-03f2-4e24-9e08-7d61dd5a939b)
 ![image](https://github.com/user-attachments/assets/43328b7e-2dc6-4929-a0c6-74e0abba10bf)
 ![image](https://github.com/user-attachments/assets/d32e2505-e4c5-4881-bbd4-5fd502d7abc3)
 ![image](https://github.com/user-attachments/assets/4fda825f-c700-4b72-bd49-a4ebc1db0a83)
 ![image](https://github.com/user-attachments/assets/04b32745-0b43-466a-9da8-44c983672ee7)
 ![image](https://github.com/user-attachments/assets/ba812bc1-3f8d-47cf-a19b-8c3689f53216)
 ![image](https://github.com/user-attachments/assets/441c5fe5-a0fa-45c3-be65-fb14e9fec835)
 ![image](https://github.com/user-attachments/assets/39e0a24c-7ccf-4ee2-9349-2d4cc6910581)

* Asignar notas al usuario:
 ![image](https://github.com/user-attachments/assets/813d363e-53b7-48b6-b221-e9bd0d711b1a)
 ![image](https://github.com/user-attachments/assets/a02c9809-3ddd-47f4-bc0d-778cd7aba749)

* Mostrar usuarios:

![image](https://github.com/user-attachments/assets/e7b82ef7-7ab7-4530-9cc1-969e229de3e7)

* Buscar información de un usuario concreto:
![image](https://github.com/user-attachments/assets/512f5776-12a0-480f-a3f0-85fd8f089787)


* Actualizar usuarios:

![image](https://github.com/user-attachments/assets/3ff8dcde-4582-474b-8600-7d6ece82c889)


* Borrar usuarios:

![image](https://github.com/user-attachments/assets/fc283742-87c0-42f5-b8c7-fab5a7c420e1)
![image](https://github.com/user-attachments/assets/481432ad-641e-4816-ab62-87451a6170a2)


* Mostrar notas:

![image](https://github.com/user-attachments/assets/c8a07938-c322-4907-8235-7b08f63f4c9c)

* Buscar nota concreta:
![image](https://github.com/user-attachments/assets/33ba3b05-fdd1-4b41-b5c2-c7a887c9f525)


* Eliminar notas:

![image](https://github.com/user-attachments/assets/c4de2b43-2079-4e5f-ada1-dd5e6db6ac34)
![image](https://github.com/user-attachments/assets/86430d9f-aee1-45ed-8638-fd4bdca26ffe)

* Mostrar notas asociadas a un usuario:
![image](https://github.com/user-attachments/assets/cdc4196c-9af5-42cd-9871-52ab9c45a5f9)

* Registro, Log-in y hashing de contraseña de un usuario:
![image](https://github.com/user-attachments/assets/f2362566-1eda-405c-946c-a379f51415fa)

# VALORACIÓN FINAL:
En este proyecto hemos aprendido a implementar las funcionalidades intermedias y avanzadas de una API-REST con una conexión a una base de datos real,
con validaciones y cifrado de datos, en general, el proyecto nos ha resultado satisfactorio, aunque hubo ciertas partes como la implementación del 
Log-in, hashing y el testeo general que fueron algo más tediosas, aunque fuera lo más complejo del proyecto, en general el proyecto se ha mostrado
funcional, por lo que estamos muy satisfechos con la realización del mismo.

#Creado por: Adán Romero y Kevin Sánchez (1ºB-DAM-PRO).



  









 






















