import React from 'react';

const Timer = ({ timeLeft }) => {
    const minutes = Math.floor(timeLeft / 60);
    const seconds = timeLeft % 60;

    const formattedMinutes = String(minutes).padStart(2, '0');
    const formattedSeconds = String(seconds).padStart(2, '0');

    return <div className="timer">{formattedMinutes}:{formattedSeconds}</div>;
};

export default Timer;
