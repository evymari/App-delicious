package controllers;

import dto.ResponseDessertDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.DessertRepository;
import service.DessertService;

import java.util.List;

@RestController
@RequestMapping("api/dessert")
public class DessertController {

    private final DessertRepository DESSERT_REPOSITORY;

    private final DessertService DESSERT_SERVICE;

    public DessertController(DessertRepository dessertRepository, DessertService dessertService) {
        DESSERT_REPOSITORY = dessertRepository;
        DESSERT_SERVICE = dessertService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDessertDto>> getAllDessert() {
        List<ResponseDessertDto> dessert = DESSERT_SERVICE.findAllDessert();
        return new ResponseEntity<>(dessert, HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public  ResponseEntity<ResponseDessertDto> getDessertId(@PathVariable Long id) {
        ResponseDessertDto dessert = DESSERT_SERVICE.findDessertById(id);
        return new ResponseEntity<>(dessert,HttpStatus.OK);
    }


}
