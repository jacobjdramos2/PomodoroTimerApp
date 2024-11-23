package com.example.pomodoro.service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerService {

    // Timer states
    private enum State {
        RUNNING, PAUSED, STOPPED
    }

    private AtomicInteger currentTime;              // The curent time in seconds
    private final int duration;                     // Total duration of the timer (in seconds)
    private State state;                            // Current state of the timer
    private ScheduledExecutorService scheduler;     // Executor for managing timer tasks
    private ScheduledFuture<?> scheduledTask;       // Future for the countdown task

    // Constructor to initialize the timer
    public TimerService(int durationInSeconds) {
        this.duration = durationInSeconds;
        this.currentTime = new AtomicInteger(0);          // Start at 0 seconds
        this.state = State.STOPPED;
        this.scheduler = Executors.newSingleThreadScheduledExecutor(); // Using a single thread for timer
    }

    // start(): Initializes and starts the timer with the given durations.
    public void start() {
        if (state == State.RUNNING) {
            System.out.println("Timer is already running.");
            return;
        }

        state = State.RUNNING;
        scheduledTask = scheduler.scheduleAtFixedRate(this::updateTime, 0, 1, TimeUnit.SECONDS);
        System.out.println("Timer started.");
    }

    // pause(): Pauses the timer and saves the current state.
    public void pause() {
        if (state == State.RUNNING) {
            scheduledTask.cancel(false); // Cancel current task without interrupting it
            state = State.PAUSED;
            System.out.println("Timer paused.");
        }
    }

    // stop(): Stops the timer and resets it to its initial state.
    public void stop() {
        if (state == State.RUNNING || state == State.PAUSED) {
            scheduledTask.cancel(false);
            currentTime.set(0);
            state = State.STOPPED;
            System.out.println("Timer stopped and reset.");
        }

    }

    // getStatus(): Returns the current state of the timer (running, paused, or stopped).
    public String getStatus() {
        return "Current State: " + state + ", Time elasped: " + currentTime.get() + " seconds.";
    }

    // updateTime(): Updates the currrent time by 1 second
    private void updateTime() {
        if (currentTime.get() < duration) {
            currentTime.incrementAndGet();
        } else {
            stop();
            System.out.println("Timer reached its duration and stopped.");
        }
    }

    // Tip: Use ScheduledExecutorService or Timer for managing timer tasks.

    // Helper method to test the timer behavior
    public static void main(String[] args) throws InterruptedException {
        TimerService timerService = new TimerService(10); // 10 seconds timer
        timerService.start();
        Thread.sleep(5000); // Wait 5 seconds
        System.out.println(timerService.getStatus());
        timerService.pause();
        Thread.sleep(2000);
        System.out.println(timerService.getStatus());
        timerService.start();
        Thread.sleep(4000);
        System.out.println(timerService.getStatus());
        timerService.stop();
    }
}
