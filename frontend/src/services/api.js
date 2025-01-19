// src/services/api.js
import axios from 'axios';

const API_URL = 'your-backend-url-here'; // Replace with your backend URL

export const startTimer = async () => {
    try {
        const response = await axios.post(`${API_URL}/start`);
        return response.data; // You can modify based on your API response
    } catch (error) {
        console.error('Error starting timer:', error);
    }
};

export const pauseTimer = async () => {
    try {
        const response = await axios.post(`${API_URL}/pause`);
        return response.data; // Modify based on your API response
    } catch (error) {
        console.error('Error pausing timer:', error);
    }
};

export const stopTimer = async () => {
    try {
        const response = await axios.post(`${API_URL}/stop`);
        return response.data; // Modify based on your API response
    } catch (error) {
        console.error('Error stopping timer:', error);
    }
};

export const getTimerStatus = async () => {
    try {
        const response = await axios.get(`${API_URL}/status`);
        return response.data; // Modify based on your API response
    } catch (error) {
        console.error('Error fetching timer status:', error);
    }
};
