package com.wouti.zoom_academia.vo;

import com.wouti.zoom_academia.entities.Utilisateur;

public class LoginResponse {
    
    private String token;
    private Utilisateur user;
    private String role;  // ⭐ Rôles du profil
    private String codeSession;  // ⭐ Code de la session
    private String referenceExterne;  // ⭐ Pour compatibilité

    public LoginResponse() {
    }

    public LoginResponse(String token, Utilisateur user, String role, String codeSession, String referenceExterne) {
        this.token = token;
        this.user = user;
        this.role = role;
        this.codeSession = codeSession;
        this.referenceExterne = referenceExterne;
    }

    // GETTERS ET SETTERS
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCodeSession() {
        return codeSession;
    }

    public void setCodeSession(String codeSession) {
        this.codeSession = codeSession;
    }

    public String getReferenceExterne() {
        return referenceExterne;
    }

    public void setReferenceExterne(String referenceExterne) {
        this.referenceExterne = referenceExterne;
    }
}