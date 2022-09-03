import Dashboard from "./components/dashboard/dashboard.jsx";
import {Route, Routes } from "react-router-dom";
import Login from "./components/login/login.jsx";
import Signup from "./components/signup/signup.jsx";


function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />}></Route>
      <Route path="/login" element={<Login />}></Route>
      <Route path="/signup" element={<Signup />}></Route>
      <Route path="/dashboard" element={<Dashboard />}></Route>
    </Routes>
  );
}

export default App;
