package net.ikhyeons.callBee.follow.repository;

import net.ikhyeons.callBee.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    public Follow findByMemberIdAndChannelId(Long memberId, Long channelId);
}
