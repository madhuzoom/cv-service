package com.example.cv.api.dto;

import com.sun.xml.internal.txw2.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class UserDetails {
    private String user;
    private List<String> skills;
    private List<String> companyHistory;

}
