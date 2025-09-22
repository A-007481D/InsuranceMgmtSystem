package model;

public abstract class Person {
    private String nom;
    private String prenom;
    private String email;

    public Person(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom(){ return nom; }
    public String getPrenom() {return prenom;}
    public String getEmail() { return email;}

    public void setNom(String nom) { this.nom = nom;}
    public void setPrenom(String prenom) { this.prenom = prenom;}
    public void setEmail(String email){ this.email =email;}


    public String toString() {
        return nom + " " + prenom + " (" + email + ")";
    }













}