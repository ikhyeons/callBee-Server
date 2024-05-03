package net.ikhyeons.callBee.follow.service;

import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.channel.service.ChannelService;
import net.ikhyeons.callBee.follow.Follow;
import net.ikhyeons.callBee.follow.repository.FollowRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import net.ikhyeons.callBee.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Timer;
import java.util.TimerTask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("팔로우 서비스")
class FollowServiceTest {
    @Autowired
    private FollowService followService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Test
    @DisplayName("팔로우")
    void 팔로우() throws Exception{
        //given
        Member member1 = new Member();
        Channel channel1 = new Channel();
        member1.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel1);
        Long m1id = memberService.join(member1);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);
        Long m2id = memberService.join(member2);
        Long m2cid = member2.getChannel().getId();

        //when
        Long fid = followService.follow(m1id, m2cid);
        //then
        assertEquals("채널 팔로우 하기", followRepository.findById(fid).get().getMember(), member1);
    }

    @Test
    @DisplayName("팔로우 취소")
    void 팔로우취소() throws Exception{
        //given
        Member member1 = new Member();
        Channel channel1 = new Channel();
        member1.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel1);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);
        Long m1id = memberService.join(member1);
        Long m2id = memberService.join(member2);
        Long m2cid = member2.getChannel().getId();
        //when
        Long fid = followService.follow(m1id, m2cid);

        followService.stopFollow(fid);
        Follow follow = followRepository.findById(fid).get();
        //then
        assertEquals("activate가 false로 변경되어야 함", follow.isActivate() , false);
        //StopTime이 0이 아니어야 함.
        assertNotEquals(follow.getStopTime(), 0);
    }

    @Test
    @DisplayName("팔로우 재활성화")
    void 팔로우재활성화() throws Exception{
        //given
        Member member1 = new Member();
        Channel channel1 = new Channel();
        member1.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel1);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);
        Long m1id = memberService.join(member1);
        Long m2id = memberService.join(member2);
        Long m2cid = member2.getChannel().getId();
        Long fid = followService.follow(m1id, m2cid);
        followService.stopFollow(fid);

        //when
        followService.follow(m1id, m2cid);
        Follow follow = followRepository.findById(fid).get();

        //then
        assertEquals("activate가 true로 변경되어야 함", follow.isActivate() , true);
        //StopTime이 0이 아니어야 함.
        assertNotEquals(follow.getRestartTime(), 0);

    }
}