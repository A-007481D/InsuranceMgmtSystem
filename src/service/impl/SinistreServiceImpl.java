package service.impl;


import dao.impl.SinistreDAOImpl;
import model.Sinistre;
import service.SinistreService;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class SinistreServiceImpl implements SinistreService {
    private final SinistreDAOImpl dao = new SinistreDAOImpl();


    @Override
    public Sinistre create(Sinistre s) { return dao.save(s); }


    @Override
    public Optional<Sinistre> findById(Long id) { return dao.findById(id); }


    @Override
    public List<Sinistre> findAll() { return dao.findAll(); }


    @Override
    public boolean deleteById(Long id) { return dao.deleteById(id); }


    @Override
    public List<Sinistre> findByContratId(Long contratId) { return dao.findByContratId(contratId); }


    @Override
    public List<Sinistre> findByClientId(Long clientId) { return dao.findByClientId(clientId); }


    @Override
    public Double totalCoutByClient(Long clientId) {
        return findByClientId(clientId).stream().mapToDouble(s -> s.getCout() == null ? 0.0 : s.getCout()).sum();
    }


    @Override
    public List<Sinistre> findBefore(LocalDateTime dateTime) { return dao.findBefore(dateTime); }


    @Override
    public List<Sinistre> findByCoutGreaterThan(Double montant) { return dao.findByCoutGreaterThan(montant); }


    @Override
    public List<Sinistre> findSortedByCoutDesc() {
        return dao.findAll().stream().sorted(Comparator.comparing(Sinistre::getCout, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
    }
}