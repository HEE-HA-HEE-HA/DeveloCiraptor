package org.example.develociraptor.domain.feed.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import org.example.develociraptor.domain.feed.entity.Feed;

@Entity
@DiscriminatorValue("Project")
public class Project extends Feed {

	@Size(max = 50)
	@Column(length = 50)
	private String title;

	@Column
	private boolean status;
}
