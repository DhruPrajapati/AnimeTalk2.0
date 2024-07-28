package com.AnimeTalk.Service;


import com.AnimeTalk.models.Chat;
import com.AnimeTalk.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User requser,User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat( Integer userId);
}
