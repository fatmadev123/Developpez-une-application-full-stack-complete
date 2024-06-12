package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {TopicService.class, UserService.class}, imports = {Arrays.class, Collectors.class, Post.class, User.class, Topic.class, Collections.class, Optional.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    TopicService topicService;
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "date", target = "date"),
            @Mapping(target = "topic", expression = "java(this.topicService.getById(postDto.getTopicId()))"),
            @Mapping(target = "user", expression = "java(this.userService.getById(postDto.getUserId()))"),
    })
    public abstract Post toEntity(PostDto postDto);


    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "post.topic.id", target = "topicId"),
            @Mapping(source = "post.topic.name", target = "topicName"),
            @Mapping(source = "post.user.id", target = "userId"),
            @Mapping(source = "post.user.userName", target = "userName"),
    })
    public abstract PostDto toDto(Post post);
}