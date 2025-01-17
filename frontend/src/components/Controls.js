// This will display the start, pause, and stop buttons
import React from 'react';

const Controls = ({ onStart, onPause, onStop }) => {
    return (
        <div className="controls">
            <button onClick={onStart}>Start</button>
            <button onClick={onPause}>Pause</button>
            <button onClick={onStop}>Stop</button>
        </div>
    );
};

export default Controls;