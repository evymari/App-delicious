package com.f5.delicious.repository;

import com.f5.delicious.entity.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator,Long> {
    Optional<Creator>findByPhone(String id);

    List<Creator> findByName(String name);
    @Query(value = "SELECT g FROM Creator g WHERE LOWER(g.name) LIKE LOWER(CONCAT(:name, '%'))")
    List<Creator> findLikeName(String name);
}
