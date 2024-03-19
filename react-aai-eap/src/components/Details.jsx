import React, { useState, useEffect, useRef } from 'react';

const Detail = ({ isOpen, onClose, onSave, onDelete, isAdd, data }) => {
  const [editedData, setEditedData] = useState(data);
  const [types, setTypes] = useState([])
  const detailRef = useRef(null);

  useEffect(() => {
    setEditedData(data)
    setTypes(Object.values(data).map((item) => {
        return getInputType(item)
    }))
    if (isAdd) {
        setEditedData(Object.values(data).map((item) => {
            return ""
        }))
    }
  }, [data, isAdd]);

  useEffect(() => {
    // Step 3: Define the function to detect outside clicks
    const handleClickOutside = (event) => {
      if (detailRef.current && !detailRef.current.contains(event.target)) {
        onClose(); // Call onClose prop if click is outside
      }
    };

    // Step 2: Add event listener when component mounts
    document.addEventListener('mousedown', handleClickOutside);

    // Remove event listener on cleanup
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [onClose]); // Ensure effect runs again if onClose changes

  if (!isOpen) {
    return null;
  }

  // Detect if the string is a valid date
  const isDateString = (value) => {
    if (!value || typeof value !== 'string') return false;
    const date = new Date(value);
    return !isNaN(date.getTime());
  };

  // Detect if the string represents a number
  const isNumberString = (value) => {
    return !isNaN(value) && !isNaN(parseFloat(value));
  };

  const getInputType = (value) => {
    if (isNumberString(value)) return 'number';
    if (isDateString(value)) return 'date';
    return 'text';
  };

  const displayValue = (value) => {
    if (typeof value === 'object' && value !== null) return value.name; // Assuming nested objects have a 'name' property
    return value;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditedData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
    console.log("changed:", name, " -> ", value)
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(editedData);
  };

  const handleDelete = (e) => {
    e.preventDefault();
    onDelete(editedData);
  };

  if (data === null) return <div>Loading...</div>

  return (
    <div ref={detailRef} style={{ 
        position: 'fixed',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        backgroundColor: 'white',
        padding: '20px',
        zIndex: 1000,
        maxHeight: '70vh',
        overflowY: 'auto',
        boxSizing: 'border-box'
        }}>
      <form onSubmit={handleSubmit}>
        {Object.entries(data).map(([key, value], index) => {
            if (index === 0) return <div key={key}></div>
            return <div key={key} style={{ textAlign: 'left', marginBottom: '10px' }}>
                <label>
                <span style={{ minWidth: '50%', display: 'inline-block' }}>{key}:</span>
                <input
                    type={types[index]}
                    name={key}
                    value={displayValue(editedData[key]) || ''}
                    onChange={handleChange}
                    style={{ textAlign: 'left' }}
                />
                </label>
            </div>
        })}
        <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '20px' }}>
          <button type="submit" style={{ flex: 1 }}>Save</button>
          <button type="button" onClick={onClose} style={{ flex: 1 }}>Cancel</button>
          <button type="button" onClick={handleDelete} style={{ flex: 1 }}>Delete</button>
        </div>
      </form>
    </div>
  );
};

export default Detail;
