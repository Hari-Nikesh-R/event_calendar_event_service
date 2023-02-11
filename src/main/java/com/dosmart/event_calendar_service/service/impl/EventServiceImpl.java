package com.dosmart.event_calendar_service.service.impl;

import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.repository.CalendarEventRepository;
import com.dosmart.event_calendar_service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService<CalendarEvent> {
    @Autowired
    CalendarEventRepository calendarEventRepository;
    @Override
    public CalendarEvent save(CalendarEvent calendarEvent) {
        Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findByLocation(calendarEvent.getLocation());
        if(optionalCalendarEvent.isPresent())
        {
            if(!optionalCalendarEvent.get().getEventEndDate().before(calendarEvent.getEventStartDate()))
            {
                return null;
            }
        }
        calendarEvent.setCreated(new Date());
        calendarEvent.setCreatedBy("hari.nikesh.r.cce@gmail.com");
        calendarEvent.setStatus("ACTIVE");
        return calendarEventRepository.save(calendarEvent);
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
}
