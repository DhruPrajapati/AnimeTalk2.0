import { Avatar, Card, CardHeader } from "@mui/material";
import React from "react";

const SearchUser = () => {
  const handleSearchUser = () => {
    console.log("handle search ");
  };
  const handleClick = (id) => {
    console.log(id);
  };

  return (
    <div>
      <div className="py-5 relative">
        <input
          className="bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full"
          placeholder="search user..."
          onChange={handleSearchUser}
          type="text"
        />
      </div>
      {false && (
        <Card>
          <CardHeader
            onClick={() => {
              handleClick();
            }}
            avatar={
              <Avatar src="https://cache.desktopnexus.com/thumbseg/1965/1965047-bigthumbnail.jpg" />
            }
            title="Dhru Prajapati"
            subheader={"codewithDhru"}
          />
        </Card>
      )}
    </div>
  );
};

export default SearchUser;
