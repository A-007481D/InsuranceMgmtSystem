package service.impl;

import dao.impl.ClientDAOImpl;
import model.Client;
import service.ClientService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {
    private final ClientDAOImpl dao = new ClientDAOImpl();

    @Override
    public Client create(Client c) {
        return dao.save( c);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return dao.deleteById(id);
    }

    @Override
    public List<Client> findByNomSorted(String nom) {
        return dao.findByNom(nom).stream().sorted((a,b) -> a.getNom().compareToIgnoreCase(b.getNom())).collect(Collectors.toList());
    }

    @Override
    public List<Client> findByConseillerId(Long conseillerId) {
        return dao.findByConseillerId(conseillerId);
    }
}