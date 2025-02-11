package com.example.pomodoro.controller;

import com.example.pomodoro.service.TimerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/timer")
@CrossOrigin(origins = "*") // Allow frontend access
public class TimerController {

    private final TimerService timerService;

    @Autowired
    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return timerService.getStatus();
    }

    @PostMapping("/start")
    public void startTimer() {
        timerService.start();
    }

    @PostMapping("/pause")
    public void pauseTimer() {
        timerService.pause();
    }

    @PostMapping("/stop")
    public void stopTimer() {
        timerService.stop();
    }
}
