package com.calevin.springbootapitemplate.dtos.entityexample;

import com.calevin.springbootapitemplate.entities.EntityExample;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class EntityExampleConverterDTO {

    @Mapping(target = "categoryName", source = "entityExample.category.name")
    public abstract GetEntityExampleDTO converterToGetEntityExampleDTO(EntityExample entityExample);

    @Mapping(target = "category", expression = "java( CategoryExample.builder().id( createEntityExampleDTO.getCategoryId() ).build() )")
    public abstract EntityExample converterToEntityExample(CreateEntityExampleDTO createEntityExampleDTO);
}
