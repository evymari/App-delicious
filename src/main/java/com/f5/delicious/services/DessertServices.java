package com.f5.delicious.services;
/*
import com.f5.delicious.dto.RequestDessertDto;
import com.f5.delicious.dto.ResponseDessertDto;
import com.f5.delicious.entity.Dessert;
import com.f5.delicious.entity.Creator;
import com.f5.delicious.exceptions.NoDessertsFoundException;
import com.f5.delicious.exceptions.NoIdFoundBadRequestException;
import com.f5.delicious.exceptions.NoIdFoundException;
import com.f5.delicious.exceptions.NoRegistersFoundException;
import com.f5.delicious.repository.DessertRepository;
import com.f5.delicious.repository.CreatorRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DessertServices {
    private final DessertRepository DESSERT_REPOSITORY;
    private final CreatorRepository CREATOR_REPOSITORY;

    public DessertServices(DessertRepository dessertRepository, CreatorRepository creatorRepository) {
        DESSERT_REPOSITORY = dessertRepository;
        CREATOR_REPOSITORY = creatorRepository;
    }

    // POST: Crear un nuevo postre
    public ResponseDessertDto createDessert(RequestDessertDto dessertDto) {
        Creator creator = CREATOR_REPOSITORY.findById(dessertDto.creatorId())
                .orElseThrow(() -> new NoIdFoundBadRequestException(dessertDto.creatorId()));

        Dessert newDessert = dessertDto.toEntity(creator);
        Dessert savedDessert = DESSERT_REPOSITORY.save(newDessert);
        return ResponseDessertDto.fromEntity(savedDessert);
    }

    // GET: Obtener todos los postres
    public List<ResponseDessertDto> findAllDesserts() {
        List<Dessert> desserts = DESSERT_REPOSITORY.findAll();
        if (desserts.isEmpty()) {
            throw new NoRegistersFoundException();
        }
        return desserts.stream()
                .map(ResponseDessertDto::fromEntity)
                .toList();
    }

    // GET: Obtener un postre por su ID
    public ResponseDessertDto findDessertById(Long id) {
        Dessert dessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        return ResponseDessertDto.fromEntity(dessert);
    }

    // PUT: Actualizar un postre existente
    public ResponseDessertDto updateDessert(Long id, RequestDessertDto request) {
        Dessert existingDessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        Creator creator = CREATOR_REPOSITORY.findById(request.creatorId())
                .orElseThrow(() -> new NoIdFoundBadRequestException(request.creatorId()));

        existingDessert.setName(request.name());
        existingDessert.setDescription(request.description());
        existingDessert.setCountryOfOrigin(request.countryOfOrigin());
        existingDessert.setIngredients(request.ingredients());
        existingDessert.setImage(request.image());
        existingDessert.setCreator(creator);

        Dessert updatedDessert = DESSERT_REPOSITORY.save(existingDessert);
        return ResponseDessertDto.fromEntity(updatedDessert);
    }

    // DELETE: Eliminar un postre por su ID
    public void deleteDessertById(Long id) {
        Dessert dessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        DESSERT_REPOSITORY.deleteById(id);
    }

    // GET: Obtener postres por el ID del creador
    public List<ResponseDessertDto> findDessertsByCreatorId(Long creatorId) {
        List<Dessert> desserts = DESSERT_REPOSITORY.findByCreatorId(creatorId);
        if (desserts.isEmpty()) {
            throw new NoDessertsFoundException(creatorId);
        }
        return desserts.stream()
                .map(ResponseDessertDto::fromEntity)
                .toList();
    }
}*/
