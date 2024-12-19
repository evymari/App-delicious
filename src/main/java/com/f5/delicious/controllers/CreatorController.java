package com.f5.delicious.controllers;


import com.f5.delicious.dto.RequestCreatorDto;
import com.f5.delicious.dto.ResponseCreatorDto;
import com.f5.delicious.services.CreatorServices;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creators")
public class CreatorController {

    private final CreatorServices CREATOR_SERVICES;

    public CreatorController(CreatorServices creatorServices) {
        CREATOR_SERVICES = creatorServices;
    }

    @PostMapping
    public ResponseEntity<ResponseCreatorDto> saveNewCreator(@Valid @RequestBody RequestCreatorDto requestCreator) {
        ResponseCreatorDto newCreator = CREATOR_SERVICES.createCreator(requestCreator);
        return new ResponseEntity<>(newCreator, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCreatorDto>> getCreatorList(@PathParam("name") String name) {
        if(name == null){
            List<ResponseCreatorDto> creators = CREATOR_SERVICES.findAllCreators();
            return new ResponseEntity<>(creators, HttpStatus.OK);
        }
        List<ResponseCreatorDto> searchCreators = CREATOR_SERVICES.searchByName(name);
        return new ResponseEntity<>(searchCreators, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCreatorDto> getCreatorById(@PathVariable Long id) {
        ResponseCreatorDto creator = CREATOR_SERVICES.findCreatorById(id);
        return new ResponseEntity<>(creator, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCreatorDto> updateCreatorById(@PathVariable Long id, @Valid @RequestBody RequestCreatorDto request) {
        ResponseCreatorDto updatedCreator = CREATOR_SERVICES.updateCreator(id, request);
        return new ResponseEntity<>(updatedCreator, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCreatorById(@PathVariable Long id) {
        CREATOR_SERVICES.deleteCreatorById(id);
        return new ResponseEntity<>("Creator has been deleted.", HttpStatus.OK);
    }
}
