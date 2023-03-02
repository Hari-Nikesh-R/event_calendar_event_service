package com.dosmart.event_calendar_service.controller;

import com.dosmart.event_calendar_service.dtos.BaseResponse;
import com.dosmart.event_calendar_service.dtos.ConfigModel;
import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    ConfigService configService;
    @GetMapping
    public BaseResponse<List<ConfigModel>> getConfig(){
        try{
            List<ConfigModel> data = configService.getConfigData();
            if(Objects.nonNull(data)){
                return new BaseResponse<>("Fetched Successfully", HttpStatus.OK.value(),true, "", data);
            }
            else{
                return new BaseResponse<>("Fetch unSucessfull", HttpStatus.NO_CONTENT.value(), false, "Unable to Read file", null);
            }
        }
        catch (Exception exception){
            BaseResponse<List<ConfigModel>> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }
}
