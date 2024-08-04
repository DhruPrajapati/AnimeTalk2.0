package com.AnimeTalk.ServiceImplementation;

import com.AnimeTalk.Config.JwtProvider;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.Repository.UserRepository;
import com.AnimeTalk.Service.UserService;
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
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not Exist with UserId "+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserId);

        User user2 =  findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId)  throws UserException{
        Optional<User> userExist = userRepository.findById(userId);

        if(userExist.isEmpty())
            throw  new UserException("User does not exist with id "+userId);


        User oldUser = userExist.get();

        if(user.getFirstName()!= null)
            oldUser.setFirstName(user.getFirstName());

        if(user.getLastName()!= null)
            oldUser.setLastName(user.getLastName());

        if(user.getEmail()!= null)
            oldUser.setEmail(user.getEmail());

        if(user.getGender()!= null)
            oldUser.setGender(user.getGender());

        User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }


    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);
        return user;
    }
}
