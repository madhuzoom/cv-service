package com.example.cv.api.controller;

import com.example.cv.api.dto.UserDetails;
import com.example.cv.api.service.impl.CvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/cvservice")
public class CvServiceController {

    @Autowired
    CvServiceImpl cvServiceImpl;

    @GetMapping(value = "/readrecords")
    public Set<UserDetails> displayUserDetails(){
        return cvServiceImpl.displayUserDetails();
    }

    @PostMapping(value = "/addrecord")
    public Set<UserDetails> addRecord(@RequestBody UserDetails newUSD){
        return cvServiceImpl.addRecord(newUSD);
    }

    @DeleteMapping(value = "/deleterecord")
    public Set<UserDetails> deleteRecord(@RequestBody UserDetails deleteUSD){
        return cvServiceImpl.deleteRecord(deleteUSD);
    }

    @PutMapping(value = "/updaterecord")
    public Set<UserDetails> updateRecord(@RequestBody UserDetails updateUSD){
        return cvServiceImpl.updateRecord(updateUSD);
    }
}
