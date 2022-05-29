package com.calevin.springbootapitemplate.dtos.entityexample;

import com.calevin.springbootapitemplate.entities.CategoryExample;
import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.repositories.CategoryExampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class EntityExampleConverterDTO {

    @Autowired
    CategoryExampleRepository categoryExampleRepository;

    @Mapping(target = "categoryName", source = "entityExample.category.name")
    public abstract GetEntityExampleDTO converterToGetEntityExampleDTO(EntityExample entityExample);

    @Mapping(target = "category", expression = "java( getCategoryExample( createEntityExampleDTO.getCategoryId() ) )")
    public abstract EntityExample converterToEntityExample(CreateEntityExampleDTO createEntityExampleDTO);

    protected CategoryExample getCategoryExample(Long categoryId) {
        log.info("getCategoryExample, parameter categoryId: {}", categoryId);
        return categoryExampleRepository.findById(categoryId).get();
    }
}
