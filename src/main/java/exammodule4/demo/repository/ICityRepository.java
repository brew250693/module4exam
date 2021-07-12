package exammodule4.demo.repository;

import exammodule4.demo.model.City;
import org.springframework.data.repository.CrudRepository;

public interface ICityRepository extends CrudRepository <City,Long> {
}
