package net.ikhyeons.callBee.subscribe.service;

import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.service.MemberService;
import net.ikhyeons.callBee.subscribe.Subscribe;
import net.ikhyeons.callBee.subscribe.repository.SubscribeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("구독 서비스")
class SubscribeServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private SubscribeRepository subscribeRepository;

    @Test
    @DisplayName("구독")
    void 구독() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);

        Long m1id = memberService.join(member);
        Long m2id = memberService.join(member2);

        //when
        Long subsId = subscribeService.subscribe(m1id, member2.getChannel().getId());
        Subscribe subscribe = subscribeRepository.findById(subsId).get();
        //then
        assertEquals("구독",subscribe.getMember(), member);

    }

    @Test
    @DisplayName("구독 연장")
    void 구독연장() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);

        Long m1id = memberService.join(member);
        Long m2id = memberService.join(member2);

        Long subsId = subscribeService.subscribe(m1id, member2.getChannel().getId());
        //when
        subscribeService.extendSubscribe(subsId);
        Subscribe subscribe = subscribeRepository.findById(subsId).get();
        //then
        assertEquals("구독 연장",subscribe.getContineuity(), 2);
    }

}