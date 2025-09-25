package service.impl;

import model.Conseiller;
import service.ConseillerService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConseillerServiceImpl implements ConseillerService {

    @Override
    public Conseiller create(Conseiller c) {
        return null;
    }

    @Override
    public Optional<Conseiller> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Conseiller> findAll() {
        return Collections.emptyList();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}