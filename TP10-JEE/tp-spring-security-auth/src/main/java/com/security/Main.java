package com.security;

// FR: Importation de SpringApplication pour démarrer l'application Spring Boot
// EN: Import SpringApplication to start the Spring Boot application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FR: Classe principale de l'application
 *     Point d'entrée pour lancer l'application Spring Security
 *
 * EN: Main application class
 *     Entry point to launch the Spring Security application
 */
@SpringBootApplication
public class Main {

    /**
     * FR: Méthode principale qui lance l'application Spring Boot
     *     Utilise SpringApplication.run() pour initialiser le contexte
     *
     * EN: Main method that launches the Spring Boot application
     *     Uses SpringApplication.run() to initialize the context
     *
     * @param args FR: Arguments de la ligne de commande
     *             EN: Command line arguments
     */
    public static void main(String[] args) {
        // FR: Lancement de l'application avec la classe Main comme configuration
        // EN: Launch the application with Main class as configuration
        SpringApplication.run(Main.class, args);
    }
}