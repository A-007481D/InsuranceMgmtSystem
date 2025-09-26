package service.impl;

import dao.impl.ClientDAOImpl;
import dao.impl.ConseillerDAOImpl;
import model.Client;
import model.Conseiller;
import service.ConseillerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConseillerServiceImpl implements ConseillerService {
    private final ConseillerDAOImpl dao = new ConseillerDAOImpl();
    private final ClientDAOImpl clientDao = new ClientDAOImpl();

    @Override
    public Conseiller create(Conseiller c) {
        return dao.save(c);
    }

    @Override
    public Optional<Conseiller> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Conseiller> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return dao.deleteById(id);
    }

    // Additional convenience: get clients of a conseiller (service-level)
    public List<Client> findClientsOfConseiller(Long conseillerId) {
        return clientDao.findAll().stream()
                .filter(cl -> conseillerId != null && conseillerId.equals(cl.getConseillerId()))
                .collect(Collectors.toList());
    }
}
