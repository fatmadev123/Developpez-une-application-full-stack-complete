package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

        private Long id;

        @NotNull
        @Size(max = 50)
        private String name;

        @NotNull
        @Size(max = 200)
        private String description;

        private List<Long> userIds;
}
