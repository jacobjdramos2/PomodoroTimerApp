package com.example.pomodoro.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service // Marks this as a Spring service
public class TimerService {

    // Timer states
    private enum State {
        RUNNING, PAUSED, STOPPED
    }

    private AtomicInteger currentTime;            // The current time in seconds
    private int duration = 1500;                  // Default: 25 minutes (1500 seconds)
    private State state;                          // Current state of the timer
    private ScheduledExecutorService scheduler;   // Executor for managing timer tasks
    private ScheduledFuture<?> scheduledTask;     // Future for the countdown task

    // Constructor to initialize the timer
    public TimerService() {
        this.currentTime = new AtomicInteger(0);  // Start at 0 seconds
        this.state = State.STOPPED;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    // Start the timer
    public void start() {
        if (state == State.RUNNING) {
            System.out.println("Timer is already running.");
            return;
        }

        state = State.RUNNING;
        scheduledTask = scheduler.scheduleAtFixedRate(this::updateTime, 0, 1, TimeUnit.SECONDS);
        System.out.println("Timer started.");
    }

    // Pause the timer
    public void pause() {
        if (state == State.RUNNING) {
            scheduledTask.cancel(false);
            state = State.PAUSED;
            System.out.println("Timer paused.");
        }
    }

    // Stop the timer and reset it
    public void stop() {
        if (state == State.RUNNING || state == State.PAUSED) {
            if (scheduledTask != null) {
                scheduledTask.cancel(false);
            }
            currentTime.set(0);
            state = State.STOPPED;
            System.out.println("Timer stopped and reset.");
        }
    }

    // Get the current timer status
    public String getStatus() {
        return String.format("Current State: %s, Time elapsed: %d seconds.", state, currentTime.get());
    }

    // Update the time if the timer is running
    private void updateTime() {
        if (currentTime.get() < duration) {
            currentTime.incrementAndGet();
            System.out.println("Time Updated: " + currentTime.get()); // 🔥 Debugging log
        } else {
            stop();
            System.out.println("Timer reached its duration and stopped.");
        }
    }
    
}
