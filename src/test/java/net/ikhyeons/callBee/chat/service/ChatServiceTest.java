package net.ikhyeons.callBee.chat.service;

import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.chat.Chat;
import net.ikhyeons.callBee.chat.repository.ChatRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("채팅 서비스")
class ChatServiceTest {
    @Autowired private ChatService chatService;
    @Autowired private MemberService memberService;
    @Autowired private ChatRepository chatRepository;

    @Test
    @DisplayName("채팅 보내기")
    void 채팅전송() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);

        Long m1id = memberService.join(member);
        Long m2id = memberService.join(member2);

        Chat chat = new Chat();
        chat.init(member, member2.getChannel(), "적당한 채팅");

        //when
        Long cid = chatService.sendChat(chat);
        //then
        assertEquals("채팅을 입력함", chatRepository.findById(cid).get(), chat);
    }
    
    @Test
    @DisplayName("채널의 채팅목록 조회")
    void 채팅목록조회() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);

        Long m1id = memberService.join(member);
        Long m2id = memberService.join(member2);

        Chat chat = new Chat();
        chat.init(member, member2.getChannel(), "적당한 채팅");

        Chat chat2 = new Chat();
        chat2.init(member2, member2.getChannel(), "적당한 채팅2");

        //when
        chatService.sendChat(chat);
        chatService.sendChat(chat2);
        List<Chat> chats = chatService.getChatList(member2.getChannel());
        //then
        assertEquals("입력된 채팅이 두개", 2, chats.size());
    }
    
    @Test
    @DisplayName("채팅 삭제")
    void 채팅삭제() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);

        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("wkdalsdnr3333@naver.com", "1234", "장민욱", "슬픈개미핥기","19990513", channel2);

        Long m1id = memberService.join(member);
        Long m2id = memberService.join(member2);

        Chat chat = new Chat();
        chat.init(member, member2.getChannel(), "적당한 채팅");

        //when
        Long cid = chatService.sendChat(chat);
        chatService.deleteChat(chat);
        //then
        assertEquals("채팅 논리 삭제", true, chat.isDel());
    }
}