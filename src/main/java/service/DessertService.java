package service;

import dto.RequestDessertDto;
import dto.ResponseDessertDto;
import entity.Dessert;
import exceptions.NoIdFoundException;
import exceptions.NoRegistersFoundException;
import org.springframework.stereotype.Service;
import repository.DessertRepository;

import java.util.List;

@Service
public class DessertService {

    private  final DessertRepository DESSERT_REPOSITORY;

    public DessertService(DessertRepository dessertRepository) {
        DESSERT_REPOSITORY = dessertRepository;
    }
    //POST
    public ResponseDessertDto createDessert(RequestDessertDto dessertDto) {

        Dessert newDessert = dessertDto.toEntity();
        Dessert saveDessert = DESSERT_REPOSITORY.save(newDessert);
        return ResponseDessertDto.fromEntity(saveDessert);

    }


    //GET
    public List<ResponseDessertDto> findAllDessert() {
        List<Dessert> desserts = DESSERT_REPOSITORY.findAll();
        if (desserts.isEmpty()) {
            throw new NoRegistersFoundException();
        }
        return desserts.stream()
                .map(ResponseDessertDto::fromEntity)
                .toList();
    }
    //GET

    public ResponseDessertDto findDessertById(Long id) {
        Dessert dessert = DESSERT_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        return  ResponseDessertDto.fromEntity(dessert);
    }
}
