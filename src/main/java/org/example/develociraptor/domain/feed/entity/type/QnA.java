package org.example.develociraptor.domain.feed.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import org.example.develociraptor.domain.feed.entity.Feed;

@Entity
@DiscriminatorValue("QnA")
public class QnA extends Feed {

	@Size(max = 50)
	@Column(length = 50)
	private String title;

}
