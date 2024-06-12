package com.openclassrooms.mddapi.payload.request;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateCommentRequest {
    @NotNull
    @Size(max = 2000)
    private String comment;
}