import LogIn from "./Compoment/LogIn";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Register from "./Compoment/Register";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LogIn/>}/>
        <Route path="/register" element={<Register/>}/>
      </Routes>
     
    </Router>
  );
}

export default App;
