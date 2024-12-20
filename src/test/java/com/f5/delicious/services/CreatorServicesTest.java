package com.f5.delicious.services;

import com.f5.delicious.dto.RequestCreatorDto;
import com.f5.delicious.dto.ResponseCreatorDto;
import com.f5.delicious.entity.Creator;
import com.f5.delicious.repository.CreatorRepository;
import com.f5.delicious.repository.DessertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CreatorServicesTest {

    @Mock
    CreatorRepository creatorRepository;

    @Mock
    DessertRepository dessertRepository;

    @InjectMocks
    private CreatorServices creatorServices;

    // Test unitario para crear un Creator
    @Test

    void should_createNewCreator() {
        // GIVEN
        RequestCreatorDto request = new RequestCreatorDto("John", "john@example.com", "123456789", "Torremolinos");
        Creator creatorToSave = request.toEntity();

        Creator expectedCreator = new Creator(1L, "John", "john@example.com", "123456789", "Torremolinos");
        ResponseCreatorDto expectedResponse = ResponseCreatorDto.fromEntity(expectedCreator); // Convertir a ResponseCreatorDto

        // Cambiar el stubbing para aceptar cualquier objeto Creator
        Mockito.when(creatorRepository.save(Mockito.any(Creator.class))).thenReturn(expectedCreator);

        // WHEN
        ResponseCreatorDto creatorResponse = creatorServices.createCreator(request);

        // THEN
        verify(creatorRepository).save(Mockito.any(Creator.class));  // Verificar que se llame al save con cualquier objeto Creator
        assertEquals(expectedResponse, creatorResponse);
    }


    // Test unitario para encontrar todos los Creators
    @Test
    void should_findAllCreators() {
        // GIVEN
        Creator creator1 = new Creator(1L, "John", "john@example.com", "123456789", "Torremolinos");
        Creator creator2 = new Creator(2L, "Jane", "jane@example.com", "987654321", "Málaga");
        List<Creator> creatorList = List.of(creator1, creator2);
        List<ResponseCreatorDto> expectedResponse = creatorList.stream()
                .map(ResponseCreatorDto::fromEntity)
                .toList();

        Mockito.when(creatorRepository.findAll()).thenReturn(creatorList);

        // WHEN
        List<ResponseCreatorDto> creatorResponse = creatorServices.findAllCreators();

        // THEN
        assertEquals(expectedResponse, creatorResponse);
    }

    // Test unitario para obtener un Creator por ID
    @Test
    void should_getCreatorById() {
        // GIVEN
        Creator creator = new Creator(1L, "John", "john@example.com", "123456789", "Torremolinos");
        ResponseCreatorDto expectedResponse = ResponseCreatorDto.fromEntity(creator);

        Mockito.when(creatorRepository.findById(1L)).thenReturn(Optional.of(creator));

        // WHEN
        ResponseCreatorDto creatorResponse = creatorServices.findCreatorById(1L);

        // THEN
        assertEquals(expectedResponse, creatorResponse);
    }

    // Test unitario para obtener los Creators por nombre
    @Test
    void should_getCreatorsByName() {
        // GIVEN
        String name = "John";
        Creator creator1 = new Creator(1L, "John", "john@example.com", "123456789", "Torremolinos");
        Creator creator2 = new Creator(2L, "John", "john2@example.com", "987654321", "Málaga");
        List<Creator> creatorList = List.of(creator1, creator2);
        List<ResponseCreatorDto> expectedResponse = creatorList.stream()
                .map(ResponseCreatorDto::fromEntity)
                .toList();

        Mockito.when(creatorRepository.findLikeName(name)).thenReturn(creatorList);

        // WHEN
        List<ResponseCreatorDto> creatorResponse = creatorServices.searchByName(name);

        // THEN
        assertEquals(expectedResponse, creatorResponse);
    }

    // Test unitario para actualizar un Creator
    @Test
    void should_updateCreator() {
        // GIVEN
        RequestCreatorDto request = new RequestCreatorDto("John Updated", "john.updated@example.com", "123456789", "Torremolinos");
        Creator existingCreator = new Creator(1L, "John", "john@example.com", "123456789", "Torremolinos");
        Creator updatedCreator = new Creator(1L, "John Updated", "john.updated@example.com", "123456789", "Torremolinos");
        ResponseCreatorDto expectedResponse = ResponseCreatorDto.fromEntity(updatedCreator);

        Mockito.when(creatorRepository.findById(1L)).thenReturn(Optional.of(existingCreator));
        Mockito.when(creatorRepository.save(existingCreator)).thenReturn(updatedCreator);

        // WHEN
        ResponseCreatorDto creatorResponse = creatorServices.updateCreator(1L, request);

        // THEN
        assertEquals(expectedResponse, creatorResponse);
    }

    // Test unitario para eliminar un Creator por ID
    @Test
    void should_deleteCreatorById() {
        // GIVEN
        Creator creatorToDelete = new Creator(1L, "John", "john@example.com", "123456789", "Torremolinos");

        Mockito.when(creatorRepository.findById(1L)).thenReturn(Optional.of(creatorToDelete));
        Mockito.when(dessertRepository.existsByCreatorId(1L)).thenReturn(false); // No hay desserts

        // WHEN
        creatorServices.deleteCreatorById(1L);

        // THEN
        verify(creatorRepository).deleteById(1L);
    }
}
