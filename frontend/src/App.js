import React, { useState, useEffect } from 'react';
import Timer from './components/Timer';
import Controls from './components/Controls';
import { getTimerStatus, startTimer, pauseTimer, stopTimer } from './services/api';
import './App.css';

const App = () => {
    const [timeLeft, setTimeLeft] = useState(1500);
    const [isRunning, setIsRunning] = useState(false);

    // Sync with backend every second
    useEffect(() => {
        const fetchStatus = async () => {
            const status = await getTimerStatus();
            if (status) {
                setTimeLeft(status.remainingTime);
                setIsRunning(status.state === 'RUNNING');
            }
        };

        fetchStatus(); // Fetch immediately
        const interval = setInterval(fetchStatus, 5000); // Fetch every 5 sec
        return () => clearInterval(interval);
    }, []);

    // Local timer update logic
    useEffect(() => {
        if (!isRunning) return; // Pause countdown if not running

        const timer = setInterval(() => {
            setTimeLeft(prevTime => Math.max(prevTime - 1, 0));
        }, 1000);

        return () => clearInterval(timer); // Cleanup interval
    }, [isRunning]); // Runs only when `isRunning` changes

    // Button Handlers
    const handleStart = async () => {
        await startTimer();
        setIsRunning(true);
    };

    const handlePause = async () => {
        await pauseTimer();
        setIsRunning(false);
    };

    const handleStop = async () => {
        await stopTimer();
        setIsRunning(false);
        setTimeLeft(1500); // Reset to 25:00
    };

    return (
        <div className="app">
            <Timer timeLeft={timeLeft} />
            <Controls onStart={handleStart} onPause={handlePause} onStop={handleStop} />
            {/* <p>{isRunning ? "Timer is running..." : "Timer is stopped."}</p> */}
        </div>
    );
};

export default App;
