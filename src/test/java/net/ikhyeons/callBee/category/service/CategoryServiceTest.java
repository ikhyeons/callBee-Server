package net.ikhyeons.callBee.category.service;

import net.ikhyeons.callBee.category.Category;
import net.ikhyeons.callBee.category.repository.CategoryRepository;
import net.ikhyeons.callBee.channel.Channel;
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
@DisplayName("카테고리 서비스")
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 추가")
    void 카테고리추가() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);
        Long cid = member.getChannel().getId();

        //when
        Long categoryid = categoryService.addCategory(cid, "공지사항");
        Category storedCategory = categoryRepository.findById(categoryid).get();
        //then
        assertEquals("카테고리를 생성함.", storedCategory.getCategoryName(), "공지사항");
        
    }

    @Test
    @DisplayName("카테고리 이름 변경")
    void 카테고리이름변경() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);
        Long cid = member.getChannel().getId();
        //when
        Long categoryid = categoryService.addCategory(cid, "공지사항");
        categoryService.renameCategory(categoryid, "공지사항2");
        Category storedCategory = categoryRepository.findById(categoryid).get();
        //then
        assertEquals("카테고리를 생성함.", storedCategory.getCategoryName(), "공지사항2");
    }
    
    @Test
    @DisplayName("카테고리 삭제")
    void 카테고리삭제() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        memberService.join(member);
        Long cid = member.getChannel().getId();
        //when
        Long categoryid = categoryService.addCategory(cid, "공지사항");
        categoryService.deleteCategory(categoryid);
        Category storedCategory = categoryRepository.findById(categoryid).get();

        //then
        assertEquals("카테고리 논리삭제.", storedCategory.isDel(), true);
    }
}