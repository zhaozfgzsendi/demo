package com.example.demo.controller;

import com.example.demo.model.TimeResponse;
import com.example.demo.service.TimeService;
import com.example.demo.service.TimeHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    private TimeHttpClient timeHttpClient;

    @GetMapping
    @ResponseBody
    public TimeResponse getCurrentTime() {
        return timeService.getCurrentTimeResponse();
    }

    @GetMapping("/web")
    public String getTimeWebPage(Model model) {
        TimeResponse timeResponse = timeService.getCurrentTimeResponse();
        model.addAttribute("timeResponse", timeResponse);
        return "time";
    }

    @PostMapping("/send")
    @ResponseBody
    public String sendTimeToUrl(@RequestParam String url) {
        try {
            TimeResponse timeResponse = timeService.getCurrentTimeResponse();
            boolean success = timeHttpClient.sendTimeData(url, timeResponse);
            return success ? "Time data sent successfully to " + url : "Failed to send time data to " + url;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}