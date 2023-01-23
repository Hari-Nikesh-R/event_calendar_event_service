package com.dosmart.event_calendar_service.controller;
import com.dosmart.event_calendar_service.dtos.BaseResponse;
import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    EventService<CalendarEvent> eventEventService;
    @PostMapping(value = "/add")
    public BaseResponse<CalendarEvent> addEvent(CalendarEvent calendarEvent){
        try{
            CalendarEvent event = eventEventService.save(calendarEvent);
            if(Objects.nonNull(event)) {
                return new BaseResponse<>("Event Added successfully", HttpStatus.OK.value(),true, "", event);
            }
            else{
                return new BaseResponse<>("Event not created", HttpStatus.OK.value(),false, "Event not created", null);
            }
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("",HttpStatus.INTERNAL_SERVER_ERROR.value(),false, exception.getMessage(),null);
        }
    }
}
