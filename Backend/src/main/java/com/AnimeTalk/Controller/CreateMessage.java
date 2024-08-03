package com.AnimeTalk.Controller;

import com.AnimeTalk.Service.MessageService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Message;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt); // user is not coming in response need to check later
        Message message = messageService.createMessage(user,chatId,req);
        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage( @PathVariable Integer chatId) throws Exception {

        List<Message> messages = messageService.findChatMessages(chatId);
        return messages;
    }
}
