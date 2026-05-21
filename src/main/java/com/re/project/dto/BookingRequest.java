package com.re.project.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequest {

    private Long departmentId;

    private Long lecturerId;

    private LocalDate sessionDate;

    private LocalTime sessionTime;

    private String note;
}
