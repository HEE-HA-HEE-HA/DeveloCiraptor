package org.example.develociraptor.domain.feed.entity.type;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.develociraptor.domain.feed.entity.Feed;

@Entity
@DiscriminatorValue("Reference")
public class Reference extends Feed {

}
