package services;

import dto.RequestDessertDto;
import dto.ResponseDessertDto;
import entity.Dessert;
import entity.User;
import exceptions.NoDessertFoundException;
import exceptions.NoIdFoundBadRequestException;
import exceptions.NoIdFoundException;
import exceptions.NoRegistersFoundException;
import org.springframework.stereotype.Service;
import repository.DessertRepository;
import repository.UserRepository;

import java.util.List;

@Service
public class DessertService {

    private final DessertRepository DESSERT_REPOSITORY;
    private final UserRepository USER_REPOSITORY;

    public DessertService(DessertRepository dessertRepository, UserRepository userRepository) {
        this.DESSERT_REPOSITORY = dessertRepository;
        this.USER_REPOSITORY = userRepository;
    }

    // POST: Create a new Dessert
    public ResponseDessertDto createDessert(RequestDessertDto dessertDto) {
        User creator = USER_REPOSITORY.findById(dessertDto.getCreatorId())
                .orElseThrow(() -> new NoIdFoundBadRequestException(dessertDto.getCreatorId()));

        Dessert newDessert = dessertDto.toEntity(creator);
        Dessert savedDessert = DESSERT_REPOSITORY.save(newDessert);
        return ResponseDessertDto.fromEntity(savedDessert);
    }

    // GET: Retrieve all Desserts
    public List<ResponseDessertDto> findAllDesserts() {
        List<Dessert> desserts = DESSERT_REPOSITORY.findAll();
        if (desserts.isEmpty()) {
            throw new NoRegistersFoundException();
        }
        return desserts.stream()
                .map(ResponseDessertDto::fromEntity)
                .toList();
    }

    // GET: Retrieve a Dessert by ID
    public ResponseDessertDto findDessertById(Long id) {
        Dessert dessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        return ResponseDessertDto.fromEntity(dessert);
    }

    // PUT: Update a Dessert
    public ResponseDessertDto updateDessert(Long id, RequestDessertDto request) {
        Dessert existingDessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        User creator = USER_REPOSITORY.findById(request.getCreatorId())
                .orElseThrow(() -> new NoIdFoundBadRequestException(request.getCreatorId()));

        existingDessert.setName(request.getName());
        existingDessert.setDescription(request.getDescription());
        existingDessert.setCountryOfOrigin(request.getCountryOfOrigin());
        existingDessert.setIngredients(request.getIngredients());
        existingDessert.setImage(request.getImage());
        existingDessert.setCreator(creator);

        Dessert updatedDessert = DESSERT_REPOSITORY.save(existingDessert);
        return ResponseDessertDto.fromEntity(updatedDessert);
    }

    // DELETE: Delete a Dessert by ID
    public void deleteDessertById(Long id) {
        Dessert dessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        DESSERT_REPOSITORY.deleteById(id);
    }

    // GET: Retrieve Desserts by Creator ID
    public List<ResponseDessertDto> findDessertsByCreatorId(Long creatorId) {
        List<Dessert> desserts = DESSERT_REPOSITORY.findByCreatorId(creatorId);
        if (desserts.isEmpty()) {
            throw new NoDessertFoundException(creatorId);
        }
        return desserts.stream()
                .map(ResponseDessertDto::fromEntity)
                .toList();
    }
}
