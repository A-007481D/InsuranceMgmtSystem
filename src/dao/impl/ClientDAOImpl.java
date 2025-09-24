package dao.impl;


import dao.ClientDAO;
import dao.impl.InMemoryDataStore;
import model.Client;
import util.IDGenerator;


import java.util.*;


public class ClientDAOImpl implements ClientDAO {
    @Override
    public Client save(Client c) {
        if (c.getId() == null) c.setId(IDGenerator.next());
        InMemoryDataStore.CLIENTS.put(c.getId(), c);
        return c;
    }


    @Override
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(InMemoryDataStore.CLIENTS.get(id));
    }


    @Override
    public List<Client> findAll() {
        return new ArrayList<>(InMemoryDataStore.CLIENTS.values());
    }


    @Override
    public boolean deleteById(Long id) {
        return InMemoryDataStore.CLIENTS.remove(id) != null;
    }


    @Override
    public List<Client> findByNom(String nom) {
        return InMemoryDataStore.findClientsByNom(nom);
    }


    @Override
    public List<Client> findByConseillerId(Long conseillerId) {
        return InMemoryDataStore.findClientsByConseillerId(conseillerId);
    }
}