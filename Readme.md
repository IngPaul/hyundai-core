# Hyundai Core API

Este es el repositorio oficial de la API Hyundai Core.

## Requisitos

Antes de ejecutar esta API, asegúrate de tener instalados los siguientes requisitos:

- Java 17
- Docker
- Maven
- Git Bash

## Pasos para ejecutar la API en Docker

Sigue estos pasos para ejecutar la API Hyundai Core en Docker:

1. Abre Git Bash.
2. Navega hasta la carpeta del proyecto.
3. Ejecuta el siguiente comando: `sh build-and-deploy.sh`

## Pasos para ejecutar la API localmente

Sigue estos pasos para ejecutar la API Hyundai Core de forma local:

1. Asegúrate de tener una base de datos llamada `purchase_hyu_db` en PostgreSQL, además configura de ser necesario las credenciales en el archivo `application-local.properties`.
2. Asegurate de tener una base redis de forma local y de configurar las credenciales en el archivo `application-local.properties`.
3. Abre el proyecto utilizando un IDE como IntelliJ.
4. Ejecuta el proyecto para iniciarlo.

## Endpoints del API

A continuación se detallan los endpoints disponibles en el contrato Swagger de esta API:

### Retrieve vehicles version by model and cryptocurrency <span style="color:red"><ins><em>(Conversion)</em></ins></span>

- URL: `POST /vehicle-models/retrieve`
- Descripción: Recupera las versiones de los vehículos por modelo y criptomoneda.
- Parámetros de solicitud:
    - `model` (string, requerido): Modelo del vehículo.
    - `cryptoCurrency` (string, requerido): Valor del modelo del vehículo en criptomoneda.
- Códigos de respuesta:
    - `201`: Éxito en la creación del cliente.
    - `400`: Solicitud incorrecta.
    - `401`: No autorizado.
    - `403`: Prohibido.
    - `404`: No encontrado.
    - `500`: Error interno del servidor.

### Purchase vehicle <span style="color:red"><ins><em>(Compra)</em></ins></span>

- URL: `POST /vehicle-models/purchase`
- Descripción: Realiza la compra de un vehículo.
- Parámetros de solicitud:
    - `convertionId` (string, requerido): ID de conversión.
    - `fullName` (string, requerido): Nombre completo del cliente.
    - `version` (string, requerido): Versión del vehículo.
    - `model` (string, requerido): Modelo del vehículo.
    - `cryptocurrency` (string, requerido): Criptomoneda utilizada para la compra.
- Códigos de respuesta:
    - `201`: Éxito en la creación del cliente.
    - `400`: Solicitud incorrecta.
    - `401`: No autorizado.
    - `403`: Prohibido.
    - `404`: No encontrado.
    - `500`: Error interno del servidor.

### Purchase report <span style="color:red"><ins><em>(Reporte)</em></ins></span>

- URL: `POST /vehicle-models/purchase/report`
- Descripción: Genera un informe de compras de vehículos.
- Parámetros de solicitud:
    - `date` (string, requerido): Fecha para generar el informe (formato: 'YYYY-MM-DD').
    - `model` (string, requerido): Modelo del vehículo.
    - `cryptocurrency` (string, requerido): Criptomoneda utilizada para la compra.
- Códigos de respuesta:
    - `201`: Éxito en la creación del cliente.
    - `400`: Solicitud incorrecta.
    - `401`: No autorizado.
    - `403`: Prohibido.
    - `404`: No encontrado.
    - `500`: Error interno del servidor.
## 

## Contacto

Para más información, puedes ponerte en contacto con:

- Nombre: Paul Cuichan
- Email: pcuichan@pichincha.com

## Entregables adicionales

- <span style="color:orange; font-weight:bold;">La colección de consultas de Postman se encuentra en la carpeta `resources/consultas.postman_collection.json`.</span>
- El script de la base de datos se encuentra en la carpeta `resources/db/schema.sql`.

