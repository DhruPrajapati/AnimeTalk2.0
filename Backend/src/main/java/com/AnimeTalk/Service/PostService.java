package com.AnimeTalk.Service;

import com.AnimeTalk.Exception.PostException;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.models.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Integer userId) throws PostException, UserException;

    String deletePost(Integer postId, Integer userId) throws PostException, UserException;

    List<Post> findPostByUserId(Integer UserId) throws PostException;

    Post findPostById(Integer PostId) throws PostException;

    List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId) throws PostException, UserException;

    Post likePost(Integer postId,Integer userId) throws PostException, UserException;



}
