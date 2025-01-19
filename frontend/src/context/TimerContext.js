// context/TimerContext.js
import React, { createContext, useState } from 'react';

const TimerContext = createContext();

const TimerProvider = ({ children }) => {
    const [timeLeft, setTimeLeft] = useState(1500); // Default 25 minutes in seconds
    const [isRunning, setIsRunning] = useState(false);

    return (
        <TimerContext.Provider value={{ timeLeft, setTimeLeft, isRunning, setIsRunning }}>
            {children}
        </TimerContext.Provider>
    );
};

export { TimerProvider, TimerContext };
