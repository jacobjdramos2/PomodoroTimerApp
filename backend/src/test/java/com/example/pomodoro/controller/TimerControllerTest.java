// package com.example.pomodoro.controller;

// import com.example.pomodoro.service.TimerService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(controllers = TimerController.class) // Focused on the TimerController
// public class TimerControllerTest {

//     @Autowired
//     private MockMvc mockMvc; // Allows us to perform HTTP requests

//     @MockBean
//     private TimerService timerService; // Mocks TimerService to isolate the controller

//     @Test
//     public void testStartTimer() throws Exception {
//         doNothing().when(timerService).start();

//         mockMvc.perform(post("/api/timer/start")) // Simulate HTTP POST request
//                 .andExpect(status().isOk()) // Expect HTTP 200 OK response
//                 .andExpect(content().string("Timer started.")); // Validate response body
//     }

//     @Test
//     public void testPauseTimer() throws Exception {
//         doNothing().when(timerService).pause();

//         mockMvc.perform(post("/api/timer/pause"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("Timer paused."));
//     }

//     @Test
//     public void testStopTimer() throws Exception {
//         doNothing().when(timerService).stop();

//         mockMvc.perform(post("/api/timer/stop"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("Timer stopped."));
//     }

//     @Test
//     public void testGetTimerStatus() throws Exception {
//         when(timerService.getStatus()).thenReturn("Current State: STOPPED, Time elapsed: 0 seconds.");

//         mockMvc.perform(get("/api/timer/status")) // Simulate HTTP GET request
//                 .andExpect(status().isOk()) // Expect HTTP 200 OK response
//                 .andExpect(content().string("Current State: STOPPED, Time elapsed: 0 seconds.")); // Validate response body
//     }
// }
