package dao;


import model.Client;
import model.Conseiller;

import java.util.List;

public interface ClientDAO extends GenericDAO<Client> {
    List<Client> findByNom(String nom);
    List<Client> findByConseillerId(Long conseillerId );
}