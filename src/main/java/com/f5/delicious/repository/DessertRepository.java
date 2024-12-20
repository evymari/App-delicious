package com.f5.delicious.repository;

import com.f5.delicious.entity.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    Optional<Dessert> findByName(String name);
    boolean existsByCreatorId(Long dessertId);
    List<Dessert> findByCreatorId(Long dessertId);


}
