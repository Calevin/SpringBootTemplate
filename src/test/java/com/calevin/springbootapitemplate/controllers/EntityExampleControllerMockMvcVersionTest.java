package com.calevin.springbootapitemplate.controllers;

import com.calevin.springbootapitemplate.dtos.entityexample.CreateEntityExampleDTO;
import com.calevin.springbootapitemplate.entities.EntityExample;
import com.calevin.springbootapitemplate.repositories.EntityExampleRepository;
import com.calevin.springbootapitemplate.services.EntityExampleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntityExampleControllerMockMvcVersionTest {
    private final String SLASH = "/";
    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String ROOT_ENDPOINT = "/entityExample";
    private final String TEST_NAME = "Test";

    private final String TEST_NAME_EDITED = "Test Edited";
    private final String TEST_DESCRIPTION = "Description Test";
    private final long TEST_CATEGORY_ID = 1;

    private long ID_NEW_ENTITY = -1;
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    @Autowired
    private EntityExampleService entityExampleService;

    @Autowired
    private EntityExampleRepository entityExampleRepository;

    @Test
    @Order(1)
    void shouldReturnAllEntities() throws Exception {
        this.mockMvc.perform(get(ROOT_ENDPOINT))
                .andDo(print())
                .andExpectAll(
                  status().isOk(),
                  jsonPath("$[0].id").value("1"),
                  jsonPath("$[6].id").value("7"),
                  jsonPath("$.*", hasSize(7))
                );
    }

    @Test
    @Order(2)
    void shouldCreateNewEntity() throws Exception {

        CreateEntityExampleDTO createEntityExampleDTO = CreateEntityExampleDTO
                .builder()
                .name(TEST_NAME)
                .description(TEST_DESCRIPTION)
                .categoryId(TEST_CATEGORY_ID)
                .build();

        ResultActions resultActions = this.mockMvc.perform(
                post(ROOT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createEntityExampleDTO))
            ).andDo((result) ->{
                    String resultJson = result.getResponse().getContentAsString();
                    log.info("resultJson {}", resultJson);

                    ObjectMapper mapper = new ObjectMapper();

                    EntityExample entityExample = mapper.readValue(resultJson, EntityExample.class);

                    ID_NEW_ENTITY = entityExample.getId();

                })
                .andExpectAll(
                    status().isCreated(),
                jsonPath(ID).value(ID_NEW_ENTITY),
                jsonPath(NAME).value(TEST_NAME),
                jsonPath(DESCRIPTION).value(TEST_DESCRIPTION)
        );
    }

    @Test
    @Order(3)
    void shouldReturnOneEntity() throws Exception {
        this.mockMvc.perform(get(ROOT_ENDPOINT + SLASH + ID_NEW_ENTITY))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath(ID).value(ID_NEW_ENTITY),
                        jsonPath(NAME).value(TEST_NAME),
                        jsonPath("categoryName").value("Primera División de Argentina")
                );
    }

    @Test
    @Order(4)
    void shouldEditOneEntity() throws Exception {
        CreateEntityExampleDTO createEntityExampleDTO = CreateEntityExampleDTO
                .builder()
                .name(TEST_NAME_EDITED)
                .description(TEST_DESCRIPTION)
                .categoryId(TEST_CATEGORY_ID)
                .build();

        this.mockMvc.perform(
                put(ROOT_ENDPOINT + SLASH + ID_NEW_ENTITY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createEntityExampleDTO))
        ).andExpectAll(
                status().isOk(),
                jsonPath(ID).value(ID_NEW_ENTITY),
                jsonPath(NAME).value(TEST_NAME_EDITED),
                jsonPath(DESCRIPTION).value(TEST_DESCRIPTION)
        );
    }

    @Test
    @Order(5)
    void shouldDeleteOneEntity() throws Exception {
        this.mockMvc
                .perform(delete(ROOT_ENDPOINT + SLASH + ID_NEW_ENTITY))
                    .andExpect(status().isOk());

        verify(entityExampleService, times(1)).findById(ID_NEW_ENTITY);
        verify(entityExampleService, times(1)).deleteById(ID_NEW_ENTITY);
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
