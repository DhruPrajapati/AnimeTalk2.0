package com.AnimeTalk.ServiceImplementation;

import com.AnimeTalk.Exception.ReelException;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.Repository.ReelsRepository;
import com.AnimeTalk.Service.ReelsService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Reels;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createReel = new Reels();
        createReel.setTitle(reel.getTitle());
        createReel.setVideo(reel.getVideo());
        createReel.setUser(user);

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReels(Integer userId) throws ReelException, UserException {
        userService.findUserById(userId);

        return reelsRepository.findByUserId(userId);
    }
}
