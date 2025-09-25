package controller;


import model.Conseiller;
import service.ConseillerService;
import service.impl.ConseillerServiceImpl;


import java.util.List;
import java.util.Optional;


public class ConseillerController {
    private final ConseillerService service = new ConseillerServiceImpl();


    public Conseiller create(Conseiller c) { return service.create(c); }
    public Optional<Conseiller> findById(Long id) { return service.findById(id); }
    public List<Conseiller> findAll() { return service.findAll(); }
    public boolean delete(Long id) { return service.deleteById(id); }
}