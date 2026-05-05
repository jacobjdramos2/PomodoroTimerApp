package com.example.pomodoro.controller;

import com.example.pomodoro.service.TimerService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/timer")
@CrossOrigin(origins = "*")
public class TimerController {

    private final TimerService timerService = new TimerService();

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        return Map.of(
            "state", timerService.getState(),
            "remainingTime", timerService.getTimeLeft(),
            "sessionType", timerService.getSessionType()
        );
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