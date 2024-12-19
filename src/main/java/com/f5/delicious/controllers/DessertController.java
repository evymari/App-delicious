package com.f5.delicious.controllers;
/*
import com.f5.delicious.dto.RequestDessertDto;
import com.f5.delicious.dto.ResponseDessertDto;
import com.f5.delicious.repository.CreatorRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.f5.delicious.repository.DessertRepository;
import com.f5.delicious.services.DessertServices;

import java.util.List;

@RestController
@RequestMapping("api/dessert")
public class DessertController {

    private final DessertRepository DESSERT_REPOSITORY;
    private final CreatorRepository CREATOR_REPOSITORY;
    private final DessertServices DESSERT_SERVICES;

    public DessertController(DessertRepository dessertRepository, CreatorRepository creatorRepository, DessertServices dessertServices) {
        DESSERT_REPOSITORY = dessertRepository;
        CREATOR_REPOSITORY = creatorRepository;
        DESSERT_SERVICES = dessertServices;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDessertDto>> getDessertList() {
        List<ResponseDessertDto> desserts = DESSERT_SERVICES.findAllDesserts();
        return new ResponseEntity<>(desserts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDessertDto> getDessertById(@PathVariable Long id) {
        ResponseDessertDto dessert = DESSERT_SERVICES.findDessertById(id);
        return new ResponseEntity<>(dessert, HttpStatus.OK);
    }

    @GetMapping(params = "creatorId")
    public ResponseEntity<List<ResponseDessertDto>> getDessertsByCreatorId(@RequestParam Long creatorId) {
        List<ResponseDessertDto> desserts = DESSERT_SERVICES.findDessertsByCreatorId(creatorId);
        return new ResponseEntity<>(desserts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDessertDto> createDessert(@Valid @RequestBody RequestDessertDto requestDessert) {
        ResponseDessertDto newDessert = DESSERT_SERVICES.createDessert(requestDessert);
        return new ResponseEntity<>(newDessert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDessertDto> updateDessert(@PathVariable Long id, @Valid @RequestBody RequestDessertDto request) {
        ResponseDessertDto updateDessert = DESSERT_SERVICES.updateDessert(id, request);
        return new ResponseEntity<>(updateDessert, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDessertById(@PathVariable Long id) {
        DESSERT_SERVICES.deleteDessertById(id);
        return new ResponseEntity<>("Dessert has been deleted.", HttpStatus.OK);
    }
}*/