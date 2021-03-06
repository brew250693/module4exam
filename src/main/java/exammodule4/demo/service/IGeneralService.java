package exammodule4.demo.service;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void save (T t);
    void remove (Long id);

}
