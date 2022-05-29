package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.dtos.entityexample.CreateEntityExampleDTO;
import com.calevin.springbootapitemplate.dtos.entityexample.EntityExampleConverterDTO;
import com.calevin.springbootapitemplate.dtos.entityexample.GetEntityExampleDTO;
import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.errors.NotFoundException;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class EntityExampleController {

    private final EntityExampleService entityExampleService;
    @Autowired
    protected EntityExampleConverterDTO converterDTO;

    @Autowired
    public EntityExampleController(EntityExampleService entityExampleService) {
        this.entityExampleService = entityExampleService;
    }

    @GetMapping("/entityExample")
    public ResponseEntity<List<GetEntityExampleDTO>>  getAll() {
        log.info("getAll");
        List<EntityExample> entities = entityExampleService.findAll();

        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(
                    entities.stream()
                            .map(converterDTO::converterToGetEntityExampleDTO)
                            .collect(Collectors.toList()));
        }
    }

    @GetMapping("/entityExample/{id}")
    public ResponseEntity<GetEntityExampleDTO> getOne(@PathVariable Long id) {
        log.info("getOne, id: {}", id);

        return entityExampleService.findById(id)
                .map(converterDTO::converterToGetEntityExampleDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/entityExample")
    public ResponseEntity<EntityExample> newRecord(@RequestBody CreateEntityExampleDTO createEntityExampleDTO) {
        log.info("newRecord");
        EntityExample newEntityExample = converterDTO.converterToEntityExample(createEntityExampleDTO);
        log.info("newRecord, newEntityExample: {}", newEntityExample);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityExampleService.save(newEntityExample));
    }

    @PutMapping("/entityExample/{id}")
    public ResponseEntity<EntityExample> editRecord(CreateEntityExampleDTO editEntityExampleDTO, @PathVariable Long id) {
        log.info("editRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map( p -> {
                    EntityExample entityExample = converterDTO.converterToEntityExample(editEntityExampleDTO);
                    entityExample.setId(id);

                    return ResponseEntity.ok(entityExampleService.save(entityExample));
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/entityExample/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
        log.info("deleteRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map( p -> {
                    entityExampleService.deleteById(id);
                    return ResponseEntity.status(HttpStatus.OK).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }
}
