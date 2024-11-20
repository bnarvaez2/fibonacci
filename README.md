# Fibonacci Web Service

Este proyecto es un servicio web que calcula la secuencia de Fibonacci. Está construido con **Spring Boot** y **Docker**.

## Descripción

El servicio web expone una API REST que permite calcular la secuencia de Fibonacci hasta un número dado. Utiliza **Hibernate** para la persistencia de datos y está configurado para desplegarse en **Render**.

## Características

- **API REST**: Endpoints para calcular la secuencia de Fibonacci.
- **Persistencia**: Uso de Hibernate para la gestión de datos.
- **Despliegue**: Configuración para desplegar en Render con Docker.

## Requisitos

- **Java 11** o superior
- **Maven** 3.6.3 o superior
- **Docker**

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/bnarvaez2/fibonacci.git
    cd fibonacci
    ```

2. Construye el proyecto con Maven:
    ```bash
    mvn clean install
    ```

3. Construye la imagen de Docker:
    ```bash
    docker build -t fibonacci-service .
    ```

4. Ejecuta el contenedor de Docker:
    ```bash
    docker run -p 8080:8080 fibonacci-service
    ```

## Documentación

[Accede a la documentación de la API en https://fibonacci-qs19.onrender.com/swagger-ui/index.html](https://fibonacci-qs19.onrender.com/swagger-ui/index.html)
