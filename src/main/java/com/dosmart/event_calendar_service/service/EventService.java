package com.dosmart.event_calendar_service.service;

import com.dosmart.event_calendar_service.model.CalendarEvent;

import java.util.List;

public interface EventService<T> {
    T save(CalendarEvent calendarEvent);
    List<T> getAllEvents();
    T getCalendarDetail(String title, String description);
}
