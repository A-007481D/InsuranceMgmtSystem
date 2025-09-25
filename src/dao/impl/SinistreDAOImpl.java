package dao.impl;

import dao.SinistreDAO;
import model.Sinistre;
import util.IDGenerator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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
        return new ArrayList<>(InMemoryDataStore.findSinistresByContratId(contratId));
    }

    @Override
    public List<Sinistre> findByClientId(Long clientId) {
        return new ArrayList<>(InMemoryDataStore.findSinistresByClientId(clientId));
    }
}