package com.dosmart.event_calendar_service.service;

import com.dosmart.event_calendar_service.dtos.ConfigModel;

import java.io.IOException;
import java.util.List;

public interface ConfigService {
    List<ConfigModel> getConfigData() throws IOException;
}
