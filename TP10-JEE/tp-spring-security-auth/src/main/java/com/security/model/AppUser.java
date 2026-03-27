package com.security.model;

// FR: Importation des collections pour gérer les ensembles de rôles
// EN: Import collections to manage role sets
import java.util.Set;

/**
 * FR: Classe représentant un utilisateur de l'application
 *     Cette classe est utilisée comme modèle de données pour stocker
 *     les informations des utilisateurs en mémoire
 *
 * EN: Class representing an application user
 *     This class is used as a data model to store user information in memory
 */
public class AppUser {

    // FR: Identifiant unique de l'utilisateur
    // EN: Unique user identifier
    private Long id;

    // FR: Nom d'utilisateur utilisé pour l'authentification
    // EN: Username used for authentication
    private String username;

    // FR: Mot de passe (en clair pour le TP, normalement crypté)
    // EN: Password (plain text for this TP, normally encrypted)
    private String password;

    // FR: Nom complet de l'utilisateur pour l'affichage
    // EN: Full name of the user for display
    private String fullName;

    // FR: Adresse email de l'utilisateur
    // EN: User email address
    private String email;

    // FR: Ensemble des rôles attribués à l'utilisateur
    //     Exemples: ADMINISTRATOR, MANAGER, STANDARD
    // EN: Set of roles assigned to the user
    //     Examples: ADMINISTRATOR, MANAGER, STANDARD
    private Set<String> roles;

    // FR: Statut du compte (true = actif, false = désactivé)
    // EN: Account status (true = active, false = disabled)
    private boolean active;

    /**
     * FR: Constructeur par défaut requis par Spring
     * EN: Default constructor required by Spring
     */
    public AppUser() {}

    /**
     * FR: Constructeur avec tous les paramètres pour initialiser un utilisateur complet
     * EN: Constructor with all parameters to initialize a complete user
     *
     * @param id       FR: Identifiant / EN: Identifier
     * @param username FR: Nom d'utilisateur / EN: Username
     * @param password FR: Mot de passe / EN: Password
     * @param fullName FR: Nom complet / EN: Full name
     * @param email    FR: Adresse email / EN: Email address
     * @param roles    FR: Ensemble des rôles / EN: Set of roles
     * @param active   FR: Statut du compte / EN: Account status
     */
    public AppUser(Long id, String username, String password, String fullName,
                   String email, Set<String> roles, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.roles = roles;
        this.active = active;
    }

    // ============================================================
    // FR: GETTERS - Méthodes pour récupérer les valeurs des attributs
    // EN: GETTERS - Methods to retrieve attribute values
    // ============================================================

    /**
     * FR: Récupère l'identifiant de l'utilisateur
     * EN: Gets the user identifier
     * @return Long FR: L'identifiant / EN: The identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * FR: Récupère le nom d'utilisateur
     * EN: Gets the username
     * @return String FR: Le nom d'utilisateur / EN: The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * FR: Récupère le mot de passe
     * EN: Gets the password
     * @return String FR: Le mot de passe / EN: The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * FR: Récupère le nom complet
     * EN: Gets the full name
     * @return String FR: Le nom complet / EN: The full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * FR: Récupère l'adresse email
     * EN: Gets the email address
     * @return String FR: L'adresse email / EN: The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * FR: Récupère l'ensemble des rôles
     * EN: Gets the set of roles
     * @return Set<String> FR: Les rôles / EN: The roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * FR: Vérifie si le compte est actif
     * EN: Checks if the account is active
     * @return boolean FR: true si actif / EN: true if active
     */
    public boolean isActive() {
        return active;
    }

    // ============================================================
    // FR: SETTERS - Méthodes pour modifier les valeurs des attributs
    // EN: SETTERS - Methods to modify attribute values
    // ============================================================

    /**
     * FR: Définit l'identifiant de l'utilisateur
     * EN: Sets the user identifier
     * @param id FR: Le nouvel identifiant / EN: The new identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * FR: Définit le nom d'utilisateur
     * EN: Sets the username
     * @param username FR: Le nouveau nom d'utilisateur / EN: The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * FR: Définit le mot de passe
     * EN: Sets the password
     * @param password FR: Le nouveau mot de passe / EN: The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * FR: Définit le nom complet
     * EN: Sets the full name
     * @param fullName FR: Le nouveau nom complet / EN: The new full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * FR: Définit l'adresse email
     * EN: Sets the email address
     * @param email FR: La nouvelle adresse email / EN: The new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * FR: Définit l'ensemble des rôles
     * EN: Sets the set of roles
     * @param roles FR: Les nouveaux rôles / EN: The new roles
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * FR: Définit le statut du compte
     * EN: Sets the account status
     * @param active FR: true pour actif / EN: true for active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    // ============================================================
    // FR: MÉTHODES UTILITAIRES
    // EN: UTILITY METHODS
    // ============================================================

    /**
     * FR: Retourne une représentation textuelle de l'utilisateur
     * EN: Returns a string representation of the user
     *
     * @return String FR: Représentation de l'utilisateur
     *                EN: User representation
     */
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", active=" + active +
                '}';
    }

    /**
     * FR: Vérifie si deux utilisateurs sont égaux (comparaison par ID)
     * EN: Checks if two users are equal (comparison by ID)
     *
     * @param obj FR: L'objet à comparer / EN: The object to compare
     * @return boolean FR: true si égaux / EN: true if equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppUser appUser = (AppUser) obj;
        return id != null && id.equals(appUser.id);
    }

    /**
     * FR: Calcule le code de hachage basé sur l'identifiant
     * EN: Calculates hash code based on identifier
     *
     * @return int FR: Le code de hachage / EN: The hash code
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}