# Proyecto de Prácticas de Programación Web

Este repositorio contiene el código fuente y la documentación relacionada con el proyecto de prácticas de Programación Web, realizado como parte del curso 2023/2024.

## Descripción

El proyecto consta de tres prácticas en las que se ha desarrollado una aplicación web para la gestión de campamentos de verano. Se han aplicado conceptos de programación en Java, así como buenas prácticas de diseño y desarrollo de software.

## Tecnologías Utilizadas

- Java
- J2EE
- MySQL
- HTML5
- CSS3
- Javascript
- Maven

## Herramientas Utilizadas

- Visual Studio Code con las extensiones de RedHat para Java

## Estructura del Proyecto

El proyecto está organizado en tres prácticas, cada una con su correspondiente documentación en formato PDF y el código fuente en Java y JSP:

- Práctica 1: Iniciación a Java
- Práctica 2: Introducción a JDBC, DAO Y DTO
- Práctica 3: Desarrollo de una aplicación web

## Ejecución del Proyecto

Para ejecutar el proyecto, se requiere tener instalado:

- JDK (Java Development Kit)
- MySQL Server
- Apache Tomcat (versión 8.5)
- Maven

### Pasos para ejecutar el proyecto:

1. Clonar el repositorio:

    ```sh
    git clone https://github.com/tuusuario/proyecto-practicas-web.git
    ```

2. Importar el proyecto en Visual Studio Code o cualquier IDE de tu preferencia.
3. Iniciar y generar la base de datos MySQL con el archivo `/script/script.sql`.
4. Configurar la conexión a la base de datos en el archivo `config.properties`.
5. Ejecutar el comando Maven para compilar y empaquetar el proyecto:

    ```sh
    mvn clean package
    ```

6. Desplegar el archivo WAR generado en el directorio `target` en un servidor Apache Tomcat.

## Informe de Prácticas

Cada práctica incluye un informe en formato PDF que detalla las decisiones de diseño e implementación, así como el análisis de las dificultades encontradas y las fuentes consultadas.

## Autoevaluación

Cada miembro del equipo ha realizado una autoevaluación que se encuentra disponible en la carpeta correspondiente.
