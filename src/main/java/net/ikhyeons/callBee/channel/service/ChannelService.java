package net.ikhyeons.callBee.channel.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.channel.ChannelDto;
import net.ikhyeons.callBee.channel.repository.ChannelRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public Long updateChannel(Long id, ChannelDto channelDto){
        Channel channel = channelRepository.findById(id).get();
        channel.init(channelDto.getChannelTitle(),
                channelDto.getChannelCategory(),
                channelDto.getChannelSubtitle(),
                channelDto.getChannelBoard());
        return id;
    }
    public Channel findOneChannel(Long id){
        return channelRepository.findById(id).get();
    }
}
