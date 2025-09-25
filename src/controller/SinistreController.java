package controller;


import model.Sinistre;
import service.SinistreService;
import service.impl.SinistreServiceImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class SinistreController {
    private final SinistreService service = new SinistreServiceImpl();


    public Sinistre create(Sinistre s) { return service.create(s); }
    public Optional<Sinistre> findById(Long id) { return service.findById(id); }
    public List<Sinistre> findAll() { return service.findAll(); }
    public boolean delete(Long id) { return service.deleteById(id); }
    public List<Sinistre> findByContratId(Long contratId) { return service.findByContratId(contratId); }
    public List<Sinistre> findByClientId(Long clientId) { return service.findByClientId(clientId); }
    public Double totalCoutByClient(Long clientId) { return service.totalCoutByClient(clientId); }
    public List<Sinistre> findBefore(LocalDateTime dateTime) { return service.findBefore(dateTime); }
    public List<Sinistre> findBeforeDate(LocalDate date) { return findBefore(date.atStartOfDay()); }
    public List<Sinistre> findSortedByCout() {
        List<Sinistre> sinistres = findAll();
        sinistres.sort((s1, s2) -> s2.getCout().compareTo(s1.getCout()));
        return sinistres;
    }
    public List<Sinistre> findByCoutGreaterThan(Double montant) { return service.findByCoutGreaterThan(montant); }
    public List<Sinistre> findSortedByCoutDesc() { return service.findSortedByCoutDesc(); }
}