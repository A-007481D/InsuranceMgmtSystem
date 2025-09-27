package service.impl;

import dao.impl.ContratDAOImpl;
import model.Contrat;
import service.ContratService;

import java.util.List;
import java.util.Optional;

public class ContratServiceImpl implements ContratService {
    private final ContratDAOImpl dao = new ContratDAOImpl();

    @Override
    public Contrat create(Contrat c) {
        if (c.getDateDebut().isAfter(c.getDateFin())) throw new IllegalArgumentException("dateDebut must be before dateFin");
        return dao.save(c);
    }

    @Override
    public Optional<Contrat> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Contrat> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return dao.deleteById(id);
    }

    @Override
    public List<Contrat> findByClientId(Long clientId) {
        return dao.findByClientId(clientId);
    }
}
