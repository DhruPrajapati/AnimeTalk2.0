import { Route, Routes } from "react-router-dom";
import "./App.css";
import Authentication from "./Pages/Authentication/Authentication";
import HomePage from "./Pages/Home/HomePage";
import Message from "./Pages/Message/Message";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getProfileAction } from "./Redux/Actions/authAction";

function App() {

  
  const { auth } = useSelector((store) => store);
   const jwt = localStorage.getItem("jwt");
    const dispatch = useDispatch();

   useEffect(() => {
     dispatch(getProfileAction(jwt));
   }, [jwt]);


  return (
    <div className="">
      <Routes>
        <Route path="/*" element={auth.user?<HomePage />:<Authentication/>} />
        <Route path="/message" element={<Message />} />
        <Route path="/*" element={<Authentication />} />
      </Routes>
    </div>
  );
}

export default App;
