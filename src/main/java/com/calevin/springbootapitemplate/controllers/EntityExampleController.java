package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EntityExample> getAll() {
        log.info("getAll");
        return entityExampleService.findAll();
    }

    @GetMapping("/entityExample/{id}")
    public EntityExample getOne(@PathVariable Long id) {
        log.info("getOne, id: {}", id);
        return entityExampleService.findById(id).orElse(null);
    }

    @PostMapping("/entityExample")
    public EntityExample newRecord(@RequestBody EntityExample newEntityExample) {
        log.info("newRecord");
        return entityExampleService.save(newEntityExample);
    }

    @PutMapping("/entityExample/{id}")
    public EntityExample editRecord(@RequestBody EntityExample entityExample, @PathVariable Long id) {
        log.info("editRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map( p -> {
                    entityExample.setId(id);
                    log.info(String.valueOf(entityExample));
                    return entityExampleService.save(entityExample);
                })
                .orElse(null);
    }

    @DeleteMapping("/entityExample/{id}")
    public EntityExample deleteRecord(@PathVariable Long id) {
        log.info("deleteRecord, id: {}", id);
        return entityExampleService
                .findById(id)
                .map( p -> {
                    entityExampleService.deleteById(id);
                    return p;
                })
                .orElse(null);
    }
}
