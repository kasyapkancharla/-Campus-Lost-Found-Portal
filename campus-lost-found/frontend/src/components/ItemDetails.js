import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getItemById, deleteItem } from '../services/api';
import './ItemDetails.css';

export default function ItemDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [item, setItem] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchItemDetails();
  }, [id]);

  const fetchItemDetails = async () => {
    try {
      setLoading(true);
      const response = await getItemById(id);
      setItem(response.data);
      setError('');
    } catch (err) {
      setError('Failed to load item details');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete this item?')) {
      try {
        await deleteItem(id);
        alert('Item deleted successfully');
        navigate('/items');
      } catch (err) {
        alert('Failed to delete item');
        console.error(err);
      }
    }
  };

  if (loading) return <p className="loading">Loading...</p>;
  if (error) return <p className="error-message">{error}</p>;
  if (!item) return <p className="error-message">Item not found</p>;

  return (
    <div className="itemdetails-container">
      <div className="itemdetails-card">
        <div className="item-header">
          <h2>{item.title}</h2>
          <span className={`status-badge ${item.status.toLowerCase()}`}>
            {item.status}
          </span>
        </div>

        <div className="item-info">
          <div className="info-section">
            <h3>Description</h3>
            <p>{item.description}</p>
          </div>

          <div className="info-section">
            <h3>Details</h3>
            <div className="details-grid">
              <div className="detail-item">
                <strong>Category:</strong>
                <span>{item.category}</span>
              </div>
              <div className="detail-item">
                <strong>Location:</strong>
                <span>{item.location}</span>
              </div>
              <div className="detail-item">
                <strong>Date & Time:</strong>
                <span>{new Date(item.dateTime).toLocaleString()}</span>
              </div>
              <div className="detail-item">
                <strong>Contact Info:</strong>
                <span>{item.contactInfo}</span>
              </div>
              <div className="detail-item">
                <strong>Posted By:</strong>
                <span>{item.user?.name || 'Unknown'}</span>
              </div>
              <div className="detail-item">
                <strong>Posted On:</strong>
                <span>{new Date(item.createdAt).toLocaleDateString()}</span>
              </div>
            </div>
          </div>
        </div>

        <div className="action-buttons">
          <button onClick={() => navigate('/items')} className="btn-back">
            Back to List
          </button>
          <button onClick={handleDelete} className="btn-delete">
            Delete Item
          </button>
        </div>
      </div>
    </div>
  );
}
