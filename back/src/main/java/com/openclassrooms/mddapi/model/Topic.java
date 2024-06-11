package com.openclassrooms.mddapi.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "topics")
@EntityListeners(AuditingEntityListener.class)
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Topic {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotNull
	    @Size(max = 50)
	    @Column(name = "name")
	    private String name;

	    @NotNull
	    @Size(max = 200)
	    @Column(name = "description")
	    private String description;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "subscriptions",
	            joinColumns = @JoinColumn( name = "topic_id" ),
	            inverseJoinColumns = @JoinColumn( name = "user_id" ) )
	    private List<User> users;
}
