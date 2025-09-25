package controller;
import model.Client;
import service.ClientService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientController {
    private final ClientService service = new service.impl.ClientServiceImpl();

    public Client create(Client c) {
        return service.create(c);
    }

    public boolean delete(Long id) {
        return service.deleteById(id);
    }

    public Optional<Client> findById(Long id) {
        return service.findById(id);
    }

    public List<Client> findByNomSorted(String nom) {
        return service.findByNomSorted(nom);
    }

    public List<Client> findByConseillerId(Long id) {
        return service.findByConseillerId(id);
    }
}
