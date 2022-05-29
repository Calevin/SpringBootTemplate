package com.calevin.springbootapitemplate.repositories;

import com.calevin.springbootapitemplate.entities.CategoryExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryExampleRepository extends JpaRepository<CategoryExample, Long> {
}
