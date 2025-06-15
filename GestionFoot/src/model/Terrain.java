package model;

public class Terrain {
    private String nom;
    private String statut;

    public Terrain(String nom, String statut) {
        this.nom = nom;
        this.statut = statut;
    }

    public String getNom() {
        return nom;
    }

    public String getStatut() {
        return statut;
    }
}