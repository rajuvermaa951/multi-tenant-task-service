package com.taskservice.multitenanttaskservice.service;

public interface CommonService {

    boolean isManagerOrTl(String loggedUserId, String loggedUserType);

    String generateTaskId();
}
