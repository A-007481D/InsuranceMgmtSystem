package service;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client create(Client c);
    Optional<Client> findById(Long id);
    List<Client> findAll();
    boolean deleteById(Long id);
    List<Client> findByNomSorted(String nom);
    List<Client> findByConseillerId(Long conseillerId);
}