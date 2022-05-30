package com.calevin.springbootapitemplate.dtos.entityexample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetEntityExampleDTO {
    private long id;
    private String name;
    private String categoryName;
}
