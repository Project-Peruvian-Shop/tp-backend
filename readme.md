# Tuberias Peruanito

## Descripción del Proyecto

Tuberias Peruanito, una plataforma de cotizaciones de tuberías. Este
servicio, desarrollado en Java con Spring Boot, proporciona una API robusta para gestionar las funcionalidades
principales de la plataforma, incluyendo la administración de productos, categorías, usuarios y cotizaciones.

Diseñado para ser escalable, seguro y de fácil despliegue, el servicio utiliza Docker para la contenedorización y MySQL
como base de datos, asegurando una configuración consistente y confiable tanto en desarrollo como en producción.

## Endpoints Disponibles

La documentación de la API Swagger se puede acceder en `http://localhost:8080/swagger-ui/index.html`.

## Ejecutar Tuberias Peruanito Service Localmente con Docker

Esta sección explica cómo ejecutar **Tuberias Peruanito** localmente utilizando Docker. El archivo
`docker-compose.yml` provisiona tanto la base de datos MySQL como la aplicación Spring Boot en contenedores separados,
permitiendo una configuración rápida y sencilla en tu entorno local.

### Requisitos

Para ejecutar esta aplicación, asegúrate de tener las siguientes dependencias instaladas:

- **Docker Engine**: versión 20.10.7 o superior.
    - Puedes instalar Docker Engine desde el [sitio web de Docker](https://docs.docker.com/engine/install/).
- **Docker Compose**: versión 1.29.2 o superior.
    - Puedes instalar Docker Compose desde el [sitio web de Docker](https://docs.docker.com/compose/install/).
- **Java 17**: Necesario para ejecutar la aplicación.
- **Maven 3.6** o superior: Usado para construir el proyecto y gestionar sus dependencias.

### Clonar el Repositorio

Clona el repositorio y accede al directorio del proyecto:

```bash
git clone https://github.com/Project-Peruvian-Shop/shop-backend
cd backend
```

### Pasos para Ejecutar Localmente

Antes de comenzar, asegúrate de que el `daemon` de Docker esté ejecutándose en tu máquina o abre **Docker Desktop** para
iniciarlo.

1. **Construir e Iniciar los Contenedores**:
   Para iniciar la aplicación localmente con Docker, ejecuta el siguiente comando:

   ```bash
   docker compose -f docker-compose.yml up --build -d
   ```

   Este comando:
    - Construirá la aplicación Spring Boot usando el `Dockerfile`.
    - Iniciará la base de datos MySQL.
    - Iniciará la aplicación Spring Boot y la conectará a la base de datos MySQL.

### Probar la API

Para probar los endpoints de la API, se incluye una **colección de Postman** con solicitudes preconfiguradas para todos
los endpoints disponibles.

### Detener la Aplicación

Para detener los contenedores en ejecución, utiliza el siguiente comando:

```bash
docker compose -f docker-compose.yml down
```

Esto detendrá y eliminará los contenedores, pero mantendrá intactos los datos de la base de datos en el volumen.

### Eliminar Todos los Recursos

Si deseas detener los contenedores y también eliminar los recursos asociados, como volúmenes, redes e imágenes creadas
por Docker Compose, ejecuta:

```bash
docker compose -f docker-compose.yml down --volumes --rmi all
```

Este comando limpiará todos los recursos, incluidos los datos de MySQL que se hayan persistido.

### Resumen

Al usar Docker y Docker Compose, puedes ejecutar rápidamente y de manera sencilla **Tuberias Peruanito** en tu
entorno local sin necesidad de configurar manualmente la base de datos o el entorno. La aplicación está completamente
containerizada, lo que facilita la prueba, el desarrollo y la implementación en cualquier entorno.
