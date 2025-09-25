package controller;


import model.Contrat;
import service.ContratService;
import service.impl.ContratServiceImpl;


import java.util.List;
import java.util.Optional;


public class ContratController {
    private final ContratService service = new ContratServiceImpl();


    public Contrat create(Contrat c) { return service.create(c); }
    public Optional<Contrat> findById(Long id) { return service.findById(id); }
    public List<Contrat> findAll() { return service.findAll(); }
    public boolean delete(Long id) { return service.deleteById(id); }
    public List<Contrat> findByClientId(Long clientId) { return service.findByClientId(clientId); }
}