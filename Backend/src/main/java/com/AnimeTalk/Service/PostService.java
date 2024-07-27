package com.AnimeTalk.Service;

import com.AnimeTalk.models.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer UserId) throws Exception;

    Post findPostById(Integer PostId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId) throws Exception;

    Post likePost(Integer postId,Integer userId) throws Exception;



}
