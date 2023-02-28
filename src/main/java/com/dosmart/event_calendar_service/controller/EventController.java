package com.dosmart.event_calendar_service.controller;
import com.dosmart.event_calendar_service.dtos.BaseResponse;
import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.dosmart.event_calendar_service.utils.Constants.AUTHORIZATION;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    EventService<CalendarEvent> eventEventService;
    @PostMapping(value = "/add")
    public BaseResponse<CalendarEvent> addEvent(@RequestBody CalendarEvent calendarEvent, @RequestHeader(AUTHORIZATION) String token){
        try{

            CalendarEvent event = eventEventService.save(calendarEvent, token);
            if(Objects.nonNull(event)) {
                return new BaseResponse<>("Event Added successfully", HttpStatus.OK.value(),true, "", event);
            }
            else{
                return new BaseResponse<>("Already present", HttpStatus.OK.value(),false, "Event at same time, duplicate event", null);
            }
        }
        catch (Exception exception)
        {
            BaseResponse<CalendarEvent> baseResponse = new BaseResponse<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, exception.getMessage(), null);
            if (baseResponse.getError().contains("401")) {
                baseResponse.setCode(401);
            }
            return baseResponse;
        }
    }
    @GetMapping(value = "/all")
    public BaseResponse<List<CalendarEvent>> getAllEvents(){
        try{
            List<CalendarEvent> calendarEvents = eventEventService.getAllEvents();
            if(Objects.nonNull(calendarEvents))
            {
                return new BaseResponse<>("Created Events", HttpStatus.OK.value(), true, "",calendarEvents);
            }
            else{
                return new BaseResponse<>("No Events present", HttpStatus.NO_CONTENT.value() ,false,"No event present, Create a new event",null);
            }
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("",HttpStatus.INTERNAL_SERVER_ERROR.value(),false, exception.getMessage(),null);
        }
    }

    @GetMapping(value = "/get")
    public BaseResponse<CalendarEvent> getCalendarDetail(@RequestParam("title") String title, @RequestParam("description") String description){
        try {
            CalendarEvent calendarEvent = eventEventService.getCalendarDetail(title, description);
            if(Objects.nonNull(calendarEvent)){
                return new BaseResponse<>("Successfully fetched",HttpStatus.OK.value(), true, "", calendarEvent);
            }
            else{
                return new BaseResponse<>("Unable to fetch", HttpStatus.NO_CONTENT.value(), false, "No event found", null);
            }
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("",HttpStatus.INTERNAL_SERVER_ERROR.value(),false, exception.getMessage(),null);
        }

    }
    @PutMapping(value = "/modify")
    public BaseResponse<String> modifyEvent(@RequestBody CalendarEvent calendarEvent)
    {
        try{
            return eventEventService.modifyEvent(calendarEvent.getEventId(),calendarEvent);
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("",HttpStatus.INTERNAL_SERVER_ERROR.value(),false, exception.getMessage(),null);
        }
    }
    @DeleteMapping(value = "/delete")
    public BaseResponse<String> deleteEvent(@RequestParam("id") String eventId){
        try {
            return eventEventService.deleteEvent(eventId);
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("",HttpStatus.INTERNAL_SERVER_ERROR.value(),false, exception.getMessage(),null);
        }
    }
}
