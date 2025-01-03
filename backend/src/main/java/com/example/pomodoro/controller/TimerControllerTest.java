package com.example.pomodoro.controller;

import com.example.pomodoro.service.TimerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TimerController.class)
public class TimerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimerService timerService;

    @Test
    public void testStartTimer() throws Exception {
        // Mock the TimerService behavior
        doNothing().when(timerService).start();

        // Perform the POST request and check the response
        mockMvc.perform(post("/api/timer/start")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Timer started."));

        // Verify that the TimerService start method was called
        verify(timerService, times(1)).start();
    }

    @Test
    public void testGetTimerStatus() throws Exception {
        // Mock the TimerService behavior
        when(timerService.getStatus()).thenReturn("Timer is running");

        // Perform the GET request and check the response
        mockMvc.perform(get("/api/timer/status")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Timer is running"));

        // Verify that the TimerService getStatus method was called
        verify(timerService, times(1)).getStatus();
    }
}
