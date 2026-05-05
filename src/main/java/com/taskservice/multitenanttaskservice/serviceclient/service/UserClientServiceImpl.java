package com.taskservice.multitenanttaskservice.serviceclient.service;

import com.taskservice.multitenanttaskservice.dto.UserDto;
import com.taskservice.multitenanttaskservice.serviceclient.constants.ServiceCodes;
import org.springframework.stereotype.Service;

@Service
public class UserClientServiceImpl implements UserClientService{
    private final RestTemplateService restTemplateService;

    public UserClientServiceImpl(RestTemplateService restTemplateService)
    {
        this.restTemplateService = restTemplateService;
    }


    @Override
    public UserDto getUserByUserId(UserDto request) {
        return restTemplateService.callUserService(ServiceCodes.GET_USER_BY_USERID,request,UserDto.class);
    }
}
