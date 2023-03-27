package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.services.EducationHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Only way tests will work is if we obtain the OAuth2 authentication data.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(EducationHistoryController.class)
public class EducationHistoryControllerTest {

    @Autowired
    private EducationHistoryController educationHistoryController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EducationHistoryService educationHistoryService;

    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void givenValidEducationHistoryId_ReturnsValidHistory() throws Exception {
        ObjectNode expectedResponseJson = mapper.createObjectNode()
                .put("id","a056116f-1ee2-4013-b263-098e4ea7a1eb")
                .put("clientId","6ad23d7f-0192-44cf-88db-0818c50a9ff5")
                .put("schoolName","Seeder College of Higher Education")
                .put("level","Bachelor's Degree")
                .put("startDate","2000-01-01T23:39:30.980+00:00")
                .put("endDate","2004-12-31T23:39:30.980+00:00");

        EducationHistory expectedResponse = this.mapper.treeToValue(expectedResponseJson, EducationHistory.class);

        EducationHistory entity = this.mapper.convertValue(expectedResponse, EducationHistory.class);
        when(educationHistoryService.getEducationHistoryById(any(UUID.class)))
                .thenReturn(entity);


        // TODO: NEED TO INSERT AUTHENTICATION DATA HERE. IN bearerToken()
        // Current test will fail
        MvcResult result = mvc.perform(get("/education_histories/a056116f-1ee2-4013-b263-098e4ea7a1eb")
                .contentType(MediaType.APPLICATION_JSON)//.with(bearerToken())
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertEquals(expectedResponseJson.toString(), responseBody);
    }
}
