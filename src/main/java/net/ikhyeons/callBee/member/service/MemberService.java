package net.ikhyeons.callBee.member.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.ChannelDto;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.exception.UniqueKeyDuplicatedException;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.MemberDto;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member){
        return memberRepository.save(member).getId();
    }

    public Member findOneMember(Long id){
        return memberRepository.findById(id).get();
    }
    /**
     * 중복된 이메일이 있으면 false
     * 중복된 이메일이 없으면 true
     * @param email
     * @return boolean
     */
    public boolean checkEmailValidation(String email){
        return !memberRepository.existByMemberEmail(email);
    }

    /**
     * 중복된 이메일이 있으면 false
     * 중복된 이메일이 없으면 true
     * @param nickname
     * @return boolean
     */
    public boolean checkNicknameValidation(String nickname){
        return !memberRepository.existByMemberNickname(nickname);
    }

    public Long updateNickname(Long id, MemberDto memberDto){
        Member member = memberRepository.findById(id).get();
        member.updateNickname(memberDto.getMemberNickname());

        return id;
    }
}
