package net.ikhyeons.callBee.subscribe.repository;

import net.ikhyeons.callBee.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {}
