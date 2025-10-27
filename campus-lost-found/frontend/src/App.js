import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Register from './components/Register';
import Login from './components/Login';
import ItemList from './components/ItemList';
import AddItem from './components/AddItem';
import ItemDetails from './components/ItemDetails';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/items" element={<ItemList />} />
          <Route path="/add-item" element={<AddItem />} />
          <Route path="/items/:id" element={<ItemDetails />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
