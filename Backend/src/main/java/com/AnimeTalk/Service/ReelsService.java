package com.AnimeTalk.Service;

import com.AnimeTalk.models.Reels;
import com.AnimeTalk.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public  List<Reels> findUsersReels(Integer userId) throws Exception;
}
