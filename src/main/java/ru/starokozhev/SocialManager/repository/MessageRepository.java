package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
