package net.ikhyeons.callBee.reply.repository;

import net.ikhyeons.callBee.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {}
