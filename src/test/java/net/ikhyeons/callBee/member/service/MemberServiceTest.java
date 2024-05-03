package net.ikhyeons.callBee.member.service;

import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.MemberDto;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import net.ikhyeons.callBee.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
@DisplayName("멤버 서비스")
public class MemberServiceTest {
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;
    @Autowired private ChannelRepository channelRepository;


    @Test
    @DisplayName("회원가입")
    void 회원가입() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리", "19991123", channel);
        //when
        Long id = memberService.join(member);
        Member storeMember = memberRepository.findById(id).get();
        //check
        assertEquals("정상 회원가입", member, storeMember);

        //given
        Member member2 = new Member();
        Channel channel2 = new Channel();
        member2.init("skantrkwl789@naver.com", "1234", "성익현","잠자는오리", "19991123", channel2);
        //check

        assertThrows(DataIntegrityViolationException.class,()-> {
            memberRepository.save(member2);
        });

    }
    
    @Test
    @DisplayName("이메일 중복 확인")
    void 이메일중복확인() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        //check
        boolean isValidate = memberService.checkEmailValidation("skantrkwl789@naver.com");
        assertEquals("이미 등록된 이메일이 없을 경우에는 true를 반환", isValidate, true);

        Long id = memberRepository.save(member).getId();
        isValidate = memberService.checkEmailValidation("skantrkwl789@naver.com");
        assertEquals("이미 등록된 이메일이 있을 경우에는 false를 반환", isValidate, false);

    }

    @Test
    @DisplayName("닉네임 중복 확인")
    void 닉네임중복확인() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        //check
        boolean isValidate = memberService.checkNicknameValidation("잠자는오리");
        assertEquals("이미 등록된 닉네임이 없을 경우에는 true를 반환", isValidate, true);

        Long id = memberRepository.save(member).getId();
        isValidate = memberService.checkNicknameValidation("잠자는오리");
        assertEquals("이미 등록된 닉네임이 있을 경우에는 false를 반환", isValidate, false);

    }

    @Test
    @DisplayName("회원 데이터 조회")
    void 회원데이터가져오기() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        Long id = memberService.join(member);
        //when
        Member storedMember = memberService.findOneMember(id);

        //then
        assertEquals("정상 조회", member, storedMember);
        //만약 유요하지 않은 아이디로 조회할 경우 에러
        assertEquals("아이디가 없어서 조회 실패해야 함", true, memberRepository.findById(0L).isEmpty());
    }
    
    @Test
    @DisplayName("멤버 닉네임 수정")
    void 멤버정보수정() throws Exception{
        //given
        Member member = new Member();
        Channel channel = new Channel();
        member.init("skantrkwl789@naver.com", "1234", "성익현", "잠자는오리","19991123", channel);
        MemberDto memberDto = new MemberDto();

        memberDto.setMemberNickname("슬픈개미핥기");

        //when
        Long id = memberService.join(member);
        memberService.updateNickname(id, memberDto);
        Member storedMember = memberService.findOneMember(id);
        //then
        assertEquals("닉네임이 슬픈개미핥기로 변경되어야 함", "슬픈개미핥기", storedMember.getMemberNickname());
    }
}
