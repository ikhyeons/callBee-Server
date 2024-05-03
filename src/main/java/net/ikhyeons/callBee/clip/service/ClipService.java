package net.ikhyeons.callBee.clip.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.clip.Clip;
import net.ikhyeons.callBee.clip.repository.ClipRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClipService {
    private final ClipRepository clipRepository;
    private final MemberRepository memberRepository;
    private final ChannelRepository channelRepository;

    public Long createClip(String data, Long memberId, Long channelId){
        Clip clip = new Clip();
        Member member = memberRepository.findById(memberId).get();
        Channel channel = channelRepository.findById(channelId).get();
        clip.init(data ,member, channel);
        Clip storedClip = clipRepository.save(clip);
        return storedClip.getId();
    }

    public Long deleteClip(Long clipId){
        Clip clip = clipRepository.findById(clipId).get();
        clip.delete();

        return 0L;
    }
}
