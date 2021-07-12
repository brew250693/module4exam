package exammodule4.demo.repository;

import exammodule4.demo.model.Nation;
import org.springframework.data.repository.CrudRepository;

public interface INationRepository extends CrudRepository <Nation,Long> {
}
