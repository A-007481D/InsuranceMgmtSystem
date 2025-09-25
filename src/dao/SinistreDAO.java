package dao;

import model.Sinistre;

import java.util.List;

public interface SinistreDAO extends GenericDAO<Sinistre> {


    List<Sinistre> findByContratId(Long contratId);

    List<Sinistre> findByClientId(Long clientId);
}