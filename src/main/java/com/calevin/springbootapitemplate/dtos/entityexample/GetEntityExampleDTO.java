package com.calevin.springbootapitemplate.dtos.entityexample;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetEntityExampleDTO {
    private long id;
    private String name;
    private String categoryName;
}
