import React, { useState, useEffect } from 'react';
import { getAllItems, searchItems } from '../services/api';
import { useNavigate } from 'react-router-dom';
import './ItemList.css';

export default function ItemList() {
  const navigate = useNavigate();
  const [items, setItems] = useState([]);
  const [searchKeyword, setSearchKeyword] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      setLoading(true);
      const response = await getAllItems();
      setItems(response.data);
      setError('');
    } catch (err) {
      setError('Failed to load items');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!searchKeyword.trim()) {
      fetchItems();
      return;
    }

    try {
      setLoading(true);
      const response = await searchItems(searchKeyword);
      setItems(response.data);
      setError('');
    } catch (err) {
      setError('Search failed');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const viewDetails = (id) => {
    navigate(`/items/${id}`);
  };

  return (
    <div className="itemlist-container">
      <h2>Lost & Found Items</h2>

      <form onSubmit={handleSearch} className="search-form">
        <input
          type="text"
          placeholder="Search items..."
          value={searchKeyword}
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <button type="submit" className="btn-search">Search</button>
        <button type="button" onClick={fetchItems} className="btn-reset">Show All</button>
      </form>

      {loading && <p className="loading">Loading items...</p>}
      {error && <p className="error-message">{error}</p>}

      <div className="items-grid">
        {items.length === 0 && !loading && (
          <p className="no-items">No items found</p>
        )}

        {items.map((item) => (
          <div key={item.id} className="item-card">
            <div className="item-header">
              <h3>{item.title}</h3>
              <span className={`status-badge ${item.status.toLowerCase()}`}>
                {item.status}
              </span>
            </div>
            <p className="item-description">{item.description}</p>
            <div className="item-details">
              <p><strong>Category:</strong> {item.category}</p>
              <p><strong>Location:</strong> {item.location}</p>
              <p><strong>Contact:</strong> {item.contactInfo}</p>
            </div>
            <button onClick={() => viewDetails(item.id)} className="btn-view">
              View Details
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
