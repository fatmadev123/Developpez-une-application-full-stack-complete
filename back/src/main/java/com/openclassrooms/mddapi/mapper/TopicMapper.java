package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.UserService;
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
@Mapper(componentModel = "spring", uses = {UserService.class}, imports = {Arrays.class, Collectors.class, Topic.class, User.class, Collections.class, Optional.class})
public abstract class TopicMapper implements EntityMapper<TopicDto, Topic> {
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "users", expression = "java(Optional.ofNullable(topicDto.getUserIds()).orElseGet(Collections::emptyList).stream().map(userId -> { User user = this.userService.getById(userId); if (user != null) { return user; } return null; }).collect(Collectors.toList()))"),
    })
    public abstract Topic toEntity(TopicDto topicDto);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "userIds", expression = "java(Optional.ofNullable(topic.getUsers()).orElseGet(Collections::emptyList).stream().map(u -> u.getId()).collect(Collectors.toList()))"),
    })
    public abstract TopicDto toDto(Topic topic);
}
