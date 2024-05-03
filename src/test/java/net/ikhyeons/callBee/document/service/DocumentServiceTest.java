package net.ikhyeons.callBee.document.service;

import net.ikhyeons.callBee.category.Category;
import net.ikhyeons.callBee.category.repository.CategoryRepository;
import net.ikhyeons.callBee.category.service.CategoryService;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.chat.Chat;
import net.ikhyeons.callBee.document.Document;
import net.ikhyeons.callBee.document.repository.DocumentRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import net.ikhyeons.callBee.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("게시글 서비스")
class DocumentServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DocumentService documentService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DocumentRepository documentRepository;

    @Test
    @DisplayName("글 작성")
    void 글작성() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);

        Long cid = categoryService.addCategory(member.getChannel().getId(), "공지사항");

        //when
        Long did = documentService.writeDoc(cid, member.getId(), "첫번째 공지", "스프링 어렵다;");
        Document document = documentRepository.findById(did).get();
        //then
        assertEquals("'첫번째 공지' 라는 글 작성", document.getDocTitle(), "첫번째 공지");
    }

    @Test
    @DisplayName("글 삭제")
    void 글삭제() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);

        Long cid = categoryService.addCategory(member.getChannel().getId(), "공지사항");
        //when
        Long did = documentService.writeDoc(cid, member.getId(), "첫번째 공지", "스프링 어렵다;");
        documentService.deleteDoc(did);
        Document document = documentRepository.findById(did).get();
        //then
        assertEquals("글 삭제", document.isDel(), true);
    }
    
    @Test
    @DisplayName("글 수정")
    void 글수정() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);

        Long cid = categoryService.addCategory(member.getChannel().getId(), "공지사항");
        //when
        Long did = documentService.writeDoc(cid, member.getId(), "첫번째 공지", "스프링 어렵다;");
        documentService.updateDoc(did, "두 번째 공지", "진짜 어렵다..");
        Document document = documentRepository.findById(did).get();
        //then
        assertEquals("글 정보 수정", document.getDocTitle(), "두 번째 공지");
    }
}