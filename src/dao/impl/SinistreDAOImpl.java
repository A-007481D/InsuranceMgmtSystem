package dao.impl;


import dao.SinistreDAO;
import model.Sinistre;
import util.IDGenerator;


import java.time.LocalDateTime;
import java.util.*;


public class SinistreDAOImpl implements SinistreDAO {
    @Override
    public Sinistre save(Sinistre s) {
        if (s.getId() == null ) s.setId(IDGenerator.next());
        InMemoryDataStore.SINISTRES.put(s.getId(), s);
        return s;
    }
    @Override
    public Optional<Sinistre> findById(Long id) {
        return Optional.ofNullable(InMemoryDataStore.SINISTRES.get(id));
    }

    @Override
    public List<Sinistre> findAll() {
        return new ArrayList<>(InMemoryDataStore.SINISTRES.values());
    }
    @Override
    public boolean deleteById(Long id) {
        return InMemoryDataStore.SINISTRES.remove(id) != null;
    }

    @Override
    public List<Sinistre> findByContratId(Long contratId) {
        return InMemoryDataStore.findSinistresByContratId(contratId);
    }

    @Override
    public List<Sinistre> findByClientId(Long clientId) {
        return InMemoryDataStore.findSinistresByClientId(clientId);
    }


    @Override
    public List<Sinistre> findBefore(LocalDateTime dateTime) {
        List<Sinistre> result = new ArrayList<>();
        for (Sinistre s : InMemoryDataStore.SINISTRES.values()) {
            if (s.getDateTime().isBefore(dateTime)) result.add(s);
        }
        return result;
    }


    @Override
    public List<Sinistre> findByCoutGreaterThan(Double montant) {
        List<Sinistre> result = new ArrayList<>();
        for (Sinistre s : InMemoryDataStore.SINISTRES.values()) {
            if (s.getCout() != null && s.getCout() > montant) result.add(s);
        }
        return result;
    }
}