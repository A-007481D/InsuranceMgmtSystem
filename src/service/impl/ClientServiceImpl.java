package service.impl;

import dao.impl.ClientDAOImpl;
import model.Client;
import service.ClientService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {
    private final ClientDAOImpl dao = new ClientDAOImpl();

    @Override
    public Client create(Client c) {
        if (c.getNom() == null || c.getNom().trim().isEmpty()) throw new IllegalArgumentException("Nom required");
        return dao.save(c);
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
        // use streams per brief
        return dao.findAll().stream()
                .filter(c -> c.getNom() != null && c.getNom().equalsIgnoreCase(nom))
                .sorted(Comparator.comparing(Client::getNom, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findByConseillerId(Long conseillerId) {
        return dao.findAll().stream()
                .filter(c -> Objects.equals(c.getConseillerId(), conseillerId))
                .collect(Collectors.toList());
    }
}
