package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PostService.class, UserService.class}, imports = {Arrays.class, Collectors.class, Post.class, User.class, Comment.class, Collections.class, Optional.class})
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment> {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(source = "comment", target = "comment"),
            @Mapping(source = "date", target = "date"),
            @Mapping(target = "post", expression = "java(this.postService.getById(commentDto.getPostId()))"),
            @Mapping(target = "user", expression = "java(this.userService.getById(commentDto.getUserId()))"),
    })
    public abstract Comment toEntity(CommentDto commentDto);


    @Mappings({
            @Mapping(source = "comment", target = "comment"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "comment.post.id", target = "postId"),
            @Mapping(source = "comment.user.id", target = "userId"),
            @Mapping(source = "comment.user.userName", target = "userName"),
    })
    public abstract CommentDto toDto(Comment comment);
}