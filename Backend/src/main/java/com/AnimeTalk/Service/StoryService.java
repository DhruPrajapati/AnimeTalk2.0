package com.AnimeTalk.Service;

import com.AnimeTalk.models.Story;
import com.AnimeTalk.models.User;

import java.util.List;

public interface StoryService {
    public Story createStory (Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
