package com.dosmart.event_calendar_service.repository;

import com.dosmart.event_calendar_service.model.CalendarEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends MongoRepository<CalendarEvent,String> {

}
