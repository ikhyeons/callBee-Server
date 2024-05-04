package net.ikhyeons.callBee.clip.service;

import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.clip.Clip;
import net.ikhyeons.callBee.clip.repository.ClipRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("클립 서비스")
class ClipServiceTest {
    @Autowired
    private ClipService clipService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ClipRepository clipRepository;

    @Test
    @DisplayName("클립 생성")
    void 클립생성() throws Exception{
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
        Long clipId = clipService.createClip("클립데이터", m1id, channel2.getId());
        //then
        Clip clip = clipRepository.findById(clipId).get();

        assertEquals("클립 생성 하기", clip.getData(), "클립데이터");
    }

    @Test
    @DisplayName("클립 삭제")
    void 클립삭제() throws Exception{
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
        Long clipId = clipService.createClip("클립데이터", m1id, channel2.getId());
        Clip clip = clipRepository.findById(clipId).get();
        clipService.deleteClip(clipId);
        //then
        assertEquals("클립 논리 삭제", clip.isDel(), true);
    }

}