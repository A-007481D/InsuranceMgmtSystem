package dao;

import model.Contrat;

import java.time.LocalDateTime;
import java.util.List;

public interface ContratDAO extends GenericDAO<Contrat> {

    List<Contrat> findByClientId(Long clientId);
    List<Contrat> findByCouttGreaterThan(Double montant);
    List<Contrat> findBefore(LocalDateTime dateTime);
    List<Contrat> findByContratId(Long contratId);
}