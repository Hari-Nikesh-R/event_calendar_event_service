package com.dosmart.event_calendar_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ConfigModel {
    private String key;
    private List<String> value;
}
