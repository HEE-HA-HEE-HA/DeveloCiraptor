package org.example.develociraptor.domain.techstack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tech_stacks")
public class TechStack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tech_stack_id")
	private Long id;

	@Size(max = 50)
	@Column(nullable = false, length = 50)
	private String name;
}
