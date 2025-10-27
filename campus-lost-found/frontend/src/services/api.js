import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// User APIs
export const registerUser = (userData) => api.post('/users/register', userData);
export const loginUser = (credentials) => api.post('/users/login', credentials);
export const getUserById = (id) => api.get(`/users/${id}`);

// Item APIs
export const getAllItems = () => api.get('/items');
export const getItemById = (id) => api.get(`/items/${id}`);
export const createItem = (itemData) => api.post('/items', itemData);
export const updateItem = (id, itemData) => api.put(`/items/${id}`, itemData);
export const deleteItem = (id) => api.delete(`/items/${id}`);
export const searchItems = (keyword) => api.get(`/items/search?keyword=${keyword}`);
export const getItemsByStatus = (status) => api.get(`/items/status/${status}`);

// Claim APIs
export const getAllClaims = () => api.get('/claims');
export const createClaim = (claimData) => api.post('/claims', claimData);
export const getClaimsByItem = (itemId) => api.get(`/claims/item/${itemId}`);
export const updateClaimStatus = (id, statusData) => api.put(`/claims/${id}/status`, statusData);

export default api;
