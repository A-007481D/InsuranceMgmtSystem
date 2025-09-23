package model;

import enums.TypeContrat;
import java.time.LocalDate;


public class Contrat {
    private Long id;
    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long clientId;


    public Contrat() {

    }


    public Contrat(Long id, TypeContrat typeContrat, LocalDate dateDebut, LocalDate dateFin, Long clientId) {
        this.id = id;
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.clientId = clientId;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TypeContrat getTypeContrat() { return typeContrat; }
    public void setTypeContrat(TypeContrat typeContrat) { this.typeContrat = typeContrat; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }


    @Override
    public String toString() {
        return String.format("%d - %s | %s to %s | ClientId=%d", id, typeContrat, dateDebut, dateFin, clientId);
    }
}