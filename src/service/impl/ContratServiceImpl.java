package service.impl;

import model.Contrat;
import service.ContratService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ContratServiceImpl implements ContratService {

    @Override
    public Contrat create(Contrat c) {
        return null;
    }

    @Override
    public List<Contrat> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Contrat> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<Contrat> findByClientId(Long clientId) {
        return Collections.emptyList();
    }
}