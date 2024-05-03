package net.ikhyeons.callBee.clip.repository;

import net.ikhyeons.callBee.clip.Clip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
}
