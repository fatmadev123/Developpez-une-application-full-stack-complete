package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost (Optional<Post> post);

}
