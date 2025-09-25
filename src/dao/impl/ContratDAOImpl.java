package dao.impl;

import dao.ContratDAO;
import model.Contrat;
import util.IDGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ContratDAOImpl implements ContratDAO {
    public Contrat save(Contrat c){
        if (c.getId() == null) c.setId(IDGenerator.next());
        InMemoryDataStore.CONTRATS.put(c.getId(), c);
        return c;
    }

    @Override
    public Optional<Contrat> findById(Long id) {
        return Optional.ofNullable(InMemoryDataStore.CONTRATS.get(id));
    }

    @Override
    public List<Contrat> findAll(){
        return new ArrayList<>(InMemoryDataStore.CONTRATS.values());
    }

    @Override
    public boolean deleteById(Long id) {
        return InMemoryDataStore.CONTRATS.remove(id) != null;
    }

    @Override
    public List<Contrat> findByClientId(Long clientId) {
        return InMemoryDataStore.findContratByClientId(clientId);
    }

    @Override
    public List<Contrat> findByCouttGreaterThan(Double montant) {
        return Collections.emptyList();
    }

    @Override
    public List<Contrat> findBefore(LocalDateTime dateTime) {
        return Collections.emptyList();
    }

    @Override
    public List<Contrat> findByContratId(Long contratId) {
        return Collections.emptyList();
    }


}