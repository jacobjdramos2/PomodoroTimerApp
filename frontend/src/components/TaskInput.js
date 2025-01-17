// Allows user to input tasks they want to focus on during the Pomodoro session

import React, { useState } from 'react';

const TaskInput = ({ onTaskSubmit }) => {
    const [task, setTask] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onTaskSubmit(task);
        setTask('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={task}
                onChange={(e) => setTask(e.target.value)}
                placeholder="Enter your task"
            />
            <button type="submit">Add Task</button>
        </form>
    );
}

export default TaskInput;