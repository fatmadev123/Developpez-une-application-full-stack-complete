package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.payload.request.CreateCommentRequest;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;
    
    @Autowired
    CommentMapper commentMapper;

    /**
     * Action to get all comments by post
     * @param postId: post id
     * @return ResponseEntity<List<CommentDto>: all the comments for the post
     */
    @GetMapping("api/post/{postId}/comment")
    public ResponseEntity<List<CommentDto>> getByPostId(@PathVariable("postId") String postId) {
        List<Comment> comments = this.commentService.getByPostId(Long.valueOf(postId));
        return ResponseEntity.ok().body(this.commentMapper.toDto(comments));
    }
}