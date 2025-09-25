package service;

import model.Contrat;

import java.util.List;
import java.util.Optional;

public interface ContratService {
    Contrat create(Contrat c );
    List<Contrat> findAll();
    Optional<Contrat> findById(Long id);
    boolean deleteById(Long id);
    List<Contrat> findByClientId(Long clientId);
}