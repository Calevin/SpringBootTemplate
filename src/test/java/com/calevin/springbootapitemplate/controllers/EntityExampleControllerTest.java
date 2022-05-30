package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.dtos.entityexample.CreateEntityExampleDTO;
import com.calevin.springbootapitemplate.dtos.entityexample.EntityExampleConverterDTO;
import com.calevin.springbootapitemplate.entities.CategoryExample;
import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class EntityExampleControllerTest {

    @Mock
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
        // MOCK
        var exampleId = 1L;
        var name = "one";
        var description = "description one";
        var entityExample = createEntityExample(exampleId, name, description, 1L);

        var exampleId2 = 2L;
        var name2 = "two";
        var description2 = "description two";
        var entityExample2 = createEntityExample(exampleId2, name2, description2, 2L);

        when(entityExampleService.findAll()).thenReturn(List.of(entityExample, entityExample2));

        // WHEN
        var getAllResponse = entityExampleController.getAll();

        // THEN
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());
        assertEquals(2, getAllResponse.getBody().size());
        assertTrue(getAllResponse.getBody().contains(converterDTO.converterToGetEntityExampleDTO(entityExample)));
        assertTrue(getAllResponse.getBody().contains(converterDTO.converterToGetEntityExampleDTO(entityExample2)));
    }

    @Test
    void getOne() {
        // GIVEN
        var exampleId = 1L;
        var categoryId = 1L;
        var name = "one";
        var nameCategory = "categoryOfOne";
        var description = "one description";
        var entityExample = createEntityExample(exampleId, name, description, categoryId);
        entityExample.setCategory(createCategoryExample(categoryId, nameCategory, null));

        // MOCK
        when(entityExampleService.findById(exampleId)).thenReturn(Optional.of(entityExample));

        // WHEN
        var getOneResponse = entityExampleController.getOne(exampleId);

        // THEN
        assertEquals(HttpStatus.OK, getOneResponse.getStatusCode());
        assertEquals(exampleId, getOneResponse.getBody().getId());
        assertEquals(name, getOneResponse.getBody().getName());
        assertEquals(nameCategory, getOneResponse.getBody().getCategoryName());
    }

    @Test
    void newRecord() {
        // GIVEN
        var exampleId = 1L;
        var name = "create";
        var description = "create description";
        var createEntityExampleDTO = CreateEntityExampleDTO
                .builder()
                .name(name)
                .description(description)
                .categoryId(11L)
                .build();

        // MOCK
        var entityExampleMock = createEntityExample(null, name, description, 11L);
        var newEntityExampleMock =  entityExampleMock.toBuilder().id(exampleId).build();
        when(entityExampleService.save(argThat((EntityExample a) -> {
            return a.getName().equals(entityExampleMock.getName()) && a.getDescription().equals(entityExampleMock.getDescription());
        }))).thenReturn(newEntityExampleMock);

        // WHEN
        var newRecordResponse = entityExampleController.newRecord(createEntityExampleDTO);

        // THEN
        assertEquals(HttpStatus.CREATED, newRecordResponse.getStatusCode());
        assertEquals(exampleId, newRecordResponse.getBody().getId());
        assertEquals(entityExampleMock.getName(), newRecordResponse.getBody().getName());
        assertEquals(entityExampleMock.getDescription(), newRecordResponse.getBody().getDescription());
    }

    @Test
    void editRecord() {
        // GIVEN
        var exampleId = 1L;
        var newName = "new name";
        var description = "create description";
        var categoryId = 10L;
        var editEntityRecord = CreateEntityExampleDTO
                .builder()
                .name(newName)
                .description(description)
                .categoryId(categoryId)
                .build();

        // MOCK
        var entityFound = createEntityExample(exampleId, newName, description, categoryId);
        when(entityExampleService.findById(exampleId)).thenReturn(Optional.of(entityFound));
        when(entityExampleService.save(entityFound)).thenReturn(entityFound);

        // WHEN
        var editRecordResponse = entityExampleController.editRecord(editEntityRecord, exampleId);

        // THEN
        assertEquals(HttpStatus.OK, editRecordResponse.getStatusCode());
        assertEquals(exampleId, editRecordResponse.getBody().getId());
        assertEquals(newName, editRecordResponse.getBody().getName());
        assertEquals(description, editRecordResponse.getBody().getDescription());
    }

    @Test
    void deleteRecord() {
        // GIVEN
        var exampleId = 1L;

        // MOCK
        var name = "name";
        var description = "delete description";
        var categoryId = 10L;
        var entityFound = createEntityExample(exampleId, name, description, categoryId);
        when(entityExampleService.findById(exampleId)).thenReturn(Optional.of(entityFound));
        doNothing().when(entityExampleService).deleteById(exampleId);

        // WHEN
        var responseDeleteRecord = entityExampleController.deleteRecord(exampleId);

        verify(entityExampleService, times(1)).findById(exampleId);
        verify(entityExampleService, times(1)).deleteById(exampleId);
        assertEquals(HttpStatus.OK, responseDeleteRecord.getStatusCode());
    }

    private EntityExample createEntityExample(Long id, String name, String description, Long categoryId) {
        return EntityExample
                .builder()
                .id(id)
                .description(description)
                .name(name)
                .category(categoryId == null ? null : CategoryExample.builder().id(categoryId).build())
                .build();
    }

    private CategoryExample createCategoryExample(Long id, String name, String description){
        return CategoryExample
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }
}
