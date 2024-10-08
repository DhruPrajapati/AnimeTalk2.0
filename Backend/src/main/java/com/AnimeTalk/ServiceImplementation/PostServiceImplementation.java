package com.AnimeTalk.ServiceImplementation;

import com.AnimeTalk.Exception.PostException;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.Repository.PostRepository;
import com.AnimeTalk.Repository.UserRepository;
import com.AnimeTalk.Service.PostService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Post;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public Post createNewPost(Post post, Integer userId) throws PostException, UserException {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(user);
        postRepository.save(newPost);
        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId() != user.getId())
            throw new PostException("you can't delete another user's post");

        postRepository.delete(post);
        return "Post delete Successfully";
    }


    @Override
    public List<Post> findPostByUserId(Integer userId) throws PostException {
      return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws PostException{
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty())
            throw new PostException("Post not found with Id"+postId);

        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post))
            user.getSavedPost().remove(post);
        else
            user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user))
            post.getLiked().remove(user);
        else
            post.getLiked().add(user);

        postRepository.save(post);
        return post;
    }
}
