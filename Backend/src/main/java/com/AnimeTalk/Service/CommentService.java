package com.AnimeTalk.Service;

import com.AnimeTalk.Exception.CommentExpection;
import com.AnimeTalk.Exception.PostException;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws CommentExpection, PostException, UserException;

    public Comment likeComments(Integer commentID, Integer userId) throws CommentExpection, UserException;

    public Comment findCommentById(Integer commentId) throws CommentExpection;

}
