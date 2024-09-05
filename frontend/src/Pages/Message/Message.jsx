import { Avatar, Grid, IconButton } from "@mui/material";
import AddIcCallIcon from "@mui/icons-material/AddIcCall";
import VideoCallIcon from "@mui/icons-material/VideoCall";
import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import WestIcon from "@mui/icons-material/West";
import React from "react";
import SearchUser from "../../Components/SearchUser/SearchUser";
import UserChatCard from "./UserChatCard";

const Message = () => {
  const handleSelectImage = () => {
    console.log("handle image");
  };
  return (
    <div>
      <Grid container className="h-screen overflow-y-hidden">
        <Grid className="px-5" item xs={3}>
          <div className="flex h-full justify-between space-x-2">
            <div className="w-full">
              <div className="flex space-x-4 items-center py-5">
                <WestIcon />
                <h1 className="text-xl font-bold">Home</h1>
              </div>
              <div className="h-[83vh]">
                <div className="">
                  <SearchUser />
                </div>
                <div className="h-full space-y-4 mt-5 overflow-y-scroll hideScrollBar">
                  <UserChatCard />
                </div>
              </div>
            </div>
          </div>
        </Grid>
        <Grid className="h-full" item xs={9}>
          <div>
            <div className="flex justify-between items-center border-l p-5">
              <div className="flex items-center space-x-3">
                <Avatar src=" https://cache.desktopnexus.com/thumbseg/1965/1965047-bigthumbnail.jpg" />
                <p>vandana prajapti</p>
              </div>
              <div className="flex space-x-3">
                <IconButton>
                  <AddIcCallIcon />
                </IconButton>
                <IconButton>
                  <VideoCallIcon />
                </IconButton>
              </div>
            </div>
            <div className="hideScrollBar overflow-y-scroll h-[82vh] px-2 space-y-5 py-5 ">
              Message
            </div>
            <div className="sticky botton-0 border-l">
              <div className="py-5 flex items-center justify-center space-x-5">
                <input
                  type="text"
                  className="bg-transparent border border-[#3b40544] rounded-full w-[90%] py-3 px-5"
                  placeholder="Type Message...."
                />
                <div className="">
                  <input
                    className="hidden"
                    type="file"
                    accept="image/*"
                    onChange={handleSelectImage}
                    id="image-input"
                  />
                  <label htmlFor="image-input">
                    <AddPhotoAlternateIcon />
                  </label>
                </div>
              </div>
            </div>
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default Message;
