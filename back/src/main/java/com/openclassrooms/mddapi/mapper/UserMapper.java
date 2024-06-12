package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.TopicService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", uses = {TopicService.class}, imports = {Arrays.class, Collectors.class, Topic.class, User.class, Collections.class, Optional.class})
public abstract class UserMapper implements EntityMapper<UserDto, User> {
    @Autowired
    TopicService topicService;

    @Mappings({
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(target = "topics", expression = "java(Optional.ofNullable(userDto.getTopicIds()).orElseGet(Collections::emptyList).stream().map(topicId -> { Topic topic = this.topicService.getById(topicId); if (topic != null) { return topic; } return null; }).collect(Collectors.toList()))"),
    })
    public abstract User toEntity(UserDto userDto);


    @Mappings({
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt"),
            @Mapping(target = "topicIds", expression = "java(Optional.ofNullable(user.getTopics()).orElseGet(Collections::emptyList).stream().map(u -> u.getId()).collect(Collectors.toList()))"),
    })
    public abstract UserDto toDto(User user);
}
