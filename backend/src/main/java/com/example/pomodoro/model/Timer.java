package com.example.pomodoro.model;

public class Timer {
    public enum State {
        RUNNING, PAUSED, STOPPED
    }

    private int status = 0;                 // Current elapsed time in seconds
    private final int duration = 3600;      // Default: 1-hour session (3600 seconds)
    private State state = State.STOPPED;

    // Getters for status
    public int getStartTime() { return status; }
    public int getEndTime() { return status + (duration - status); }
    public int getRemainingTime() { return Math.max(duration - status, 0); }
    public boolean isPaused() { return state == State.PAUSED; }
    public String getState() { return state.toString(); }

    // Reset timer
    public void reset() {
        status = 0;
        state = State.STOPPED;
    }

    // Timer controls
    public void start() { if (state != State.RUNNING) state = State.RUNNING; }
    public void pause() { if (state == State.RUNNING) state = State.PAUSED; }
    public void resume() { if (state == State.PAUSED) state = State.RUNNING; }
    public void stop() {
        state = State.STOPPED;
        status = 0;
    }

    // Update timer status (increment time while running)
    public void updateStatus(int timeElapsed) {
        if (state == State.RUNNING) {
            status += timeElapsed;
            if (status >= duration) {
                status = duration;
                state = State.STOPPED; // Auto-stop when duration ends
            }
        }
    }

    // Return JSON-formatted status
    public String getStatus() {
        return "{ \"elapsedTime\": " + status + 
               ", \"remainingTime\": " + getRemainingTime() + 
               ", \"state\": \"" + state + "\" }";
    }
}
