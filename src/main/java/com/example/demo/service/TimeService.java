package com.example.demo.service;

import com.example.demo.model.TimeResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    private static final DateTimeFormatter TIME_12_HOUR_FORMAT = DateTimeFormatter.ofPattern("hh:mm:ss a");
    private static final DateTimeFormatter TIME_24_HOUR_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter FULL_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TimeResponse getCurrentTimeResponse() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = now.atZone(systemZone);
        ZonedDateTime utcDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        TimeResponse response = new TimeResponse();
        response.setTime12Hour(now.format(TIME_12_HOUR_FORMAT));
        response.setTime24Hour(now.format(TIME_24_HOUR_FORMAT));
        response.setUtcTime(utcDateTime.format(FULL_FORMAT) + " UTC");
        response.setLocalTime(now.format(FULL_FORMAT) + " " + systemZone.toString());
        response.setTimezone(systemZone.toString());
        response.setEpochTime(System.currentTimeMillis());

        return response;
    }
}