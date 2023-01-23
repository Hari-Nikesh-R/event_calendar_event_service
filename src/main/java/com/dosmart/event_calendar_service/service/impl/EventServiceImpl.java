package com.dosmart.event_calendar_service.service.impl;

import com.dosmart.event_calendar_service.dtos.AdminDetail;
import com.dosmart.event_calendar_service.model.CalendarEvent;
import com.dosmart.event_calendar_service.repository.CalendarEventRepository;
import com.dosmart.event_calendar_service.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService<CalendarEvent> {
    @Autowired
    CalendarEventRepository calendarEventRepository;
    @Override
    public CalendarEvent save(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }
}
