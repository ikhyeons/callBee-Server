package net.ikhyeons.callBee.channel.service;

import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.ChannelDto;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("채널 서비스")
class ChannelServiceTest {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ChannelRepository channelRepository;

    @Test
    @DisplayName("채널 정보 업데이트")
    void 채널정보업데이트() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);

        ChannelDto channelDto = new ChannelDto(
                "채널 제목",
                "채널 카테고리",
                "채널 부제목",
                "채널보드");
        //when
        Long mcId = memberService.join(member);
        Long cId = channelService.updateChannel(mcId, channelDto);
        //then
        Channel resultChannel = channelRepository.findById(cId).get();
        assertThat(resultChannel.getChannelTitle()).isEqualTo("채널 제목");
    }
    
    @Test
    @DisplayName("채널 정보 조회")
    void 채널정보조회() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        //when
        Long mcId = memberService.join(member);
        Channel channel1 = channelService.findOneChannel(mcId);
        channel1.initMember(member);
        //then
        assertEquals("채널 정보를 id로 정상적으로 조회함.", channel1.getMember(), member);
    }

}