package net.ikhyeons.callBee.channel.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {}

