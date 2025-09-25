package service;


import model.Sinistre;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface SinistreService {
    Sinistre create(Sinistre s);
    Optional<Sinistre> findById(Long id);
    List<Sinistre> findAll();
    boolean deleteById(Long id);
    List<Sinistre> findByContratId(Long contratId);
    List<Sinistre> findByClientId(Long clientId);
    Double totalCoutByClient(Long clientId);
    List<Sinistre> findBefore(LocalDateTime dateTime);
    List<Sinistre> findByCoutGreaterThan(Double montant);
    List<Sinistre> findSortedByCoutDesc();
}