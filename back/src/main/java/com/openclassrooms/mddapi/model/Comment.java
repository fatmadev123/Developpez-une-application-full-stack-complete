package com.openclassrooms.mddapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 2000)
    @Column(name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "date", updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Comment(String comment, Post post) {
        this.comment = comment;
        this.post = post;
    }
}