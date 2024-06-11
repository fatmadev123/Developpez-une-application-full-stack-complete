package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Service to get all topics
     * @return List<Topic>: all topics
     */
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    /**
     * get all topics followed by the connected user
     * @return List<Topic>: all topics followed by the connected user
     */
    public List<Topic> getFollowedTopics() {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        return user.get().getTopics();
    }

    /**
     * get the topic by id
     * @param id: topic id
     * @return Topic: the topic
     */
    public Topic getById(Long id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    /**
     * make the user follow the topic
     * @param id: subject id
     */
    public void follow(Long id) {
        Topic topic = this.topicRepository.findById(id).orElse(null);
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (topic == null || user.isEmpty()) {
            throw new NotFoundException();
        }

        boolean isFollowing;
        List<Long> userIds = new ArrayList<>();
        for (User u : topic.getUsers()) {
            userIds.add(u.getId());
        }
        isFollowing = userIds.contains(user.get().getId());

        if(isFollowing) {
            throw new BadRequestException();
        }
        topic.getUsers().add(user.get());
        this.topicRepository.save(topic);
    }

    /**
     *make the user unfollow the topic
     * @param id: subject id
     */
    public void unfollow(Long id) {
        Topic topic = this.topicRepository.findById(id).orElse(null);
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (topic == null || user.isEmpty()) {
            throw new NotFoundException();
        }
        boolean isFollowing;
        List<Long> userIds = new ArrayList<>();
        for (User u : topic.getUsers()) {
            userIds.add(u.getId());
        }
        isFollowing = userIds.contains(user.get().getId());
        if(!isFollowing) {
            throw new BadRequestException();
        }
        List<User> updatedUsers = new ArrayList<>();
        for (User u: topic.getUsers()) {
            if (!Objects.equals(u.getId(), user.get().getId())) {
                updatedUsers.add(u);
            }
        }
        topic.setUsers(updatedUsers);
        this.topicRepository.save(topic);
    }
}
