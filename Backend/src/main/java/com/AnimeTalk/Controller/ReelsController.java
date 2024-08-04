package com.AnimeTalk.Controller;

import com.AnimeTalk.Exception.ReelException;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.Service.ReelsService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Reels;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reels,reqUser);
        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){
        List<Reels>  reels= reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws ReelException, UserException {
        List<Reels>  reels= reelsService.findUsersReels(userId);
        return reels;
    }
}
