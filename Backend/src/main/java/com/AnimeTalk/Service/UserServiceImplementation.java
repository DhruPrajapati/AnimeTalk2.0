package com.AnimeTalk.Service;

import com.AnimeTalk.Repository.UserRepository;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("user not Exist with UserId "+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);

        User user2 =  findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, Integer userId)  throws Exception{
        Optional<User> userExist = userRepository.findById(userId);

        if(userExist.isEmpty())
            throw  new Exception("User does not exist with id "+userId);


        User oldUser = userExist.get();

        if(user.getFirstName()!= null)
            oldUser.setFirstName(user.getFirstName());

        if(user.getLastName()!= null)
            oldUser.setLastName(user.getLastName());

        if(user.getEmail()!= null)
            oldUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }


    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
