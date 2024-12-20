package com.f5.delicious;

import com.f5.delicious.dto.RequestCreatorDto;
import com.f5.delicious.dto.RequestDessertDto;
import com.f5.delicious.entity.Creator;
import com.f5.delicious.entity.Dessert;
import com.f5.delicious.repository.CreatorRepository;
import com.f5.delicious.repository.DessertRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class DessertAcceptaceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CreatorRepository creatorRepository;

    private Creator testCreator;

    @BeforeEach
    void setUp() {
        // Limpieza de base de datos
        creatorRepository.deleteAll();

        // Crear un creador de ejemplo
        testCreator = new Creator("John", "john.doe@gmail.com", "123456789", "123 Some St, City");
        creatorRepository.save(testCreator);
    }

    @Test
    void shouldCreateCreator() throws Exception {
        // Crear un DTO para el creador
        RequestCreatorDto creatorDto = new RequestCreatorDto("Alice", "alice.doe@gmail.com", "987654321", "456 Another St, City");

        // Llamada al endpoint para crear un creador
        mockMvc.perform(post("/creators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creatorDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Alice")))
                .andExpect(jsonPath("$.email", is("alice.doe@gmail.com")))
                .andExpect(jsonPath("$.phone", is("987654321")))
                .andExpect(jsonPath("$.address", is("456 Another St, City")));
    }

    @Test
    void shouldNotCreateCreatorWithDuplicatePhone() throws Exception {
        // Intentar crear un creador con un teléfono duplicado
        RequestCreatorDto creatorDto = new RequestCreatorDto("Alice", "alice.doe@gmail.com", "123456789", "456 Another St, City");

        mockMvc.perform(post("/creators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creatorDto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", is("Phone number already exists")));
    }

    @Test
    void shouldGetAllCreators() throws Exception {
        mockMvc.perform(get("/creators"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].email", is("john.doe@gmail.com")));
    }

    @Test
    void shouldGetCreatorById() throws Exception {
        mockMvc.perform(get("/creators/" + testCreator.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.email", is("john.doe@gmail.com")));
    }

    @Test
    void shouldUpdateCreator() throws Exception {
        RequestCreatorDto updatedCreatorDto = new RequestCreatorDto("John Updated", "john.updated@gmail.com", "123456789", "789 Updated St, City");

        mockMvc.perform(put("/creators/" + testCreator.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCreatorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Updated")))
                .andExpect(jsonPath("$.email", is("john.updated@gmail.com")))
                .andExpect(jsonPath("$.address", is("789 Updated St, City")));
    }

    @Test
    void shouldDeleteCreator() throws Exception {
        mockMvc.perform(delete("/creators/" + testCreator.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Creator has been deleted.")));

        mockMvc.perform(get("/creators/" + testCreator.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldNotDeleteCreatorWithDesserts() throws Exception {
        // Simular que el creador tiene postres (añadir lógica para asociar postres al creador)
        // Si es necesario, se debe simular la creación de postres en los tests de la entidad Dessert.

        // Intentar eliminar el creador con postres asociados
        mockMvc.perform(delete("/creators/" + testCreator.getId()))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", is("Creator cannot be deleted because they have desserts.")));
    }
}