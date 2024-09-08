import {
  Avatar,
  Backdrop,
  CircularProgress,
  Grid,
  IconButton,
} from "@mui/material";
import AddIcCallIcon from "@mui/icons-material/AddIcCall";
import VideoCallIcon from "@mui/icons-material/VideoCall";
import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import WestIcon from "@mui/icons-material/West";
import React, { useEffect, useRef, useState } from "react";
import SearchUser from "../../Components/SearchUser/SearchUser";
import UserChatCard from "./UserChatCard";
import ChatMessage from "./ChatMessage";
import { useDispatch, useSelector } from "react-redux";
import { createMessage, getAllChats } from "../../Redux/Actions/messageAction";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import uploadToCloudinary from "../../Utils/uploadToCloudinary";
import SockJS from "sockjs-client";
import { API_BASE_URL } from "../../Config/api";
import Stomp from "stompjs"; // Fixed typo here

const Message = () => {
  const dispatch = useDispatch();
  const { message, auth } = useSelector((store) => store);
  const [currentChat, setCurrentChat] = useState();
  const [messages, setMessages] = useState([]);
  const [selectedImage, setSelectedImage] = useState();
  const [loading, setLoading] = useState(false);
  const [stompClient, setStompClient] = useState(null);
  const chatContainerRef = useRef(null);

  const handleSelectImage = async (e) => {
    setLoading(true);
    console.log("handle image");
    const imgUrl = await uploadToCloudinary(e.target.files[0], "image");
    setSelectedImage(imgUrl);
    setLoading(false);
  };

  const handleCreateMessage = (value) => {
    const message = {
      chatId: currentChat?.id,
      content: value,
      image: selectedImage,
    };
    dispatch(createMessage({ message, sendMessageToServer }));
  };

  const onErr = (error) => {
    console.log("WebSocket error: ", error);
  };

  const sendMessageToServer = (newMessage) => {
    if (stompClient && newMessage) {
      stompClient.send(
        `/app/chat/${currentChat?.id}`,
        {},
        JSON.stringify(newMessage)
      );
    }
  };

  const onMessageReceived = (payload) => {
    console.log("payload", payload);
    const receivedMessage = JSON.parse(payload.body);
    console.log("Message received from WebSocket: ", receivedMessage);

    setMessages((prevMessages) => [...prevMessages, receivedMessage]); // Correct state update
  };

  useEffect(() => {
    dispatch(getAllChats());
  }, [dispatch]);

  useEffect(() => {
    if (message.message) {
      setMessages((prevMessages) => [...prevMessages, message.message]); // Correct state update
    }
  }, [message.message]);

  useEffect(() => {
    const sock = new SockJS(`${API_BASE_URL}/ws`);
    const stomp = Stomp.over(sock); // Fixed typo here
    stomp.connect(
      {},
      () => {
        console.log("WebSocket Connected...");
        setStompClient(stomp);
      },
      onErr
    );

    return () => {
      if (stompClient) {
        stompClient.disconnect(() => {
          console.log("WebSocket Disconnected");
        });
      }
    };
  }, []);

  useEffect(() => {
    if (stompClient && auth.user && currentChat) {
      const subscription = stompClient.subscribe(
        `/user/${currentChat.id}/private`,
        onMessageReceived
      );

      return () => {
        subscription.unsubscribe();
      };
    }
  }, [stompClient, auth.user, currentChat]);

  useEffect(() => {
    if (chatContainerRef.current)
      chatContainerRef.current.scrollTop =
        chatContainerRef.current.scrollHeight;
  }, [message]);

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
                <SearchUser />
                <div
                  ref={chatContainerRef}
                  className="h-full space-y-4 mt-5 overflow-y-scroll hideScrollBar">
                  {message.chats.map((item) => (
                    <div
                      key={item.id}
                      onClick={() => {
                        setCurrentChat(item);
                        console.log("Selected chat: ", item);
                        setMessages(item.messages);
                      }}>
                      <UserChatCard chat={item} />
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </Grid>
        <Grid className="h-full" item xs={9}>
          {currentChat ? (
            <div>
              <div className="flex justify-between items-center border-l p-5">
                <div className="flex items-center space-x-3">
                  <Avatar src="https://cache.desktopnexus.com/thumbseg/1965/1965047-bigthumbnail.jpg" />
                  <p>
                    {auth.user.id === currentChat.users[0]?.id
                      ? `${currentChat.users[1].firstName} ${currentChat.users[1].lastName}`
                      : `${currentChat.users[0].firstName} ${currentChat.users[0].lastName}`}
                  </p>
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
                {messages.map((item) => (
                  <ChatMessage key={item.id} item={item} />
                ))}
              </div>
              <div className="sticky bottom-0 border-l">
                <div className="py-5 flex items-center justify-center space-x-5">
                  {selectedImage && (
                    <img
                      src={selectedImage}
                      className="w-[5rem] h-[5rem] object-cover px-2"
                      alt=""
                    />
                  )}
                  <input
                    type="text"
                    className="bg-transparent border border-[#3b40544] rounded-full w-[90%] py-3 px-5"
                    placeholder="Type Message...."
                    onKeyPress={(e) => {
                      if (e.key === "Enter" && e.target.value) {
                        handleCreateMessage(e.target.value);
                        setSelectedImage("");
                      }
                    }}
                  />
                  <div>
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
          ) : (
            <div className="h-full space-y-5 flex flex-col justify-center items-center">
              <ChatBubbleOutlineIcon sx={{ fontSize: "15rem" }} />
              <p className="text-xl font-semibold"> No Chat Selected</p>
            </div>
          )}
        </Grid>
      </Grid>
      <Backdrop
        sx={(theme) => ({ color: "#fff", zIndex: theme.zIndex.drawer + 1 })}
        open={loading}>
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  );
};

export default Message;
