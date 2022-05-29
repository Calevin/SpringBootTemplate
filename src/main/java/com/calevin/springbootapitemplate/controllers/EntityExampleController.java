package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntityExampleController {

    private final EntityExampleService entityExampleService;

    @Autowired
    public EntityExampleController(EntityExampleService entityExampleService) {
        this.entityExampleService = entityExampleService;
    }

    @GetMapping("/entityExample")
    public List<EntityExample> getAll() {
        return entityExampleService.findAll();
    }
}
