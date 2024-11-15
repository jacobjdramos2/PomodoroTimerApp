/*
 * Represents the timer's properties, like duration, status, and remainingTime
 */
package com.example.pomodoro.model;

public class Timer {
    // Timer status
    public enum State {
        RUNNING, PAUSED, STOPPED
    }
    
    private int status = 0;                 // Current time on timer
    private int duration = 3600;            // 3600 seconds for 1 hour
    private State state = State.STOPPED;    // Timer state

    // startTime: Time when the timer started
    public int startTime() { return status; };

    // endTime: Time when the timer ends (based on the duration)
    public int endTime() {
        if (state == State.RUNNING) {
            return status;
        } 

        return duration;
    }

    // remainingTime: Time left in the session (in ms)
    public int remainingTime() { return duration - status; }

    // isPaused: Whether the timer is paused
    public boolean isPaused() { return state == State.PAUSED; }

    // state: The current state (RUNNING, PAUSED, STOPPED)
    public String getState() { return state.toString(); }

    // reset(): Resets the timer to the initial state.
    public void reset() {
        status = 0;
        state = State.STOPPED;
    } 

    // start 
    public void start() {
        if (state != State.RUNNING) state = State.RUNNING;
    }

    // pause 
    public void pause() {
        if (state == State.RUNNING) state = State.PAUSED;
    }

    // resume
    public void resume() {
        if (state == State.PAUSED) state = State.RUNNING;
    }

    // stop
    public void stop() {
        state = State.STOPPED;
        status = 0;
    }

    // Updaets the status of the timer (ex. incrementing time when running)
    public void updateStatus(int timeElasped) {
        if (state == State.RUNNING) {
            status += timeElasped;
            if (status == duration) {
                status = duration;
                state = State.STOPPED;
            }
        }
    }

    // getStatus(): Returns a string or JSON object with current timer status
    public String getStatus() {
        return "Status (seconds): " + status + 
        "\n Sate: " + state;
    }
}
