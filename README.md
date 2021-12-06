# eCommerceMobile_API
###### API REST desarrollada en Java, utilizando Spring Boot y Maven

### 1.- ¿Cómo crear servicios REST usando Java y SpringBoot?
Se debe comenzar definiendo una interfaz la cual va a actuar de Repositorio.
Para decir que la interfaz va a ser un repositorio se le debe agregar @Repository 
en la definición de dicha interfaz.
###### @Repository: Es un marcador para cualquier clase que cumpla el rol o el estereotipo de un repositorio (también conocido como Data Access Object o DAO).
Se le pueden agregar "funcionalidades" a la interfaz haciendo que extienda (o inlcuya) los métodos de las clases Repository, JpaRepository, entre otras. Estas clases son brindadas por SpringBoot. Estos métodos son para obtener y/o manipular objetos que tengamos definidos de forma sencilla.
###### Si consideramos la interfaz UsuarioRepo, la misma extiende los métodos de JpaRepository, la cual precisa que se le pase, la clase de la entidad a la cual va a manipular y tipo de dato asociado al ID de dicha entidad. --> public interface UsuarioRepo extends JpaRepository<Usuario, String>. Adicionalmente, se le pueden agregar nuevos métodos al repositorio para que busque, agregue, modifique o elimine datos de una forma mas específica.
Luego de definir la interfaz que va a actuar de Repositorio, se deben definir los servicios. Los servicios son las operaciones que se comunican con las entidades (con los datos). Para crear estas operaciones se debe crear lo siguiente:
######
a) Se debe definir una interfaz, la cual va a contener el cabezal de las operaciones (Por ejemplo, UsuarioServicio.java)
######
b) Se debe definir una clase, la cual va a implementar las operaciones definidas en la interfaz, previamente creada, y a la clase se la va a identificar como @Service. (Por ejemplo, UsuarioServicioImpl.java). Todas las operaciones deben llevar @Override y @Transactional. Cabe aclarar que todos los accesos a los datos se hacen a traves del repositorio creado (en este caso UsuarioRepo.java) y para ser utilizado se debe definir un atributo de tipo UsuarioRepo con la etiqueta @Autowired.
###### @Service se encarga de gestionar las operaciones de negocio más importantes a nivel de la aplicación y aglutina llamadas a varios repositorios de forma simultánea. Su tarea fundamental es la de agregador.
###### @Override le permite al compilador de Java saber que deseas anular un método existente de una clase primaria (es decir, sobrescribirá cómo funciona una función en esta clase mientras exista en la clase primaria).
###### @Transactional es un metadato que especifica que una interfaz, clase o método debe tener semántica transaccional.
###### @Autowired permite inyectar unas dependencias con otras dentro de Spring.
Las operaciones que se definieron dentro de las clases con etiqueta @Service, van a ser utilizadas en los Controladores. En los controladores se van a definir las operaciones que va a realizar cada EndPoint de la API REST.
######
Los controladores son clases que tienen las etiquetas @RestController y @RequestMapping. En el controlador se tiene que instanciar el objeto servicio que vamos a estar utilizando, en este caso, UsuarioServicio (con @Autowired). Esto se hace ya que para acceder a los datos tenemos que utilizar las operaciones definidas en el servicio.
Cada Endpoint se va a identificar con @PostMapping, @GetMapping, @PutMapping, @DeleteMapping, etc. El Endpoint puede devolver algo de tipo ResponseEntity, o cualquier objeto Java. (Ejemplo: UsuarioControlador.java)
###### @RestController es una anotación de conveniencia para crear controladores Restful. Es una especialización de @Component y se detecta automáticamente a través del escaneo de rutas de clases. Agrega las anotaciones @Controller y @ResponseBody. Convierte la respuesta a JSON o XML. 
###### @RequestMapping es una anotación en el marco de Spring que nos permite mapear solicitudes HTTP con métodos que deseamos ejecutar.
###### @PostMapping, @GetMapping, @PutMapping, @DeleteMapping, etc, indican que tipo de Request va a ser la operación.
###### ResponseEntity extiende o hereda de la clase HttpEntity y agrega un HttpStatus (código de estado). Normalmente usada en los servicios REST dentro de los métodos de controladores. ResponseEntity maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos de estado permitiéndonos total libertad de configurar la respuesta que queremos que se envié desde nuestros endpoints.

### 2.- ¿Cómo crear entidades en Java?
Se debe definir una clase y debe implementar del tipo Serializable. Se debe agregar la etiqueta @Entity a la clase. Se deben definir los atributos de la clase, y al menos uno de ellos, debe tener la etiqueta @Id.
######
Si se agrega la etiqueta @Table se puede configurar, nombre de tabla que se creará, indices, entre otras cosas. Ejemplo: Usuario.java.
###### @Data esta etiqueta pertenece a la libreria lombok y la misma hace que se "generen" los getters y setters automaticamente (no se muestran en el código).

### 3.- ¿Cómo relacionar entidades en Java? 
#### 3a.- Relación 1 - N Débil (Entidad A (1) -> Entidad B (N))
Primero se debe crear la entidad A. Luego se debe crear una clase que va a contener la clave primaria de la entidad debil (clave primaria entidad A + clave primaria entidad B). Esta clase debe tener la etiqueta @Embeddable y NO debe tener la etiqueta @Entity.
######
Por último, se crea la clase de la entidad débil. Esta clase va a tener la etiqueta @Entity. Definir un atributo del tipo de la clase que tiene la clave primaria de la entidad débil y al mismo asignarle la etiqueta @EmbeddedId. Adicionalmente, se debe realizar la asociación entre las entidades B y A. En la entidad B se debe definir un atributo del tipo de la entidad A y agregarle las etiquetas @ManyToOne y @JoinColumn. En esta última se especifica que columna hace el join entre las tablas. 
######
Ejemplo Relación 1 - N Débil: Categoría (1) -> SubCategoría (N). La clase SubCategoriaPK es la clave primaria de la entidad (tabla) SubCategoría.
 




