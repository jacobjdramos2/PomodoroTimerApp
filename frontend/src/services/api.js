import axios from 'axios';

const API_BASE_URL = ' http://localhost:3000';
 export const startTimer = async () => {
    try {
        const response = await axios.post(`${API_BASE_URL}/timer/start`);
        return response.data;
    } catch (error) {
        console.error('Error starting the timr:', error);
        throw error;
    }
 };

 export const pauseTimer = async () => {
    try {
        const response = await axios.post(`${API_BASE_URL}/timer/pause`);
        return response.data;
    } catch (error) {
        console.error('Error pausing the timer:', error);
        throw error;
    }
 };

 export const stopTimer = async () => {
    try {
        const response = await axios.post(`${API_BASE_URL}/timer/stop`);
        return response.data;
    } catch (error) {
        console.error('Error stopping the timer:', error);
        throw error;
    }
 };

 export const getTimerStatus = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/timer/status`);
        return response.data;
    } catch (error) {
        console.error('Error getting the timer status:', error);
        throw error;
    }
 };