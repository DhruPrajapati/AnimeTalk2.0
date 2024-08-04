package com.AnimeTalk.Service;

import com.AnimeTalk.Exception.MessageException;
import com.AnimeTalk.models.Chat;
import com.AnimeTalk.models.Message;
import com.AnimeTalk.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatMessages(Integer chatId) throws Exception;

}
