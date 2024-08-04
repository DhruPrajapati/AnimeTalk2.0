package com.AnimeTalk.ServiceImplementation;

import com.AnimeTalk.Exception.CommentExpection;
import com.AnimeTalk.Exception.PostException;
import com.AnimeTalk.Exception.UserException;
import com.AnimeTalk.Repository.CommentRepository;
import com.AnimeTalk.Repository.PostRepository;
import com.AnimeTalk.Service.CommentService;
import com.AnimeTalk.Service.PostService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Comment;
import com.AnimeTalk.models.Post;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementaton implements CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws CommentExpection, PostException, UserException {

        User user = userService.findUserById(userId);

        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment =commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment likeComments(Integer commentId, Integer userId) throws CommentExpection, UserException {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user))
            comment.getLiked().add(user);
        else
            comment.getLiked().remove(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws CommentExpection{
        Optional<Comment> opt = commentRepository.findById(commentId);

        if(opt.isEmpty())
            throw new CommentExpection("Comment does not exits");

        return opt.get();
    }
}
