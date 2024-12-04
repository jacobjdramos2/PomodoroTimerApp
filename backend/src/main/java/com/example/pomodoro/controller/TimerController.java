/*
 * Define REST endpoints, e.g., /start, /stop, /pause
 * Holds the REST controllers that handle incoming HTTP requests and send responses.
 */
package com.example.pomodoro.controller;

import com.example.pomodoro.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/timer")
public class TimerController {

    @Autowired
    private TimerService timerService;

    // Endpoint to start the timer
    @PostMapping("/start")
    public String startTimer() {
        timerService.start();
        return "Timer started.";
    }

    // Endpoint to pause the timer
    @PostMapping("/pause")
    public String pauseTimer() {
        timerService.pause();
        return "Timer paused.";
    }

    // Endpoint to stop the timer
    @PostMapping("/stop")
    public String stopTimer() {
        timerService.stop();
        return "Timer stopped";
    }

    // Endpoint to get the timer's current status
    @PostMapping("/status")
    public String getTimerStatus() {
        return timerService.getStatus();
    }

    /*
     * EXPLANATION
     * @RestController: Indicates that this class handles RESTful HTTP requests and automatically serializes responses into JSON or text.
     * @RequestMapping("/api/timer"): Sets the base URL for all endpoints in this controller.
     * @PostMapping: Used for operations that modify the state of the server (start, pause, stop).
     * @GetMapping: Used for retrieving information (status).
     * @AutoWired: used to inject TimerService and delegate the logic
     */
    
}