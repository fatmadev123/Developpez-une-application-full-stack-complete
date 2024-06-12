package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreatePostRequest {
    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Size(max = 5000)
    private String content;

    private Long topicId;

}