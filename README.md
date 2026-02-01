# MineCrafteos
**Índice**

1. [Introducción](#id1)

2. [Manual técnico para desarrolladores](#id2)

	2.1 [Requisitos previos](#id2.1)

	2.2 [Estructura](#id2.2)

	2.3 [Metodología](#id2.3)

	2.4 [Ejecución del proyecto](#id2.4)

	2.5 [Manejo de errores](#id2.5)

3. [Manual de usuario](#id3)

4. [Reparto de tareas](#id4)

5. [Mejoras](#id5)

6. [Conclusiones](#id6)

7. [Autores](#id7)

## Introducción<a name="id1"></a>

Nuestra aplicación recoge datos de una base de datos de objetos de minecraft, y nos da información sobre ellos, como son el nombre, su imagen y su modo de obtención.

Nos permite tambien crear objetos, añadiendolos a la base de datos, pero habiendose registrado el usuario previamente en la aplicación y teniendo la sesión iniciada.


El proyecto ha sido desarrollado como parte del módulo de **Acceso a Datos** de 2º de DAM, aplicando conceptos como:

- Arquitectura MVC.
- Persistencia de datos.
- Acceso a base de datos mediante JDBC.
- Manejo de errores.
- Programación orientada a objetos.


## Manual técnico para desarrolladores<a name="id2"></a>

### Requisitos previos<a name="id2.1"></a>

- El proyecto se ha desarrollado usando **Java SE 17**, usando **ANT** por lo tanto necesitaremos una versión igual o superior para poder ejecutarlo.
- **NetBeans** ha sido el IDE usado para su desarrollo.
- Es necesario disponer de una base de datos **MySQL** con los datos de los objetos.   
  El programa usa un **conector JDBC** para la conexión con la base de datos  

### Estructura<a name="id2.2"></a>

El proyecto está planteado siguiendo el patrón MVC (Modelo-Vista-Controlador), sin embargo hemos necesitado de añadir servicios para gestionar la conexión con la base de datos y el acceso a los mismos.

Nuestro proyecto cuenta con los siguientes paquetes:


#### Modelo

El modelo contiene los datos del programa y define cómo estos deben ser manipulados, es decir, contiene la lógica que se necesita para gestionar el estado y las reglas del negocio. Interactúa respondiendo a las solicitudes del controlador para acceder o actualizar los datos. Notifica indirectamente a la vista cuando los datos han cambiado para que se actualice la presentación.

#### Controlador

El controlador recibe las entradas del usuario desde la vista y las traduce en acciones que el modelo debe ejecutar. Se encarga de interpretar las acciones del usuario, manejar los eventos, y de actualizar tanto el modelo como la vista.

#### Vista

Se encarga de la visualización de los datos del modelo de manera que el usuario los entienda. No contiene lógica de negocio, solo muestra lo que el modelo le proporciona.. La vista recibe entradas del usuario (clics, teclas, etc.) y las envía al controlador.

#### Servicio

Se encarga de gestionar la conexión con la base de datos, así como de realizar el CRUD entre el programa y ella.


### Metodología<a name="id2.3"></a>

El desarrollo del proyecto se realizó siguiendo una metodología incremental:

1. Análisis del problema.  
2. Diseño de la arquitectura.  
3. Implementación del modelo.  
4. Creación de la interfaz gráfica.  
5. Desarrollo de controladores.  
6. Implementación de la base de datos.  
7. Integración.  
8. Pruebas.  
9. Documentación.  

Se utilizó **Git** como sistema de control de versiones.

---

### Ejecución del proyecto<a name="id2.4"></a>

Desde NetBeans:

1. Abrir NetBeans.  
2. File → Open Project.  
3. Seleccionar la carpeta del proyecto.  
4. Configurar JDK 17.  
5. Configurar el driver JDBC.  
6. Ejecutar la clase `Main`.  

Desde terminal (si se genera JAR):

```bash
java -jar MineCrafteos.jar
```

### Manejo de errores<a name="id2.5"></a>

Los errores de sql se manejan en la clase DBManager, usando try-catch y excepciones.

Los errores de introduccion de datos por parte del usuario, se manejan en los controladores, empleando recursos como JOptionPane.

## Manual de usuario<a name="id3"></a>

## Reparto de tareas<a name="id4"></a>

- Conjuntamente, concretamos el pliego del proyecto, así como las ideas generales de la base de datos y la creación de los modelos.
- Axel(22 horas)
  - Realización de la vista principal, vista de buscar objeto, e implementación de imágenes, así como elaboración e implementación del gif la ventana de inicio.
- Carlos(22 horas)
	- Pulido de modelos ER y Relacional, realización del script de la base de datos, creación e implementación del servicio de conexión con la base de datos y creacion e implementación de la gestión de los usuarios
- Breogán(25 horas)
  - Vista y lógica de la creación de objetos y manejo de errores, así como la integración de la vista de los usuarios con la estética del programa.

## Mejoras <a name="id5"></a>
* Concretar numero de objetos resultantes de un crafteo


## Conclusiones<a name="id6"></a>

## Autores<a name="id7"></a>
Axel Torreiro Lodeiro
Breogan Fontenla Rosende
José Carlos Domínguuez Figueiras
