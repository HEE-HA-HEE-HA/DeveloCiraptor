package org.example.develociraptor.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.position.entity.Position;
import org.example.develociraptor.global.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE User SET is_deleted = true where user_id = ?")
@SQLRestriction("is_deleted = false")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;

	@Column(nullable = false, unique = true)
	private String email;

	@Size(min = 2, max = 12)
	@Column(nullable = false, unique = true, length = 12)
	private String nickName;

	@Column(nullable = false, length = 60)
	private String password;

	private String introduction;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	@Builder
	public User(String email, String nickName, String password) {
		this.email = email;
		this.nickName = nickName;
		this.password = password;
		this.grade = Grade.BRONZE;
	}

	public void update(String nickName, String introduction, Position position) {
		this.nickName = nickName;
		this.introduction = introduction;
		this.position = position;
	}

	public boolean isPasswordCheck(PasswordEncoder passwordEncoder, String password) {
		return passwordEncoder.matches(password, this.password);
	}

	public void updatePassword(String newPassword) {
		this.password = newPassword;
	}
}
