package com.example.cv.api.service.impl;

import com.example.cv.api.dto.UserDetails;
import com.example.cv.api.service.CvService;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CvServiceImpl implements CvService {

    Set<UserDetails> userDetailsSet = new HashSet<>();

    @PostConstruct
    public void initilizeUserDetails(){
        List<String> skillsList = new ArrayList<>();
        skillsList.add("java1");
        List<String> companyHostoryList = new ArrayList<>();
        companyHostoryList.add("HSBC1");
        UserDetails usd = new UserDetails("user1", skillsList, companyHostoryList);
        userDetailsSet.add(usd);
    }

    @Override
    public Set<UserDetails> displayUserDetails() {
        return userDetailsSet;
    }

    @Override
    public Set<UserDetails> addRecord(UserDetails newUSD) {
        userDetailsSet.add(newUSD);
        return userDetailsSet;
    }

    @Override
    public Set<UserDetails> deleteRecord(UserDetails deleteUSD) {
        userDetailsSet.remove(deleteUSD);
        return userDetailsSet;
    }

    @Override
    public Set<UserDetails> updateRecord(UserDetails updateUSD) {
        Set<UserDetails> updatedUSDSet = new HashSet<>();
        userDetailsSet.stream().forEach(ud -> {
            if(ud.getUser().equals(updateUSD.getUser()) ){
                updatedUSDSet.add(updateUSD);
            } else{
                updatedUSDSet.add(ud);
            }
        });
        userDetailsSet = updatedUSDSet;
        return userDetailsSet;
    }

    public Set<UserDetails> getUserDetailsSet() {
        return userDetailsSet;
    }
}
