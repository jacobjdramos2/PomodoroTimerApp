package com.example.pomodoro.service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerService {
    public enum State {
        RUNNING, PAUSED, STOPPED
    }

    public enum SessionType {
        WORK, SHORT_BREAK, LONG_BREAK
    }

    private AtomicInteger currentTime;
    private State state;
    private SessionType sessionType;
    private int sessionCount;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledTask;

    private static final int WORK_DURATION = 1500;
    private static final int SHORT_BREAK_DURATION = 300;
    private static final int LONG_BREAK_DURATION = 900;

    public TimerService() {
        this.currentTime = new AtomicInteger(WORK_DURATION);
        this.state = State.STOPPED;
        this.sessionType = SessionType.WORK;
        this.sessionCount = 0;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        if (state == State.RUNNING) return;

        state = State.RUNNING;
        scheduledTask = scheduler.scheduleAtFixedRate(this::updateTime, 0, 1, TimeUnit.SECONDS);
    }

    public void pause() {
        if (state == State.RUNNING && scheduledTask != null) {
            scheduledTask.cancel(false);
            state = State.PAUSED;
        }
    }

    public void stop() {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }
        state = State.STOPPED;
        currentTime.set(WORK_DURATION);
        sessionType = SessionType.WORK;
        sessionCount = 0;
    }

    private void updateTime() {
        if (currentTime.get() > 0) {
            currentTime.decrementAndGet();
        } else {
            switchSession();
        }
    }

    private void switchSession() {
        if (sessionType == SessionType.WORK) {
            sessionCount++;
            if (sessionCount % 4 == 0) {
                sessionType = SessionType.LONG_BREAK;
                currentTime.set(LONG_BREAK_DURATION);
            } else {
                sessionType = SessionType.SHORT_BREAK;
                currentTime.set(SHORT_BREAK_DURATION);
            }
        } else {
            sessionType = SessionType.WORK;
            currentTime.set(WORK_DURATION);
        }
    }

    public int getTimeLeft() {
        return currentTime.get();
    }

    public String getSessionType() {
        return sessionType.toString();
    }

    public String getState() {
        return state.toString();
    }
}