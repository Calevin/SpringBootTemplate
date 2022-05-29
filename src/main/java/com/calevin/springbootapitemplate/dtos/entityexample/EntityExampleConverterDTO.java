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

    public EntityExample converterToEntityExample(CreateEntityExampleDTO createEntityExampleDTO) {
        log.info("converterToEntityExample");
        EntityExample entityExample = new EntityExample();
        entityExample.setName(createEntityExampleDTO.getName());
        entityExample.setDescription(createEntityExampleDTO.getDescription());

        CategoryExample categoryExample = categoryExampleRepository.findById(createEntityExampleDTO.getCategoryId()).get();

        entityExample.setCategory(categoryExample);

        return entityExample;
    }
}
