package dao.impl;

import dao.ClientDAO;
import dao.ConseillerDAO;
import model.Conseiller;
import util.IDGenerator;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConseillerDAOImpl implements ConseillerDAO {
    @Override
    public Conseiller save(Conseiller c){
        if(c.getId() == null) c.setId(IDGenerator.next());
        InMemoryDataStore.CONSEILLERS.put(c.getId(),c);
        return c;
    }
    @Override
    public Optional<Conseiller> findById(Long id){
        return Optional.ofNullable(InMemoryDataStore.CONSEILLERS.get(id));
    }

    @Override

    public List<Conseiller> findAll() {
        return new ArrayList<>(InMemoryDataStore.CONSEILLERS.values());
    }

    @Override

    public boolean deleteById(Long id) {
        return InMemoryDataStore.CONSEILLERS.remove(id) != null;
    }






}