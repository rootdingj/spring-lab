package com.spring.data.cache.cache.repository;

import com.spring.data.cache.cache.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
