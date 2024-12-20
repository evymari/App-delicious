# Dessert API

Dessert API es un servicio backend desarrollado en Java utilizando el framework Spring Boot. Proporciona una API REST para gestionar recursos relacionados con postres, incluyendo operaciones CRUD para la gestión de postres y guardianes relacionados.

---

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.0**
- **Spring Data JPA**
- **H2 Database** (para pruebas locales)
- **MySQL** (opcional para despliegue en producción)
- **Maven** (gestión de dependencias)

---

## Endpoints Disponibles

### Postres

#### Crear un postre
- **URL**: `/api/dessert`
- **Método**: POST
- **Cuerpo de la solicitud**:
```json
{
  "name": "Chocolate Cake",
  "description": "Delicious chocolate cake",
  "price": 5.99
}
```
- **Respuesta exitosa**:
```json
{
  "id": 1,
  "name": "Chocolate Cake",
  "description": "Delicious chocolate cake",
  "price": 5.99
}
```

#### Obtener todos los postres
- **URL**: `/api/dessert`
- **Método**: GET
- **Respuesta exitosa**:
```json
[
  {
    "id": 1,
    "name": "Chocolate Cake",
    "description": "Delicious chocolate cake",
    "price": 5.99
  },
  {
    "id": 2,
    "name": "Vanilla Ice Cream",
    "description": "Creamy vanilla ice cream",
    "price": 3.49
  }
]
```

#### Actualizar un postre
- **URL**: `/api/dessert/{id}`
- **Método**: PUT
- **Cuerpo de la solicitud**:
```json
{
  "name": "Updated Dessert",
  "description": "Updated description",
  "price": 6.49
}
```
- **Respuesta exitosa**:
```json
{
  "id": 1,
  "name": "Updated Dessert",
  "description": "Updated description",
  "price": 6.49
}
```

#### Eliminar un postre
- **URL**: `/api/dessert/{id}`
- **Método**: DELETE
- **Respuesta exitosa**:
```json
{
  "message": "Dessert with id 1 deleted successfully"
}
```

---

## Instalación y Configuración

### Prerrequisitos

1. **Java 17** instalado en tu máquina.
2. **Maven** para gestionar las dependencias.
3. **MySQL** (opcional, solo para producción).

### Pasos de Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/dessert-api.git
   cd dessert-api
   ```

2. Configura las propiedades de la base de datos en el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/dessert_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ````

3. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```

4. Accede a la API en: [http://localhost:8080/api/dessert](http://localhost:8080/api/dessert)

---

## Pruebas

### Pruebas Unitarias
Las pruebas unitarias están desarrolladas utilizando **JUnit**. Para ejecutarlas:
```bash
mvn test
```

### Colección de Postman
Una colección de Postman para probar los endpoints está disponible en el archivo `Dessert-API.postman_collection.json`.

---

## Contribuciones
¡Las contribuciones son bienvenidas! Por favor, sigue estos pasos:
1. Haz un fork del proyecto.
2. Crea una rama para tu feature:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Haz un commit de tus cambios:
   ```bash
   git commit -m "Agrega nueva funcionalidad"
   ```
4. Envía un pull request.

---

## Licencia
Este proyecto está licenciado bajo la [MIT License](LICENSE).

---

## Contacto
Si tienes preguntas o sugerencias, no dudes en contactarme:
- **Correo**: tu-email@ejemplo.com
- **GitHub**: [tu-usuario](https://github.com/tu-usuario)


