package ar.com.frupp.gpuroi.repository;

import ar.com.frupp.gpuroi.entity.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PriceRepository extends CrudRepository<Price, String> {
    Optional<Price> findByName(String name);
}
