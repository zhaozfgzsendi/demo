package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeResponse {
    private String time12Hour;
    private String time24Hour;
    private String utcTime;
    private String localTime;
    private String timezone;
    private long epochTime;
}