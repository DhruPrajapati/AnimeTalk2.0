import { Avatar, Button, CardHeader, IconButton } from '@mui/material';
import React from 'react'
import MoreVertIcon from "@mui/icons-material/MoreVert";

const PopularUserCard = () => {
  return (
    <div>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: "red[500]" }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <Button size='small'>Follow</Button>
        }
        title="Dhru Prajapti "
        subheader="@dhruprajapati "
      />
    </div>
  );
}

export default PopularUserCard
