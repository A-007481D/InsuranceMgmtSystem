package model;


public class Client extends Person {
    private Long conseillerId;


    public Client() {
        super();
    }
    public Client(Long id, String nom, String prenom, String email, Long conseillerId) {
        super(id, nom, prenom, email);
        this.conseillerId = conseillerId;
    }


    public Long getConseillerId() {
        return conseillerId;
    }
    public void setConseillerId(Long conseillerId) {
        this.conseillerId = conseillerId;
    }


    @Override
    public String toString() {
        return super.toString() + " | ConseillerId=" + conseillerId;
    }
}