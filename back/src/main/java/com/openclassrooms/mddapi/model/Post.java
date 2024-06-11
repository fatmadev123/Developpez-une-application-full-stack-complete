package com.openclassrooms.mddapi.model;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"id"})
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NonNull
	    @Size(max = 50)
	    @Column(name = "title")
	    private String title;

	    @CreationTimestamp
	    @Column(name = "date", updatable = false)
	    private LocalDateTime date;

	    @NotNull
	    @Size(max = 5000)
	    @Column(name = "content")
	    private String content;

	    @ManyToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "topic_id", referencedColumnName = "id")
	    private Topic topic;

	    public Post(String title, String text, Topic topic) {
	        this.title = title;
	        this.content = text;
	        this.topic = topic;
	    }
}
