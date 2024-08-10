import React from "react";
import Sidebar from "../../Components/SideBar/Sidebar";
import { Grid } from "@mui/material";
import { Route, Router, Routes, useLocation } from "react-router-dom";
import MiddlePart from "../../Components/MiddlePart/MiddlePart";
import CreateReelsForm from "../../Components/Reels/CreateReelsForm";
import Reels from "../../Components/Reels/Reels";
import Profile from "../Profile/Profile";
import HomeRight from "../../Components/HomeRight/HomeRight";

const HomePage = () => {
  const location = useLocation();
  return (
    <div className="px-20">
      <Grid container spacing={0}>
        <Grid item xs={0} lg={3}>
          <div className="sticky top-0">
            <Sidebar />
          </div>
        </Grid>
        <Grid
          item
          className="px-5 flex justify-center"
          xs={12}
          lg={location.pathname === "/" ? 6 : 9}>
          <Routes>
            <Route path="/" element={<MiddlePart />} />
            <Route path="/reels" element={<Reels />} />
            <Route path="/create-reels" element={<CreateReelsForm />} />
            <Route path="/profile/:id" element={<Profile />} />
          </Routes>
        </Grid>
        <Grid item lg={3} className="relative">
            <div className="sticky top-0 w-full">
                <HomeRight/>
            </div>

        </Grid>
      </Grid>
    </div>
  );
};

export default HomePage;
