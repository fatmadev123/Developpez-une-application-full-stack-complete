package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String title;

    private LocalDateTime date;

    @NotNull
    @Size(max = 5000)
    private String content;

    private Long userId;

    private String userName;

    private Long topicId;

    private String topicName;

}
