package net.ikhyeons.callBee.chat.repository;

import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    public List<Chat> findAllByChannel(Channel channel);
}
