package com.taskservice.multitenanttaskservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse <T>{
    private String status;
    private String message;
    private String errmsg;
    private T data;
}
