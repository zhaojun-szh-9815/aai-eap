// Filename - Home.jsx
import React from "react";
// Importing Link from react-router-dom to 
// navigate to different end points.
import { Link } from "react-router-dom";
 
const Home = () => {
    return (
        <div>
            <h1>Home Page</h1>
            <br />
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/test">test</Link>
                </li>
                <li>
                    <Link to="/records">record</Link>
                </li>
                <li>
                    <Link to="/dynamic">record dynamic</Link>
                </li>
            </ul>
        </div>
    );
};
 
export default Home;