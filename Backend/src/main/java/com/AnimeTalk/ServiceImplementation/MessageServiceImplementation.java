package com.AnimeTalk.ServiceImplementation;

import com.AnimeTalk.Exception.MessageException;
import com.AnimeTalk.Repository.ChatRepository;
import com.AnimeTalk.Repository.MessageRepository;
import com.AnimeTalk.Service.ChatService;
import com.AnimeTalk.Service.MessageService;
import com.AnimeTalk.models.Chat;
import com.AnimeTalk.models.Message;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation  implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    Message message = new Message();
    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        Message message = new Message();

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
