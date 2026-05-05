package com.taskservice.multitenanttaskservice.serviceclient.service;

public interface RestTemplateService {

    public <T, R> R call(String serviceName,String serviceCode, T request, Class<R> responseType);

    public <T,R> R callUserService(String serviceCode , T request,Class<R> responseType);
}
