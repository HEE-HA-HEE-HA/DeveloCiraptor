package org.example.develociraptor.domain.chatroom.repository;


import org.example.develociraptor.domain.chatroom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom, Long> {

}
