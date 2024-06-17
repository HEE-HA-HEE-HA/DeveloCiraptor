package org.example.develociraptor.domain.blacklist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.member.entity.Member;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Blacklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blacklist_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_member_id")
	private Member fromMember;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_member_id")
	private Member toMember;

	@CreatedDate
	private LocalDateTime createdAt;
}
