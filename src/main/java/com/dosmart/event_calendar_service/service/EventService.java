package com.dosmart.event_calendar_service.service;

import com.dosmart.event_calendar_service.model.CalendarEvent;

public interface EventService<T> {
    T save(CalendarEvent calendarEvent);
}
