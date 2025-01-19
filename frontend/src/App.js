import React, { useState, useEffect } from 'react';
import Timer from './components/Timer';
import Controls from './components/Controls';
import TaskInput from './components/TaskInput';

const App = () => {
    const [timeLeft, setTimeLeft] = useState(1500); // Initial time set to 25 minutes
    const [isRunning, setIsRunning] = useState(false);

    useEffect(() => {
        let timer;
        if (isRunning && timeLeft > 0) {
            timer = setInterval(() => {
                setTimeLeft(prevTime => prevTime - 1);
            }, 1000);
        } else if (!isRunning || timeLeft === 0) {
            clearInterval(timer);
        }

        return () => clearInterval(timer); // Cleanup on component unmount
    }, [isRunning, timeLeft]);

    const handleStart = () => setIsRunning(true);
    const handlePause = () => setIsRunning(false);
    const handleStop = () => {
        setIsRunning(false);
        setTimeLeft(1500); // Reset to 25 minutes
    };

    return (
        <div className="app">
            <TaskInput />
            <Timer timeLeft={timeLeft} />
            <Controls onStart={handleStart} onPause={handlePause} onStop={handleStop} />
        </div>
    );
};

export default App;
