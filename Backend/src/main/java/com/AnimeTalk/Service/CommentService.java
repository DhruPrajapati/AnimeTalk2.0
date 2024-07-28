package com.AnimeTalk.Service;

import com.AnimeTalk.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment likeComments(Integer commentID, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;

}
