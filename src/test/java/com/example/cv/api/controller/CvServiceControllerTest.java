package com.example.cv.api.controller;

import com.example.cv.api.dto.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CvServiceControllerTest {

    final String BASE_URI = "http://localhost:8181/cvservice";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void displayUserDetails() throws Exception {
        String uri = BASE_URI+"/readrecords";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals( "[{\"user\":\"user1\",\"skills\":[\"java1\"],\"companyHistory\":[\"HSBC1\"]}]", content);
    }

    @Test
    void addRecord() throws Exception {
        String uri = BASE_URI+"/addrecord";
        List<String> skillsList = new ArrayList<>();
        skillsList.add("java2");
        List<String> companyHostoryList = new ArrayList<>();
        companyHostoryList.add("HSBC2");
        UserDetails newUSD = new UserDetails("user2", skillsList, companyHostoryList);

        String inputJson = mapToJson(newUSD);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"user\":\"user1\",\"skills\":[\"java1\"],\"companyHistory\":[\"HSBC1\"]}," +
                "{\"user\":\"user2\",\"skills\":[\"java2\"],\"companyHistory\":[\"HSBC2\"]}]", content);
    }

    @Test
    void updateRecord()throws Exception {
        addRecord();
        String uri = BASE_URI+"/updaterecord";
        List<String> skillsList = new ArrayList<>();
        skillsList.add("java2");
        List<String> companyHostoryList = new ArrayList<>();
        companyHostoryList.add("HSBC3");
        UserDetails newUSD = new UserDetails("user2", skillsList, companyHostoryList);

        String inputJson = mapToJson(newUSD);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"user\":\"user1\",\"skills\":[\"java1\"],\"companyHistory\":[\"HSBC1\"]}," +
                "{\"user\":\"user2\",\"skills\":[\"java2\"],\"companyHistory\":[\"HSBC3\"]}]",content );
    }


    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}