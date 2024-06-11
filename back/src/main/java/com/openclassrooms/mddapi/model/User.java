package com.openclassrooms.mddapi.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NonNull
	    @Size(max = 50)
	    @Column(name = "user_name")
	    private String userName;

	    @NonNull
	    @Size(max = 50)
	    @Column(name = "email")
	    @Email
	    private String email;

	    @NonNull
	    @Size(max = 255)
	    @Column(name = "password")
	    private String password;

	    @CreationTimestamp
	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @UpdateTimestamp
	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "subscriptions",
	            joinColumns = @JoinColumn( name = "user_id" ),
	            inverseJoinColumns = @JoinColumn( name = "topic_id" ) )
	    private List<Topic> topics;
}
