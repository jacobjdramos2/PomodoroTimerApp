package com.example.pomodoro.controller;

import com.example.pomodoro.service.TimerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimerController.class)
public class TimerControllerTest {

    @Autowired
    private MockMvc mockMvc; // For sending mock HTTP requests

    @MockBean
    private TimerService timerService; // Mock the TimerService

    @BeforeEach
    public void setup() {
        // Mock TimerService behavior for each test case
        when(timerService.getStatus()).thenReturn("Current State: STOPPED, Time elapsed: 0 seconds.");
    }

    @Test
    public void testStartTimer() throws Exception {
        // Simulate the behavior of the start() method
        doNothing().when(timerService).start();

        mockMvc.perform(post("/api/timer/start")) // Send a POST request
                .andExpect(status().isOk()) // Assert HTTP 200 response
                .andExpect(content().string("Timer started.")); // Assert response body
    }

    @Test
    public void testPauseTimer() throws Exception {
        // Simulate the behavior of the pause() method
        doNothing().when(timerService).pause();

        mockMvc.perform(post("/api/timer/pause"))
                .andExpect(status().isOk())
                .andExpect(content().string("Timer paused."));
    }

    @Test
    public void testStopTimer() throws Exception {
        // Simulate the behavior of the stop() method
        doNothing().when(timerService).stop();

        mockMvc.perform(post("/api/timer/stop"))
                .andExpect(status().isOk())
                .andExpect(content().string("Timer stopped.")); // Corrected the response string
    }

    @Test
    public void testGetTimerStatus() throws Exception {
        // Simulate the behavior of the getStatus() method
        when(timerService.getStatus()).thenReturn("Current State: STOPPED, Time elapsed: 0 seconds.");

        mockMvc.perform(get("/api/timer/status")) // Send a GET request
                .andExpect(status().isOk()) // Assert HTTP 200 response
                .andExpect(content().string("Current State: STOPPED, Time elapsed: 0 seconds.")); // Assert response body
    }
}
