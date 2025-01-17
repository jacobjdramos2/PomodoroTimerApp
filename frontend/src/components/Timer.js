// Display the countdown and timer progress
import React from 'react';

const Timer = ({ timeLeft }) => {
    const formatTime = (time) => {
        const minutes = Math.floor(time / 60);
        const seconds = time % 60;
        return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    };

    return (
        <div className ="timer">
            <h1>{formatTime(timeLeft)}</h1>
        </div>
    );
};

export default Timer;