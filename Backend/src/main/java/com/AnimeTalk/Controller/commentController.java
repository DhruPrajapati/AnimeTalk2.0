package com.AnimeTalk.Controller;

import com.AnimeTalk.Service.CommentService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.Comment;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class commentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comment/post/{postId}")
     public Comment createComment(@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
         User reqUser = userService.findUserByJwt(jwt);

         Comment createdComment = commentService.createComment(comment,postId,reqUser.getId());

        return createdComment;
     }

    @PutMapping("/api/comment/like/{commentId}")
    public Comment likeComment(@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,@PathVariable Integer commentId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.likeComments(commentId,reqUser.getId());

        return likedComment;
    }
}
