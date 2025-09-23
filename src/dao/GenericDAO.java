package dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
    boolean deleteById(Long id);
}
