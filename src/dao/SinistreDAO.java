package dao;

import model.Sinistre;

import java.time.LocalDateTime;
import java.util.List;

public interface SinistreDAO extends GenericDAO<Sinistre> {


    List<Sinistre> findByContratId(Long contratId);

    List<Sinistre> findByClientId(Long clientId);

    List<Sinistre> findBefore(LocalDateTime dateTime);

    List<Sinistre> findByCoutGreaterThan(Double montant);
}