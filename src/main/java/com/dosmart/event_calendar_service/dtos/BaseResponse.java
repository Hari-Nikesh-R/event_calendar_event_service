package com.dosmart.event_calendar_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String result;
    private Integer code;
    private boolean success;
    private String error;
    private T value;
}
