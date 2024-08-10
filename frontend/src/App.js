import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import Authentication from "./Pages/Authentication/Authentication";
import HomePage from "./Pages/Home/HomePage";
import Message from "./Pages/Message/Message";

function App() {
  return (
    <div className="">
      <Router>
        <Routes>
          <Route path="/*" element={<HomePage />} />
          <Route path="/message" element={<Message />} />
          <Route path="/*" element={<Authentication />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
