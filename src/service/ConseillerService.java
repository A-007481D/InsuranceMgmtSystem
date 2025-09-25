package service;

import model.Conseiller;

import java.util.List;
import java.util.Optional;

public interface ConseillerService{
    Conseiller create(Conseiller c);
    Optional<Conseiller> findById(Long id);
    List<Conseiller> findAll();
    boolean deleteById(Long id);



}