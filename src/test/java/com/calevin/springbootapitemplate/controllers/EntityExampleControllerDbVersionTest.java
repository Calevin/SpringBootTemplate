package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.dtos.entityexample.CreateEntityExampleDTO;
import com.calevin.springbootapitemplate.dtos.entityexample.EntityExampleConverterDTO;
import com.calevin.springbootapitemplate.entities.CategoryExample;
import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntityExampleControllerDbVersionTest {

    @SpyBean
    @Autowired
    private EntityExampleService entityExampleService;

    @Autowired
    private EntityExampleConverterDTO converterDTO;

    EntityExampleController entityExampleController;

    @BeforeEach
    public void before(){
        entityExampleController = new EntityExampleController(entityExampleService, converterDTO);
    }

    @Test
    void getAll() {
        // GIVEN
        // WHEN
        var getAllResponse = entityExampleController.getAll();

        // THEN
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());
        assertEquals(7, getAllResponse.getBody().size());
    }

    @Test
    void getOne() {
        // GIVEN
        var exampleId = 1L;

        // WHEN
        var getOneResponse = entityExampleController.getOne(exampleId);

        // THEN
        assertEquals(HttpStatus.OK, getOneResponse.getStatusCode());
        assertEquals(exampleId, getOneResponse.getBody().getId());
        assertEquals("Club Atlético Boca Juniors", getOneResponse.getBody().getName());
        assertEquals("Primera División de Argentina", getOneResponse.getBody().getCategoryName());
    }

    @Test
    @Order(1)
    void newRecord() {
        // GIVEN
        var exampleId = 8L;
        var name = "create";
        var description = "create description";
        var createEntityExampleDTO = CreateEntityExampleDTO
                .builder()
                .name(name)
                .description(description)
                .categoryId(1L)
                .build();

        // WHEN
        var newRecordResponse = entityExampleController.newRecord(createEntityExampleDTO);

        // THEN
        assertEquals(HttpStatus.CREATED, newRecordResponse.getStatusCode());
        assertEquals(exampleId, newRecordResponse.getBody().getId());
        assertEquals(createEntityExampleDTO.getName(), newRecordResponse.getBody().getName());
        assertEquals(createEntityExampleDTO.getDescription(), newRecordResponse.getBody().getDescription());
    }

    @Test
    @Order(2)
    void editRecord() {
        // GIVEN
        var exampleId = 8L;
        var newName = "new name";
        var description = "create description";
        var categoryId = 1L;
        var editEntityRecord = CreateEntityExampleDTO
                .builder()
                .name(newName)
                .description(description)
                .categoryId(categoryId)
                .build();

        // WHEN
        var editRecordResponse = entityExampleController.editRecord(editEntityRecord, exampleId);

        // THEN
        assertEquals(HttpStatus.OK, editRecordResponse.getStatusCode());
        assertEquals(exampleId, editRecordResponse.getBody().getId());
        assertEquals(newName, editRecordResponse.getBody().getName());
        assertEquals(description, editRecordResponse.getBody().getDescription());
    }

    @Test
    @Order(3)
    void deleteRecord() {
        // GIVEN
        var exampleId = 8L;

        // WHEN
        var responseDeleteRecord = entityExampleController.deleteRecord(exampleId);

        verify(entityExampleService, times(1)).findById(exampleId);
        verify(entityExampleService, times(1)).deleteById(exampleId);
        assertEquals(HttpStatus.OK, responseDeleteRecord.getStatusCode());
    }
}
