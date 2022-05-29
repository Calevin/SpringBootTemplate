package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.errors.NotFoundException;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class EntityExampleController {

    private final EntityExampleService entityExampleService;

    @Autowired
    public EntityExampleController(EntityExampleService entityExampleService) {
        this.entityExampleService = entityExampleService;
    }

    @GetMapping("/entityExample")
    public ResponseEntity<List<EntityExample>>  getAll() {
        log.info("getAll");
        List<EntityExample> entities = entityExampleService.findAll();

        if(entities.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(entities);
        }
    }

    @GetMapping("/entityExample/{id}")
    public ResponseEntity<EntityExample> getOne(@PathVariable Long id) {
        log.info("getOne, id: {}", id);

        return entityExampleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/entityExample")
    public ResponseEntity<EntityExample> newRecord(@RequestBody EntityExample newEntityExample) {
        log.info("newRecord");
        return ResponseEntity.status(HttpStatus.CREATED).body(entityExampleService.save(newEntityExample));
    }

    @PutMapping("/entityExample/{id}")
    public ResponseEntity<EntityExample> editRecord(@RequestBody EntityExample entityExample, @PathVariable Long id) {
        log.info("editRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map( p -> {
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
