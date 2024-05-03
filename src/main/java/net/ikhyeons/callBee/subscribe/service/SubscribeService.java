package net.ikhyeons.callBee.subscribe.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import net.ikhyeons.callBee.subscribe.Subscribe;
import net.ikhyeons.callBee.subscribe.repository.SubscribeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService{
    private final SubscribeRepository subscribeRepository;
    private final MemberRepository memberRepository;
    private final ChannelRepository channelRepository;

    public Long subscribe(Long memberId, Long channelId){
        Subscribe subscribe = new Subscribe();
        Member member = memberRepository.findById(memberId).get();
        Channel channel = channelRepository.findById(channelId).get();
        subscribe.init(1, member, channel);
        subscribeRepository.save(subscribe);
        return subscribe.getId();
    };
    
    //구독 연장
    public Long extendSubscribe(Long subsId){
        Subscribe subscribe = subscribeRepository.findById(subsId).get();
        subscribe.extendSubscribe();
        return subscribe.getId();
    }
}
