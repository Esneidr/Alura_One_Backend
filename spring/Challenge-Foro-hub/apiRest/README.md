**Foro_Hub APIREST – v1.0**
- Versión: 1.0
- Fecha: 27/02/2026
- Autor: Esneider Córdoba
- Ubicación: Medellín – Colombia

Primera versión de una API REST para la gestión de un foro académico.
Esta versión implementa autenticación con token y operaciones CRUD básicas para la entidad Tópicos.

**Tecnologías utilizadas:**
- Java 17 
- Spring Boot 4.0.3 
- PostgreSQL 
- Postman (pruebas y consultas)
- IntelliJ IDEA

**Arquitectura**

El proyecto está desarrollado como una API RESTful, sin frontend en esta versión.

⚠️ Actualmente no se incluye documentación automática (Swagger/OpenAPI), ya que SpringDoc aún no cuenta con versión compatible con Spring Boot 4.0.3.

**Seguridad**

La API implementa autenticación basada en token (JWT).
- El único endpoint público es: POST /login 
- Después de autenticarse correctamente, el sistema genera un token. 
- El token debe enviarse en los demás endpoints mediante el header: Authorization: Bearer {token}

Todos los endpoints (excepto login) están protegidos.

**Entidades del sistema**

| Campo         | Tipo          | Descripción                         |
| ------------- |---------------| ----------------------------------- |
| id            | Long          | Identificador único                 |
| autor         | String        | Usuario que crea el tópico          |
| titulo        | String        | Título del tópico (no repetido)     |
| mensaje       | String        | Contenido del mensaje (no repetido) |
| curso         | Enum          | Curso asociado                      |
| fechaCreacion | LocalDateTime | Fecha de creación                   |
| activo        | Boolean       | Indica si el tópico está activo     |

**Reglas:**
- No se puede repetir el título. 
- No se puede repetir el mensaje. 
- El DELETE no elimina físicamente, solo cambia activo = false.

**Usuario**

| Campo          | Tipo   |
| -------------- | ------ |
| id             | Long   |
| nombreCompleto | String |
| email          | String |
| password       | String |
| perfil_id      | Long   |

**Perfil**

| Campo  | Tipo   |
| ------ | ------ |
| id     | Long   |
| nombre | String |

**ENUM**

Cursos: Se implementó un ENUM para definir los cursos disponibles en los tópicos.

    BACKEND,
    DATASCIENCE,
    FRONTEND,
    IA,
    INFRASTRUCTURE,
    QA

**Endpoints implementados (Tópicos)**

| Método | Endpoint      | Descripción                                              |
| ------ | ------------- | -------------------------------------------------------- |
| POST   | /topicos      | Crear tópico                                             |
| GET    | /topicos/{id} | Obtener tópico por ID                                    |
| GET    | /topicos      | Obtener todos los tópicos activos                        |
| PUT    | /topicos/{id} | Actualizar tópico (no permite repetir título ni mensaje) |
| DELETE | /topicos/{id} | Inactivar tópico (soft delete)                           |

**Formato estándar de respuesta**

Todas las respuestas de la API retornan un StandardResponse:

`{`

    "success": boolean,
    "mensaje": string,
    "resultado": [],
    "count": Long
`}`

**Campos:**
    
    success → Indica si la operación fue exitosa
    mensaje → Mensaje descriptivo
    resultado → Objeto o lista de resultados
    count → Cantidad de registros retornados

**Pruebas**

Las pruebas fueron realizadas con: Postman

Colecciones manuales para autenticación y endpoints protegidos

**Estado del proyecto**

- Autenticación con token 
- CRUD completo para Tópicos 
- Validaciones de negocio 
- Soft Delete 
- Sin frontend 
- Sin documentación Swagger/OpenAPI

**Contexto del Proyecto**

Proyecto desarrollado como práctica del
Challenge Foro Hub – Academia Aulara Latam

Enfocado en el fortalecimiento de competencias en:

- Desarrollo backend con Spring Boot 
- Seguridad con JWT 
- Modelado de entidades
- Buenas prácticas REST
- Validaciones de negocio

