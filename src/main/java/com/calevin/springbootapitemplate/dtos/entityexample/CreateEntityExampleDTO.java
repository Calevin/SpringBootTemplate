package com.calevin.springbootapitemplate.dtos.entityexample;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CreateEntityExampleDTO {
    private String name;
    private String description;
    private Long categoryId;
}
