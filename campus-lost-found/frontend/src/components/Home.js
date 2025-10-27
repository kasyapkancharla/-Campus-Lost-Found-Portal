import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

export default function Home() {
  return (
    <div className="home-container">
      <div className="hero-section">
        <h1>Campus Lost & Found Portal</h1>
        <p>Find what you've lost, return what you've found</p>
        
        <div className="cta-buttons">
          <Link to="/items" className="btn-cta primary">
            Browse Items
          </Link>
          <Link to="/add-item" className="btn-cta secondary">
            Report Item
          </Link>
        </div>
      </div>

      <div className="features-section">
        <div className="feature-card">
          <h3>📢 Report Lost Items</h3>
          <p>Quickly report items you've lost on campus</p>
        </div>
        
        <div className="feature-card">
          <h3>🔍 Search Found Items</h3>
          <p>Browse through items found by others</p>
        </div>
        
        <div className="feature-card">
          <h3>🤝 Connect & Claim</h3>
          <p>Contact finders and reclaim your belongings</p>
        </div>
      </div>
    </div>
  );
}
