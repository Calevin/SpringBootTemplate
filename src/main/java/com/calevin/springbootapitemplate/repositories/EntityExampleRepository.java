package com.calevin.springbootapitemplate.repositories;

import com.calevin.springbootapitemplate.entities.EntityExample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityExampleRepository extends JpaRepository<EntityExample, Long> {
}
