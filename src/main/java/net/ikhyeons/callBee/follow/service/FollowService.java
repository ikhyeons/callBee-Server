package net.ikhyeons.callBee.follow.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.follow.Follow;
import net.ikhyeons.callBee.follow.repository.FollowRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final ChannelRepository channelRepository;

    public Long follow(Long memberId, Long channelId){
        //만약 이미 팔로우한 전적이 있을경우 팔로우 재활성화

        Member member = memberRepository.findById(memberId).get();
        Channel channel = channelRepository.findById(channelId).get();
        Follow storedFollow = followRepository.findByMemberIdAndChannelId(memberId, channelId);
        Long fid = storedFollow == null? firstF(member, channel) : secondF(storedFollow);
        return fid;
    }

    private Long firstF(Member member, Channel channel){
        Follow follow = new Follow();
        follow.init(member, channel);
        followRepository.save(follow);
        return follow.getId();
    }

    private Long secondF(Follow follow){
        follow.activeFollow();
        follow.restartFollowTime();
        return follow.getId();
    }

    public Long stopFollow(Long followId){
        Follow follow = followRepository.findById(followId).get();

        //이전 정지시간
        LocalTime start = follow.getStopTime() == null ? follow.getCreateTime().toLocalTime() : follow.getStopTime().toLocalTime();

        //정지 시간 수정 및 팔로우 정지
        follow.stopFollowTime();
        LocalTime end = LocalDateTime.now().toLocalTime();

        //팔로우 활성 기간 증가
        Duration diff = Duration.between(start, end);
        Long diffMin = diff.toMinutes() >= 1 ? diff.toMinutes() : 0;
        follow.increaseTimeLength(diffMin);

        //팔로우 상태 수정
        follow.inactiveFollow();
        return follow.getId();
    }
}
