package repository;

import entity.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    Optional<Dessert> findByName(String name); // Correcto: el campo 'name' existe en la entidad
    boolean existsByDessertId(Long DessertId);              // Cambiado de existsByDessertId a existsById
    List<Dessert> findByDessertId(Long DessertId);          // Cambiado de findByDessertId a findById
}
