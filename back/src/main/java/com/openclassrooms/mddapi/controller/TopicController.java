package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * Action to get all topics
     * @return ResponseEntity<List<TopicsDto>>: all topics
     */
    @GetMapping()
    public ResponseEntity<List<TopicDto>> getAll() {
        List<Topic> topics = this.topicService.getAll();
        return ResponseEntity.ok().body(this.topicMapper.toDto(topics));
    }

    /**
     * Action to get all topics followed by the user connected
     * @return ResponseEntity<List<TopicDto>>: all topics followed by the user
     */
    @GetMapping("/user")
    public ResponseEntity<List<TopicDto>> getFollowedTopics() {
        List<Topic> topics = this.topicService.getFollowedTopics();
        return ResponseEntity.ok().body(this.topicMapper.toDto(topics));
    }
}