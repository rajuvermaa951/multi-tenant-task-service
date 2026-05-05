package com.taskservice.multitenanttaskservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AppConstant {
    public static final String INVALID_LOGGED_IN_USER = "INVALID LOGGED IN DETAILS";
    public static List<String> EMPTY_LIST =new ArrayList<>();
}
