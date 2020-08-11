package com.example.cv.api.service.impl;

import com.example.cv.api.dto.UserDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CvServiceImplTest {
    CvServiceImpl cvServiceImpl = new CvServiceImpl();
    @BeforeEach
    void setUp() {
        cvServiceImpl.initilizeUserDetails();
    }

    @AfterEach
    void tearDown() {
        cvServiceImpl=null;
    }

    @Test
    void displayUserDetails() {
        assertEquals(1,cvServiceImpl.getUserDetailsSet().size());
    }

    @Test
    void addRecord() {

        List<String> skillsList = new ArrayList<>();
        skillsList.add("java2");
        List<String> companyHostoryList = new ArrayList<>();
        companyHostoryList.add("HSBC2");
        UserDetails newUSD = new UserDetails("user2", skillsList, companyHostoryList);
        assertEquals(2,cvServiceImpl.addRecord(newUSD).size());
    }

    @Test
    void deleteRecord() {
        List<String> skillsList = new ArrayList<>();
        skillsList.add("java2");
        List<String> companyHostoryList = new ArrayList<>();
        companyHostoryList.add("HSBC2");
        UserDetails newUSD = new UserDetails("user2", skillsList, companyHostoryList);
        assertEquals(1,cvServiceImpl.deleteRecord(newUSD).size());
    }

    @Test
    void updateRecord() {
        List<String> skillsList = new ArrayList<>();
        skillsList.add("java2");
        List<String> companyHostoryList = new ArrayList<>();
        companyHostoryList.add("HSBC3");
        UserDetails newUSD = new UserDetails("user2", skillsList, companyHostoryList);
        addRecord();
        assertEquals(2,cvServiceImpl.updateRecord(newUSD).size());
    }

    @Test
    void setUserDetailsSet() {
    }
}