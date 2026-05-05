import React, { useState, useEffect } from 'react';
import Timer from './components/Timer';
import Controls from './components/Controls';
import { getTimerStatus, startTimer, pauseTimer, stopTimer } from './services/api';
import './App.css';

const App = () => {
    const [timeLeft, setTimeLeft] = useState(1500);
    const [isRunning, setIsRunning] = useState(false);
    const [sessionType, setSessionType] = useState('WORK'); // 'WORK', 'SHORT_BREAK', 'LONG_BREAK'

    // Fetch timer status from backend every 5 seconds
    useEffect(() => {
        const fetchStatus = async () => {
            const status = await getTimerStatus();
            if (status) {
                setTimeLeft(status.remainingTime);
                setSessionType(status.sessionType);
                setIsRunning(status.state === 'RUNNING');
            }
        };

        fetchStatus(); // Fetch immediately
        const interval = setInterval(fetchStatus, 1000);
        return () => clearInterval(interval);
    }, []);

    // Start/Pause/Stop Handlers
    const handleStart = async () => {
        await startTimer();
        const status = await getTimerStatus();
        if (status) {
            setTimeLeft(status.remainingTime);
            setIsRunning(true);
        }
    };

    const handlePause = async () => {
        await pauseTimer();
        const status = await getTimerStatus();
        if (status) {
            setIsRunning(false);
        }
    };

    const handleStop = async () => {
        await stopTimer();
        const status = await getTimerStatus();
        if (status) {
            setIsRunning(false);
            setTimeLeft(1500);
        }
    };

    return (
        <div className="app">
            <h1>Pomodoro Timer</h1>
            <h2>{sessionType === 'WORK' ? "Focus Time!" : sessionType === 'SHORT_BREAK' ? "Short Break" : "Long Break"}</h2>
            <p>{isRunning ? "Timer is running..." : "Timer is stopped."}</p>
            <Timer timeLeft={timeLeft} />
            <Controls onStart={handleStart} onPause={handlePause} onStop={handleStop} />
        </div>
    );
};

export default App;
