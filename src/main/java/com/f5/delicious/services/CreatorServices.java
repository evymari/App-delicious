package com.f5.delicious.services;

import com.f5.delicious.dto.RequestCreatorDto;
import com.f5.delicious.dto.ResponseCreatorDto;
import com.f5.delicious.entity.Creator;

import com.f5.delicious.exceptions.*;
import com.f5.delicious.repository.DessertRepository;
import org.springframework.stereotype.Service;
import com.f5.delicious.repository.CreatorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreatorServices {
    private final CreatorRepository CREATOR_REPOSITORY;
    private final DessertRepository DESSERT_REPOSITORY;

    public CreatorServices(CreatorRepository creatorRepository, DessertRepository dessertRepository) {
        this.CREATOR_REPOSITORY = creatorRepository;
        this.DESSERT_REPOSITORY = dessertRepository;
    }

    // POST
    public ResponseCreatorDto createCreator(RequestCreatorDto creatorDto) {
        Optional<Creator> existingCreator = CREATOR_REPOSITORY.findByPhone(creatorDto.phone());
        if (existingCreator.isPresent()) {
            throw new DuplicatePhoneException(creatorDto.phone());
        }

        Creator newCreator = creatorDto.toEntity();
        Creator savedCreator = CREATOR_REPOSITORY.save(newCreator);
        return ResponseCreatorDto.fromEntity(savedCreator);
    }

    // GET: Get all creators
    public List<ResponseCreatorDto> findAllCreators() {
        List<Creator> creators = CREATOR_REPOSITORY.findAll();
        if (creators.isEmpty()) {
            throw new NoRegistersFoundException();
        }

        return creators.stream()
                .map(ResponseCreatorDto::fromEntity)
                .collect(Collectors.toList());
    }

    // GET: Find creator by ID
    public ResponseCreatorDto findCreatorById(Long id) {
        Creator creator = CREATOR_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        return ResponseCreatorDto.fromEntity(creator);
    }

    // GET: Search creator by name
    public List<ResponseCreatorDto> searchByName(String name) {
        List<Creator> creatorList = CREATOR_REPOSITORY.findLikeName(name);
        if (creatorList.isEmpty()) {
            throw new NoNameFoundException(name);
        }
        return creatorList.stream()
                .map(ResponseCreatorDto::fromEntity).toList();
    }

    // PUT: Update creator
    public ResponseCreatorDto updateCreator(Long id, RequestCreatorDto request) {
        Creator existingCreator = CREATOR_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        // Update fields
        existingCreator.setName(request.name());
        existingCreator.setEmail(request.email());
        existingCreator.setPhone(request.phone());
        existingCreator.setAddress(request.address());

        Creator updatedCreator = CREATOR_REPOSITORY.save(existingCreator);
        return ResponseCreatorDto.fromEntity(updatedCreator);
    }

    // DELETE: Delete creator by ID if no dependencies with Dessert exist
    public void deleteCreatorById(Long id) {
        Creator creator = CREATOR_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        boolean hasDesserts = DESSERT_REPOSITORY.existsByCreatorId(id);
        if (hasDesserts) {
            throw new DependencyException(id);
        }

        CREATOR_REPOSITORY.deleteById(id);
    }
}