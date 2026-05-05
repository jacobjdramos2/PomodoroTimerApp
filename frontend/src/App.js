import React, { useState, useEffect } from 'react';
import Timer from './components/Timer';
import Controls from './components/Controls';
import { getTimerStatus, startTimer, pauseTimer, stopTimer } from './services/api';
import './App.css';

const App = () => {
    const [timeLeft, setTimeLeft] = useState(1500);
    const [isRunning, setIsRunning] = useState(false);
    const [sessionType, setSessionType] = useState('WORK'); // 'WORK', 'SHORT_BREAK', 'LONG_BREAK'
    const [tasks, setTasks] = useState([]);
    const [taskInput, setTaskInput] = useState("");     

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

    useEffect(() => {
        const savedTasks = JSON.parse(localStorage.getItem("pomodoroTasks")) || [];
        setTasks(savedTasks);
    }, []);
    
    useEffect(() => {
        if (tasks.length > 0) {
            localStorage.setItem("pomodoroTasks", JSON.stringify(tasks));
        }
    }, [tasks]);

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

    const addTask = () => {
        if (taskInput.trim() === "") return;
    
        const newTask = {
            id: Date.now(),
            text: taskInput,
            completed: false
        };
    
        setTasks([...tasks, newTask]);
        setTaskInput("");
    };

    const toggleTaskComplete = (id) => {
        setTasks(tasks.map((task) =>
          task.id === id ? { ...task, completed: !task.completed } : task
        ));
    };
      
    const deleteTask = (id) => {
        setTasks(tasks.filter((task) => task.id !== id));
    };

    return (
        <div className="app">
            <div className="app-card">
                <h1 className="app-title">Pomodoro Timer</h1>
                <h2>Focus Time!</h2>
                <p>Timer is {isRunning ? "running" : "stopped"}.</p>

                <Timer timeLeft={timeLeft} />
                <Controls
                    onStart={handleStart}
                    onPause={handlePause}
                    onStop={handleStop}
                />
                <div className="task-section">
                    <h3>Tasks</h3>

                    <div className="task-input-container">
                        <input
                            type="text"
                            placeholder="Add a task..."
                            value={taskInput}
                            onChange={(e) => setTaskInput(e.target.value)}
                        />

                        <button onClick={addTask}>Add</button>
                    </div>

                    <ul className="task-list">
                        {tasks.map((task) => (
                            <li key={task.id} className={task.completed ? "completed-task" : ""}>
                                <span>{task.text}</span>

                                <div className="task-actions">
                                    <button onClick={() => toggleTaskComplete(task.id)}>
                                        {task.completed ? "Undo" : "Done"}
                                    </button>

                                    <button onClick={() => deleteTask(task.id)}>
                                        Delete
                                    </button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default App;
