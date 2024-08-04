package com.AnimeTalk.Controller;

import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.Repository.UserRepository;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException{
        User user = userService.findUserById(id);
        return user;
    }


    @PutMapping("/api/users")
    public User updateUser(@RequestBody User user,@RequestHeader("Authorization") String jwt) throws UserException{
        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user,reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/api/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId2) throws UserException{
        User reqUser = userService.findUserByJwt(jwt);
        User user =  userService.followUser(reqUser.getId(),userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) throws UserException{
        List<User> users = userService.searchUser(query);
        return users;
    }

    @DeleteMapping("/api/users/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws UserException{
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty())
            throw new UserException(("User does not exist with id "+userId));

        userRepository.deleteById(userId);

        return "user deleted SuccessFully";
    }


    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
