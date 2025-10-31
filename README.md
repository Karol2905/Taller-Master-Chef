

# 🍳 MasterChef API

## Descripción del proyecto

**MasterChef** es una API RESTful desarrollada en **Spring Boot** que permite gestionar recetas de cocina.
Cada receta incluye su título, ingredientes, pasos de preparación, nombre del chef, tipo de plato y temporada.

El proyecto está desplegado automáticamente en **Azure App Service** mediante un flujo **CI/CD con GitHub Actions**.

---

## Tecnologías utilizadas

* Java 17
* Spring Boot 3
* Maven
* MongoDB Atlas
* GitHub Actions (CI/CD)
* Azure Web App

---

## Instrucciones de instalación y ejecución local

### Requisitos previos

* JDK 17 o superior
* Maven 3.8+
* Conexión a internet


### Clonar el repositorio

```bash
git clone https://github.com/<tu-usuario>/Masterchef.git
cd Masterchef
```

### Construir el proyecto

```bash
mvn clean install
```

### Ejecutar la aplicación

```bash
mvn spring-boot:run
```

### Acceso local

Una vez iniciada, la API estará disponible en:
`http://localhost:8082/swagger-ui/index.html`

---

## Ejemplos de Request y Response por Endpoint

### Crear una receta

**POST** `/recipes`

```json
{
  "id": "r1",
  "title": "Ensalada César",
  "ingredients": ["Lechuga", "Pollo", "Queso parmesano", "Aderezo César"],
  "steps": ["Lava la lechuga", "Cocina el pollo", "Mezcla todo y agrega el aderezo"],
  "chefName": "Karol Estefany",
  "type": "Ensalada",
  "season": 1
}
```

**Response 201**

```json
{
  "id": "r1",
  "message": "Receta creada exitosamente"
}
```

---

### Obtener todas las recetas

**GET** `/recipes`
**Response 200**

```json
[
  {
    "id": "r1",
    "title": "Ensalada César",
    "ingredients": ["Lechuga", "Pollo", "Queso parmesano", "Aderezo César"],
    "steps": ["Lava la lechuga", "Cocina el pollo", "Mezcla todo y agrega el aderezo"],
    "chefName": "Karol Estefany",
    "type": "Ensalada",
    "season": 1
  }
]
```

---

### Eliminar una receta

**DELETE** `/recipes/{id}`
**Response 200**

```json
{
  "message": "Receta eliminada correctamente"
}
```

---

## Enlace al Swagger UI publicado en Azure

**Swagger UI:**
[https://masterchef-dosw-aue5f3d4gegwhgdv.canadacentral-01.azurewebsites.net/swagger-ui/index.html](https://masterchef-dosw-aue5f3d4gegwhgdv.canadacentral-01.azurewebsites.net/swagger-ui/index.html)

---

## CI/CD con GitHub Actions

El flujo de integración y despliegue continuo realiza:

* **CI:** Ejecución automática de pruebas al hacer *push* o *pull request* en la rama `develop`.
* **CD:** Despliegue automático en Azure al hacer *push* a la rama `main`.

Archivo de workflow: `.github/workflows/ci-cd.yml`

