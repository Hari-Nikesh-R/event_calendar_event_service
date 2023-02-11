package com.dosmart.event_calendar_service.service.impl;

import com.dosmart.event_calendar_service.dtos.BaseResponse;
import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.repository.CalendarEventRepository;
import com.dosmart.event_calendar_service.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EventServiceImpl implements EventService<CalendarEvent> {
    @Autowired
    CalendarEventRepository calendarEventRepository;
    @Override
    public CalendarEvent save(CalendarEvent calendarEvent) {
        Optional<List<CalendarEvent>> optionalCalendarEvent = calendarEventRepository.findByLocation(calendarEvent.getLocation());
        if(optionalCalendarEvent.isPresent()) {
            for (CalendarEvent event : optionalCalendarEvent.get()) {
                if (changeDateToDateTime(event.getEventStartDate()) == changeDateToDateTime(calendarEvent.getEventStartDate())){
                    if (calendarEvent.getStartHour() >= event.getStartHour() && calendarEvent.getStartHour() <= event.getStartHour()) {
                        return null;
                    }
                    if (calendarEvent.getEndHour() >= event.getStartHour() && calendarEvent.getEndHour() <= event.getEndHour()) {
                        return null;
                    }
                    if(calendarEvent.getStartHour() > event.getStartHour() && calendarEvent.getEndHour() >= event.getEndHour()){
                        return null;
                    }
                }

            }
        }
        calendarEvent.setCreated(new Date());
        calendarEvent.setCreatedBy("hari.nikesh.r.cce@gmail.com");
        calendarEvent.setStatus("ACTIVE");
        return calendarEventRepository.save(calendarEvent);
    }

    @Override
    public BaseResponse<String> modifyEvent(String eventId, CalendarEvent calendarEvent) {
        Optional<CalendarEvent> optionalCalendarEvent =  calendarEventRepository.findById(eventId);
        if(optionalCalendarEvent.isPresent())
        {
            BeanUtils.copyProperties(calendarEvent, optionalCalendarEvent.get());
            calendarEventRepository.save(optionalCalendarEvent.get());
            return new BaseResponse<>("Modification Successful", HttpStatus.OK.value(),true,"","Modified Event");
        }
        return new BaseResponse<>("Modification UnSuccessful", HttpStatus.NO_CONTENT.value(), false, "Event not found, Something went wrong",null);
    }

    @Override
    public BaseResponse<String> deleteEvent(String eventId) {
        calendarEventRepository.deleteById(eventId);
        return new BaseResponse<>("Deleted Successfully",HttpStatus.OK.value(),true,"","Deleted the event");
    }

    @Override
    public List<CalendarEvent> getAllEvents() {
        return calendarEventRepository.findAll();
    }

    @Override
    public CalendarEvent getCalendarDetail(String title, String description) {
        Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findByTitleAndDescription(title, description);
        return optionalCalendarEvent.orElse(null);
    }
    private Integer changeDateToDateTime(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
}
