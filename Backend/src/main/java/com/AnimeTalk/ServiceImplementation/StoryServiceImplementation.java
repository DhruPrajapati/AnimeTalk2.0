package com.AnimeTalk.ServiceImplementation;

import com.AnimeTalk.Repository.StoryRepository;
import com.AnimeTalk.Service.StoryService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Story;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation  implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story createdStory = new Story();
        createdStory.setCaptions(story.getCaptions());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setCreatedAt(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        return storyRepository.findByUserId(userId);
    }
}
