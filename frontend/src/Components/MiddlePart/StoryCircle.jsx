import React from "react";
import { Avatar } from "@mui/material";
const StoryCircle = () => {
  return (
    <div>
      <div className="flex flex-col items-center mr-4 cursor-pointer">
        <Avatar
          sx={{ width: "5rem", height: "5rem" }}
          src="https://png.pngtree.com/png-vector/20191101/ourmid/pngtree-cartoon-color-simple-male-avatar-png-image_1934459.jpg"
        />

        <p>jaiPra...</p>
      </div>
    </div>
  );
};

export default StoryCircle;
