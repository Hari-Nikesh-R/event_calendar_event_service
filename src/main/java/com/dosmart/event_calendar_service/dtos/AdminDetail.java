package com.dosmart.event_calendar_service.dtos;

import lombok.Data;

@Data
public class AdminDetail {
    private int id;
    private String profilePicture;
    private String email;
    private String firstName;
    private String lastName;
    private String organization;
    private String phoneNumber;
    private String password;
    private String organizationalAddress;
    private boolean authority;
}
