package com.dosmart.event_calendar_service.controller;
import com.dosmart.event_calendar_service.dtos.BaseResponse;
import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    EventService<CalendarEvent> eventEventService;
    @PostMapping(value = "/add")
    public BaseResponse<CalendarEvent> addEvent(@RequestBody CalendarEvent calendarEvent){
        try{
            CalendarEvent event = eventEventService.save(calendarEvent);
            if(Objects.nonNull(event)) {
                return new BaseResponse<>("Event Added successfully", HttpStatus.OK.value(),true, "", event);
            }
            else{
                return new BaseResponse<>("Already present", HttpStatus.OK.value(),false, "Event at same time, duplicate event", null);
            }
        }
        catch (Exception exception)
        {
            return new BaseResponse<>("",HttpStatus.INTERNAL_SERVER_ERROR.value(),false, exception.getMessage(),null);
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
