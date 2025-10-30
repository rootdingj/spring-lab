package com.spring.jpa.repository;

import com.spring.jpa.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends BaseRepository<Coffee, Long> {
}


