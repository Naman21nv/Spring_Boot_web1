package org.example.springboot_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootWebApplication
 *
 * <p>Purpose:
 * - This class serves as the entry point for the Spring Boot application.
 * - It contains the main method which delegates to Spring Boot's SpringApplication class to bootstrap the application.
 *
 * <p>Key concepts and theory:
 * - Spring Boot: An extension of the Spring framework that eliminates boilerplate configurations required for setting up a Spring application.
 * - @SpringBootApplication: A convenience annotation that combines three commonly used annotations:
 *   1. @Configuration: Tags the class as a source of bean definitions for the application context.
 *   2. @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 *   3. @ComponentScan: Tells Spring to look for other components, configurations, and services in the current package, allowing it to find the controllers.
 *
 * <p>Execution flow:
 * 1. The JVM invokes the public static void main(String[] args) method.
 * 2. Inside the main method, SpringApplication.run() is called.
 * 3. SpringApplication bootstraps the application:
 *    - Sets up the default configuration.
 *    - Starts the Spring application context (the IoC container).
 *    - Performs class path scanning to find components (like @Controller, @Service, etc.).
 *    - Starts the embedded web server (e.g., Tomcat) if the application is a web application.
 *
 * <p>Methods:
 * - main(String[] args): The standard Java entry point. It takes command-line arguments and passes them to SpringApplication.run().
 * - SpringApplication.run(Class<?> primarySource, String... args): A static helper that can be used to run a SpringApplication from the specified source using default settings.
 */
@SpringBootApplication // Marks this class as a Spring Boot application entry point and enables auto-configuration and component scanning.
public class SpringBootWebApplication {

    /**
     * The entry point of the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Delegates the bootstrapping of the application to Spring Boot.
        // It starts the application context and the embedded web server.
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

}
