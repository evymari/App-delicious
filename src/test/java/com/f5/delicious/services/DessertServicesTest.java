package com.f5.delicious.services;

import com.f5.delicious.dto.RequestDessertDto;
import com.f5.delicious.dto.ResponseDessertDto;
import com.f5.delicious.entity.Creator;
import com.f5.delicious.entity.Dessert;
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
class DessertServicesTest {
    @Mock
    DessertRepository dessertRepository;

    @Mock
    CreatorRepository creatorRepository;

    @InjectMocks
    private DessertServices dessertService;

    // Test unitario para crear un Dessert
    @Test

    void should_createNewDessert() {
        // GIVEN
        Long creatorId = 1L;
        Creator creator = new Creator(creatorId, "John Doe", "john@example.com");
        RequestDessertDto requestDessert = new RequestDessertDto(
                "Chocolate Cake",
                "Delicious chocolate cake",
                "France",
                "Flour, Sugar, Chocolate",
                "https://example.com/cake.jpg",
                creatorId
        );
        Dessert dessertToSave = requestDessert.toEntity(creator);

        Dessert expectedDessert = new Dessert(
                "Chocolate Cake",
                "Delicious chocolate cake",
                "France",
                "Flour, Sugar, Chocolate",
                "https://example.com/cake.jpg",
                creator
        );
        ResponseDessertDto expectedResponse = ResponseDessertDto.fromEntity(expectedDessert);

        Mockito.when(creatorRepository.findById(creatorId)).thenReturn(Optional.of(creator));
        Mockito.when(dessertRepository.save(Mockito.any(Dessert.class))).thenReturn(expectedDessert);

        // WHEN
        ResponseDessertDto dessertResponse = dessertService.createDessert(requestDessert);

        // THEN
        verify(dessertRepository).save(Mockito.any(Dessert.class));
        assertEquals(expectedResponse, dessertResponse);
    }


    // Test unitario para encontrar todos los Desserts
    @Test
    void should_findAllDesserts() {
        // GIVEN
        Dessert dessert1 = new Dessert("Cake", "Tasty cake", "USA", "Flour, Sugar", "https://example.com/cake.jpg", new Creator(1L, "Alice", "alice@example.com"));
        Dessert dessert2 = new Dessert("Pie", "Apple pie", "UK", "Apples, Flour", "https://example.com/pie.jpg", new Creator(2L, "Bob", "bob@example.com"));
        List<Dessert> dessertList = List.of(dessert1, dessert2);
        List<ResponseDessertDto> expectedResponse = dessertList.stream()
                .map(ResponseDessertDto::fromEntity)
                .toList();

        Mockito.when(dessertRepository.findAll()).thenReturn(dessertList);

        // WHEN
        List<ResponseDessertDto> dessertResponse = dessertService.findAllDesserts();

        // THEN
        assertEquals(expectedResponse, dessertResponse);
    }

    // Test unitario para obtener un Dessert por ID
    @Test
    void should_getDessertById() {
        // GIVEN
        Long dessertId = 1L;
        Dessert dessert = new Dessert("Cake", "Tasty cake", "USA", "Flour, Sugar", "https://example.com/cake.jpg", new Creator(1L, "Alice", "alice@example.com"));
        ResponseDessertDto expectedResponse = ResponseDessertDto.fromEntity(dessert);

        Mockito.when(dessertRepository.findById(dessertId)).thenReturn(Optional.of(dessert));

        // WHEN
        ResponseDessertDto dessertResponse = dessertService.findDessertById(dessertId);

        // THEN
        assertEquals(expectedResponse, dessertResponse);
    }

    // Test unitario para actualizar un Dessert
    @Test
    void should_updateDessert() {
        // GIVEN
        Long dessertId = 1L;
        RequestDessertDto requestDessert = new RequestDessertDto(
                "Updated Cake",
                "Updated description",
                "France",
                "Flour, Sugar, Chocolate",
                "https://example.com/updated.jpg",
                1L
        );
        Dessert existingDessert = new Dessert("Cake", "Tasty cake", "USA", "Flour, Sugar", "https://example.com/cake.jpg", new Creator(1L, "Alice", "alice@example.com"));

        Dessert updatedDessert = new Dessert("Updated Cake", "Updated description", "France", "Flour, Sugar, Chocolate", "https://example.com/updated.jpg", existingDessert.getCreator());
        ResponseDessertDto expectedResponse = ResponseDessertDto.fromEntity(updatedDessert);

        Mockito.when(dessertRepository.findById(dessertId)).thenReturn(Optional.of(existingDessert));
        Mockito.when(creatorRepository.findById(requestDessert.creatorId())).thenReturn(Optional.of(existingDessert.getCreator()));
        Mockito.when(dessertRepository.save(existingDessert)).thenReturn(updatedDessert);

        // WHEN
        ResponseDessertDto dessertResponse = dessertService.updateDessert(dessertId, requestDessert);

        // THEN
        assertEquals(expectedResponse, dessertResponse);
    }

    // Test unitario para eliminar un Dessert por ID
    @Test
    void should_deleteDessertById() {
        // GIVEN
        Long dessertId = 1L;
        Dessert dessertToDelete = new Dessert("Cake", "Tasty cake", "USA", "Flour, Sugar", "https://example.com/cake.jpg", new Creator(1L, "Alice", "alice@example.com"));

        Mockito.when(dessertRepository.findById(dessertId)).thenReturn(Optional.of(dessertToDelete));

        // WHEN
        dessertService.deleteDessertById(dessertId);

        // THEN
        verify(dessertRepository).deleteById(dessertId);
    }
}
