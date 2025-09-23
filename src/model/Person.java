package model;


public abstract class Person {
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    public Person() {
        // khawi
    }
    protected Person(Long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Long getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom; }
    public String getEmail(){
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%d - %s %s <%s>", id, prenom, nom, email);
    }
}