package com.dosmart.event_calendar_service.repository;

import com.dosmart.event_calendar_service.model.CalendarEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CalendarEventRepository extends MongoRepository<CalendarEvent,String> {
    Optional<CalendarEvent> findByEventStartDateAndStartHourAndLocation(Date eventStartDate, Integer startHour, String location);
    Optional<CalendarEvent> findByLocation(String location);
    Optional<CalendarEvent> findByTitleAndDescription(String title, String description);

}
