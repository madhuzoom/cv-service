package com.example.cv.api.service;

import com.example.cv.api.dto.UserDetails;

import java.util.Set;

public interface CvService {

    public Set<UserDetails> displayUserDetails();

    public Set<UserDetails> addRecord(UserDetails newUSD);

    public Set<UserDetails> deleteRecord( UserDetails deleteUSD);

    public Set<UserDetails> updateRecord(UserDetails updateUSD);

}
