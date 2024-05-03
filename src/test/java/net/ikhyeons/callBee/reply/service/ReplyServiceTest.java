package net.ikhyeons.callBee.reply.service;

import net.ikhyeons.callBee.category.service.CategoryService;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.document.Document;
import net.ikhyeons.callBee.document.service.DocumentService;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.service.MemberService;
import net.ikhyeons.callBee.reply.Reply;
import net.ikhyeons.callBee.reply.repository.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("댓글 서비스")
class ReplyServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;


    @Test
    @DisplayName("댓글 작성")
    void 댓글작성() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);

        Long cid = categoryService.addCategory(member.getChannel().getId(), "공지사항");
        Long did = documentService.writeDoc(cid, member.getId(), "첫번째 공지", "스프링 어렵다;");
        Long rid = replyService.writeReply(member.getId(), did, "ㅇㅈ 또 ㅇㅈ");
        //when
        Reply reply = replyRepository.findById(rid).get();
        //then
        assertEquals("댓글을 작성함", reply.getReplyContent(), "ㅇㅈ 또 ㅇㅈ");
    }
    
    @Test
    @DisplayName("댓글 수정")
    void 댓글수정() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);

        Long cid = categoryService.addCategory(member.getChannel().getId(), "공지사항");
        Long did = documentService.writeDoc(cid, member.getId(), "첫번째 공지", "스프링 어렵다;");
        Long rid = replyService.writeReply(member.getId(), did, "ㅇㅈ 또 ㅇㅈ");
        //when
        Reply reply = replyRepository.findById(rid).get();
        replyService.updateReply(rid, "ㅇㅈ 또 또 ㅇㅈ");
        //then
        assertEquals("댓글을 작성함", reply.getReplyContent(), "ㅇㅈ 또 또 ㅇㅈ");
    }

    @Test
    @DisplayName("댓글 삭제")
    void 댓글삭제() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);

        Long cid = categoryService.addCategory(member.getChannel().getId(), "공지사항");
        Long did = documentService.writeDoc(cid, member.getId(), "첫번째 공지", "스프링 어렵다;");
        Long rid = replyService.writeReply(member.getId(), did, "ㅇㅈ 또 ㅇㅈ");
        //when
        replyService.deleteReply(rid);
        Reply reply = replyRepository.findById(rid).get();
        //then
        assertEquals("댓글 논리 삭제", reply.isDel(), true);
    }

}