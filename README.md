#Instalación

Para instalar este sistema, es necesario contar con *Java 7* además de *Tomcat 7*. 

Seguir los siguientes pasos:

1. Descargar el código desde el repositorio público de Github en https://github.com/HumbertoLoza/humbertolozaci.git (git clone https://github.com/HumbertoLoza/humbertolozaci.git)
2. Asegurarse de tener configurado Maven para que sea visible desde el path de su consola.
3. Entrar en la carpeta descargada desde Github y ejecutar mvn package
4. La instrucción anterior generará una carpeta llamada _target_ dentro de la cual hay un archivo .war, dicho archivo debe ser puesto en la carpeta webapps de su Tomcat.
5. Para que la aplicación funcione, es necesario agregar un *Data Source* en Tomcat. Para ello, es necesario editar el archivo RUTA_TOMCAT/conf/context.xml para agregar lo siguiente:

<Resource name="jdbc/test" auth="Container" type="javax.sql.DataSource"
            	driverClassName="org.hsqldb.jdbcDriver" url="jdbc:hsqldb:mem:ciTest"
            	username="sa" password="" maxActive="20" maxIdle="10" maxWait="-1" />
            	
6. De la misma manera, es necesario descargar el driver de JDBC de HSQLDB desde [aquí](http://www.java2s.com/Code/JarDownload/hsqldb/hsqldb-1.8.0.10.jar.zip) y agregarlo en la carpeta RUTA_TOMCAT/lib antes de iniciar el servidor de aplicaciones.
7. Iniciar el servidor de aṕlicaciones.
8. La base de datos utilizada por este sistema es una base de datos HSQLDB, por lo que reside en memoria y no es necesario instalar ningún gestor de base de datos adicional.
9. Iniciar Tomcat.
10. Una vez iniciado Tomcat, el sistema estará en SERVIDOR/application-1.0-SNAPSHOT (en un servidor instalado en un ambiente local, la ruta sería http://localhost/application-1.0-SNAPSHOT)
