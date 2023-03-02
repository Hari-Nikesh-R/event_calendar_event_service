package com.dosmart.event_calendar_service.service.impl;

import com.dosmart.event_calendar_service.dtos.ConfigModel;
import com.dosmart.event_calendar_service.service.ConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Override
    public List<ConfigModel> getConfigData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ConfigModel[] configModel = objectMapper.readValue(new File("D:\\This PC Hari\\SECE\\Extracurriculam\\SECE Projects\\SECE Event Calendar\\event_calendar_event_service\\src\\main\\java\\com\\dosmart\\event_calendar_service\\config.json"), ConfigModel[].class);
        return new ArrayList<>(Arrays.asList(configModel));
    }
}
