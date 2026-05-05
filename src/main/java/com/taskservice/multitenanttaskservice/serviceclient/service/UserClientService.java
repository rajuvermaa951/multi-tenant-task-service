package com.taskservice.multitenanttaskservice.serviceclient.service;

import com.taskservice.multitenanttaskservice.dto.UserDto;

public interface UserClientService {
    public UserDto getUserByUserId(UserDto dto);
}
