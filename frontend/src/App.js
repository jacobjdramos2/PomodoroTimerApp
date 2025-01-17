// frontend/src/App.js
import React, { useState, useEffect } from 'react';
import Timer from './components/Timer';
import Controls from './components/Controls';
import TaskInput from './components/TaskInput';

const App = () => {
    const [timeLeft, setTimeLeft] = useState(1500); // 25 minutes in seconds
    const [isRunning, setIsRunning] = useState(false);

    useEffect(() => {
        let timer;
        if (isRunning) {
            timer = setInterval(() => {
                setTimeLeft((prevTime) => (prevTime > 0 ? prevTime - 1 : 0));
            }, 1000);
        } else {
            clearInterval(timer);
        }
        return () => clearInterval(timer);
    }, [isRunning]);

    const handleStart = () => setIsRunning(true);
    const handlePause = () => setIsRunning(false);
    const handleStop = () => {
        setIsRunning(false);
        setTimeLeft(1500); // Reset to 25 minutes
    };

    const handleTaskSubmit = (task) => {
        console.log(`Task added: ${task}`);
    };

    return (
        <div className="app">
            <TaskInput onTaskSubmit={handleTaskSubmit} />
            <Timer timeLeft={timeLeft} />
            <Controls onStart={handleStart} onPause={handlePause} onStop={handleStop} />
        </div>
    );
};

export default App;
