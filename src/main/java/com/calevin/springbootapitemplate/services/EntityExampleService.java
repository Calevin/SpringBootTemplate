package com.calevin.springbootapitemplate.services;

import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.repositories.EntityExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class EntityExampleService extends BaseService<EntityExample, Long, EntityExampleRepository> {
}
