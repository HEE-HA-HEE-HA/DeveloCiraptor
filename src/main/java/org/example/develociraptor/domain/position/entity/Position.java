package org.example.develociraptor.domain.position.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "positions")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "position_id")
	private Short id;

	@Column(nullable = false)
	private String name;

}
