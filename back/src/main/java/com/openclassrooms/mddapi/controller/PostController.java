package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;
    
    @Autowired
    private PostMapper postMapper;

    /**
     * Action to get feed posts
     * @return ResponseEntity<List<PostDto>: all the feed posts
     */
    @GetMapping()
    public ResponseEntity<List<PostDto>> getFeedPosts() {
        List<Post> posts = this.postService.getFeedPosts();
        return ResponseEntity.ok().body(this.postMapper.toDto(posts));
    }
    /**
     * Action to get a post by id
     * @param id: post id
     * @return ResponseEntity<PostDto>: the post
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") String id) {
        try {
            Post post = this.postService.getById(Long.valueOf(id));

            if (post == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.postMapper.toDto(post));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
