package com.dosmart.event_calendar_service.model;

import com.dosmart.event_calendar_service.dtos.AdminDetail;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document
public class CalendarEvent {
    @Id
    private String eventId;
    private String department;
    private String createdBy;
    private String modifiedBy;
    private String status;
    private String title;
    private String description;
    private int startHour;
    private int endHour;
    private int startMinute;
    private int endMinute;
    private Date eventStartDate;
    private Date eventEndDate;
    private AdminDetail profile;
    private Date created;
    private Date updated;
    private String location;
    private boolean notifyAll;
    private String eventType;
}
