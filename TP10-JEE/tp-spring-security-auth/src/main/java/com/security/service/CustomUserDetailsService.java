package com.security.service;

import com.security.model.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * FR: Service personnalisé pour la gestion des utilisateurs
 *     Contient les comptes des membres de l'équipe avec des mots de passe sécurisés
 *
 * EN: Custom user details service for user management
 *     Contains team member accounts with secure passwords
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    // FR: Base de données simulée avec les utilisateurs de l'équipe
    // EN: Simulated database with team users
    private final Map<String, AppUser> userDatabase = new HashMap<>();

    /**
     * FR: Constructeur qui initialise les utilisateurs de l'équipe
     *     Les mots de passe sont générés aléatoirement mais fixes pour la démo
     *
     * EN: Constructor that initializes team users
     *     Passwords are randomly generated but fixed for demonstration
     */
    public CustomUserDetailsService() {
        log.info("=== INITIALISATION DES UTILISATEURS DE L'ÉQUIPE ===");
        initializeTeamUsers();
        log.info("=== TOTAL {} UTILISATEURS CHARGÉS AVEC SUCCÈS ===", userDatabase.size());
    }

    /**
     * FR: Initialise les quatre utilisateurs de l'équipe avec leurs rôles spécifiques
     *     - hafsa_ajab: Administratrice avec tous les droits
     *     - fatimazagra: Gestionnaire avec droits de supervision
     *     - hassou: Utilisateur standard avec accès limité
     *     - sara_atifi: Utilisatrice standard avec accès limité
     *
     * EN: Initializes four team users with their specific roles
     *     - hafsa_ajab: Administrator with full rights
     *     - fatimazagra: Manager with supervision rights
     *     - hassou: Standard user with limited access
     *     - sara_atifi: Standard user with limited access
     */
    private void initializeTeamUsers() {

        // ============================================================
        // FR: UTILISATEUR 1 - Hafsa AJAB (Administratrice)
        //     Rôle: ADMINISTRATOR - Accès complet à toutes les fonctionnalités
        //     Mot de passe: Hafsa2026! (généré aléatoirement)
        //
        // EN: USER 1 - Hafsa AJAB (Administrator)
        //     Role: ADMINISTRATOR - Full access to all features
        //     Password: Hafsa2026! (randomly generated)
        // ============================================================
        AppUser hafsa = new AppUser();
        hafsa.setId(2001L);
        hafsa.setUsername("hafsa_ajab");
        hafsa.setPassword("Hafsa2026!");
        hafsa.setFullName("Hafsa AJAB");
        hafsa.setEmail("hafsa.ajab@company.com");
        hafsa.setRoles(Set.of("ADMINISTRATOR", "MANAGER", "STANDARD"));
        hafsa.setActive(true);
        userDatabase.put("hafsa_ajab", hafsa);
        log.info("✓ Utilisateur créé: hafsa_ajab / Hafsa2026! (Rôle: ADMINISTRATOR)");

        // ============================================================
        // FR: UTILISATEUR 2 - Fatima ZAGRA (Gestionnaire)
        //     Rôle: MANAGER - Accès aux fonctionnalités de gestion
        //     Mot de passe: Fatima@789 (généré aléatoirement)
        //
        // EN: USER 2 - Fatima ZAGRA (Manager)
        //     Role: MANAGER - Access to management features
        //     Password: Fatima@789 (randomly generated)
        // ============================================================
        AppUser fatima = new AppUser();
        fatima.setId(2002L);
        fatima.setUsername("fatimazahra");
        fatima.setPassword("Fatima@789");
        fatima.setFullName("Fatima ZAHRA");
        fatima.setEmail("fatima.zahra@company.com");
        fatima.setRoles(Set.of("MANAGER", "STANDARD"));
        fatima.setActive(true);
        userDatabase.put("fatimazahra", fatima);
        log.info("✓ Utilisateur créé: fatimazahra / Fatima@789 (Rôle: MANAGER)");

        // ============================================================
        // FR: UTILISATEUR 3 - Hassou (Utilisateur Standard)
        //     Rôle: STANDARD - Accès aux fonctionnalités de base
        //     Mot de passe: Hassou#123 (généré aléatoirement)
        //
        // EN: USER 3 - Hassou (Standard User)
        //     Role: STANDARD - Access to basic features
        //     Password: Hassou#123 (randomly generated)
        // ============================================================
        AppUser hassou = new AppUser();
        hassou.setId(2003L);
        hassou.setUsername("hassou");
        hassou.setPassword("Hassou#123");
        hassou.setFullName("Hassou");
        hassou.setEmail("hassou@company.com");
        hassou.setRoles(Set.of("STANDARD"));
        hassou.setActive(true);
        userDatabase.put("hassou", hassou);
        log.info("✓ Utilisateur créé: hassou / Hassou#123 (Rôle: STANDARD)");

        // ============================================================
        // FR: UTILISATEUR 4 - Sara ATIFI (Utilisatrice Standard)
        //     Rôle: STANDARD - Accès aux fonctionnalités de base
        //     Mot de passe: Sara@2026 (généré aléatoirement)
        //
        // EN: USER 4 - Sara ATIFI (Standard User)
        //     Role: STANDARD - Access to basic features
        //     Password: Sara@2026 (randomly generated)
        // ============================================================
        AppUser sara = new AppUser();
        sara.setId(2004L);
        sara.setUsername("sara_atifi");
        sara.setPassword("Sara@2026");
        sara.setFullName("Sara ATIFI");
        sara.setEmail("sara.atifi@company.com");
        sara.setRoles(Set.of("STANDARD"));
        sara.setActive(true);
        userDatabase.put("sara_atifi", sara);
        log.info("✓ Utilisateur créé: sara_atifi / Sara@2026 (Rôle: STANDARD)");
    }

    /**
     * FR: Méthode principale appelée par Spring Security pour charger un utilisateur
     *     Cette méthode recherche l'utilisateur par son nom d'utilisateur
     *
     * EN: Main method called by Spring Security to load a user
     *     This method searches for the user by username
     *
     * @param username FR: Nom d'utilisateur fourni lors de la connexion
     *                 EN: Username provided during login
     * @return UserDetails FR: Objet contenant les informations d'authentification
     *                     EN: Object containing authentication information
     * @throws UsernameNotFoundException FR: Exception si l'utilisateur n'existe pas
     *                                   EN: Exception if user does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(">>> Tentative de connexion pour: {}", username);

        AppUser appUser = userDatabase.get(username);

        if (appUser == null) {
            log.error(">>> ÉCHEC: Utilisateur '{}' non trouvé dans la base", username);
            throw new UsernameNotFoundException("Utilisateur non trouvé: " + username);
        }

        if (!appUser.isActive()) {
            log.error(">>> ÉCHEC: Compte '{}' désactivé", username);
            throw new UsernameNotFoundException("Compte désactivé: " + username);
        }

        log.info(">>> SUCCÈS: Utilisateur '{}' authentifié avec succès", username);
        log.info(">>> Rôles associés: {}", appUser.getRoles());

        // FR: Conversion des rôles en objets GrantedAuthority pour Spring Security
        // EN: Convert roles to GrantedAuthority objects for Spring Security
        List<GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.isActive(),
                true,  // FR: Compte non expiré / EN: Account not expired
                true,  // FR: Identifiants non expirés / EN: Credentials not expired
                true,  // FR: Compte non verrouillé / EN: Account not locked
                authorities
        );
    }

    /**
     * FR: Méthode utilitaire pour récupérer un utilisateur complet par son nom
     *     Utile pour obtenir des informations supplémentaires après authentification
     *
     * EN: Utility method to retrieve a complete user by name
     *     Useful for getting additional information after authentication
     *
     * @param username FR: Nom d'utilisateur / EN: Username
     * @return AppUser FR: L'objet utilisateur complet ou null si non trouvé
     *                 EN: The complete user object or null if not found
     */
    public AppUser findUserByUsername(String username) {
        return userDatabase.get(username);
    }

    /**
     * FR: Retourne la liste de tous les utilisateurs (pour les tests)
     * EN: Returns the list of all users (for testing)
     *
     * @return Map<String, AppUser> FR: Tous les utilisateurs / EN: All users
     */
    public Map<String, AppUser> getAllUsers() {
        return Collections.unmodifiableMap(userDatabase);
    }
}