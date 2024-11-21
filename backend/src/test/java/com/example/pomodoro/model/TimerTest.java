package com.example.pomodoro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TimerTest {

    private Timer timer;

    // Set up a new Timer before each test
    @BeforeEach
    public void setup() {
        timer = new Timer();
    }

    @Test
    public void testInitialState() {
        assertEquals(0, timer.startTime(), "Start time should be 0");
        assertEquals(3600, timer.remainingTime(), "Remaining time should be 3600 seconds initially");
        assertEquals("STOPPED", timer.getState(), "Timer should start in STOPPED state");
    }

    @Test
    public void testStartTimer() {
        timer.start();
        assertEquals("RUNNING", timer.getState(), "Timer should be running after start");
    }

    @Test
    public void testPauseTimer() {
        timer.start();
        timer.pause();
        assertEquals("PAUSED", timer.getState(), "Timer should be paused");
    }

    @Test
    public void testResumeTimer() {
        timer.start();
        timer.pause();
        timer.resume();
        assertEquals("RUNNING", timer.getState(), "Timer should resume to running state");
    }

    @Test
    public void testResetTimer() {
        timer.start();
        timer.updateStatus(10);  // simulate 10 seconds elapsed
        timer.reset();
        assertEquals(0, timer.startTime(), "Status should reset to 0");
        assertEquals("STOPPED", timer.getState(), "Timer state should reset to STOPPED");
    }

    @Test
    public void testUpdateStatus() {
        timer.start();
        timer.updateStatus(10); // simulate 10 seconds elapsed
        assertEquals(10, timer.startTime(), "Status should be updated to 10");
        assertEquals(3590, timer.remainingTime(), "Remaining time should decrease accordingly");
    }

    @Test
    public void testStopTimer() {
        timer.start();
        timer.stop();
        assertEquals(0, timer.startTime(), "Status should reset to 0 after stopping");
        assertEquals("STOPPED", timer.getState(), "Timer state should be STOPPED after stop");
    }
}
