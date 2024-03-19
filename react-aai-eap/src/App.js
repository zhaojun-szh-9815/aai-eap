import Home from './components/Home';
import Test from './components/test'
import Record from './components/Record'
import Dynamic from './components/Record_Dynamic'
import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route,
} from "react-router-dom";

function App() {

  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact
          path="/"
          element={<Home />}
          />
          <Route exact
          path="/records"
          element={<Record />}
          />
          <Route exact
          path="/test"
          element={<Test />}
          />
          <Route exact
          path="/dynamic"
          element={<Dynamic />}
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
