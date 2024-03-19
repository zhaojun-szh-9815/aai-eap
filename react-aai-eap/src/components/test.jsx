import axios from "axios";
import React from "react";


const baseUrl = "http://192.168.0.238:8080/record/maintenance/"

const RenderObject = ({ obj }) => {
  return (
    <div>
      {Object.entries(obj).map(([key, value]) => {
        // Check if the value is an object and not an array or null
        if (value == null) {
          return (
            <p>
              <strong>{key}: N/A</strong>
            </p>
          )
        } else if (typeof value === 'object' && value !== null && !Array.isArray(value)) {
          // Recursively render nested objects
          return (
            <div key={key}>
              <strong>{key}:</strong>
              {/* <RenderObject obj={value} /> */}
              {value.name}
            </div>
          );
        } else {
          // Render the key-value pair
          return (
            <p key={key}>
              <strong>{key}:</strong> {value.toString()}
            </p>
          );
        }
      })}
    </div>
  );
};

// Main component to render the list of objects
const ObjectList = ({ data }) => {
  return (
    <div>
      {data.map((obj, index) => (
        <div key={index}>
          <RenderObject obj={obj} />
          <hr />
        </div>
      ))}
    </div>
  );
};


function App() {
  const [records, setRecords] = React.useState([])

  React.useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(baseUrl);
        console.log('Fetched data:', JSON.stringify(response.data));
        setRecords(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [])

  return (
      <ObjectList data={records} />
  );
}

export default App;