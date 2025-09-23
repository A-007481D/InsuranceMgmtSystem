package model;

import enums.TypeSinistre;
import java.time.LocalDateTime;


public class Sinistre {
    private Long id;
    private TypeSinistre typeSinistre;
    private LocalDateTime dateTime;
    private Double cout;
    private String description;
    private Long contratId;


    public Sinistre() {

    }


    public Sinistre(Long id, TypeSinistre typeSinistre, LocalDateTime dateTime, Double cout, String description, Long contratId) {
        this.id = id;
        this.typeSinistre = typeSinistre;
        this.dateTime = dateTime;
        this.cout = cout;
        this.description = description;
        this.contratId = contratId;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TypeSinistre getTypeSinistre() { return typeSinistre; }
    public void setTypeSinistre(TypeSinistre typeSinistre) { this.typeSinistre = typeSinistre; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getContratId() { return contratId; }
    public void setContratId(Long contratId) { this.contratId = contratId; }


    @Override
    public String toString() {
        return String.format("%d - %s | %s | %.2f | ContratId=%d | %s", id, typeSinistre, dateTime, cout, contratId, description);
    }
}