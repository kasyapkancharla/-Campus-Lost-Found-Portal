import React, { useState } from 'react';
import { createItem } from '../services/api';
import { useNavigate } from 'react-router-dom';
import './AddItem.css';

export default function AddItem() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    category: '',
    status: 'LOST',
    location: '',
    dateTime: '',
    contactInfo: '',
    user: { id: 1 } // You should get this from logged-in user
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setError('');

    // Get logged-in user from localStorage
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      setError('Please login first');
      setTimeout(() => navigate('/login'), 2000);
      return;
    }

    const itemData = {
      ...formData,
      dateTime: new Date(formData.dateTime).toISOString(),
      user: { id: user.id }
    };

    try {
      await createItem(itemData);
      setMessage('Item added successfully!');
      setTimeout(() => {
        navigate('/items');
      }, 2000);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to add item');
    }
  };

  return (
    <div className="additem-container">
      <div className="additem-card">
        <h2>Add New Item</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Title *</label>
            <input
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Description *</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
              rows="4"
              required
            ></textarea>
          </div>

          <div className="form-group">
            <label>Category *</label>
            <select
              name="category"
              value={formData.category}
              onChange={handleChange}
              required
            >
              <option value="">Select Category</option>
              <option value="Electronics">Electronics</option>
              <option value="Books">Books</option>
              <option value="Clothing">Clothing</option>
              <option value="Accessories">Accessories</option>
              <option value="Documents">Documents</option>
              <option value="Others">Others</option>
            </select>
          </div>

          <div className="form-group">
            <label>Status *</label>
            <select
              name="status"
              value={formData.status}
              onChange={handleChange}
              required
            >
              <option value="LOST">Lost</option>
              <option value="FOUND">Found</option>
            </select>
          </div>

          <div className="form-group">
            <label>Location *</label>
            <input
              type="text"
              name="location"
              value={formData.location}
              onChange={handleChange}
              placeholder="e.g., Library, Cafeteria"
              required
            />
          </div>

          <div className="form-group">
            <label>Date & Time *</label>
            <input
              type="datetime-local"
              name="dateTime"
              value={formData.dateTime}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Contact Info *</label>
            <input
              type="text"
              name="contactInfo"
              value={formData.contactInfo}
              onChange={handleChange}
              placeholder="Email or Phone"
              required
            />
          </div>

          <button type="submit" className="btn-primary">Add Item</button>
        </form>

        {message && <p className="success-message">{message}</p>}
        {error && <p className="error-message">{error}</p>}
      </div>
    </div>
  );
}
