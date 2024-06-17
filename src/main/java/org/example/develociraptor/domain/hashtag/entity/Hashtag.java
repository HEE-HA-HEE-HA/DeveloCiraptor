package org.example.develociraptor.domain.hashtag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hashtag_id")
	private Long id;

	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String name;

}
