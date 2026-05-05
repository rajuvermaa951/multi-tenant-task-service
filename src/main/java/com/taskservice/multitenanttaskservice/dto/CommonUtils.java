package com.taskservice.multitenanttaskservice.dto;

public class CommonUtils {
    public static boolean isNullOrBlank(String organizationId) {
        return organizationId == null || organizationId.isBlank();
    }
}
