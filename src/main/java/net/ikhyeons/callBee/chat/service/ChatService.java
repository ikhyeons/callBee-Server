package net.ikhyeons.callBee.chat.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.chat.Chat;
import net.ikhyeons.callBee.chat.repository.ChatRepository;
import net.ikhyeons.callBee.exception.MemberNotFoundException;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    public Long sendChat(Chat chat){
        Optional<Member> oMember = memberRepository.findById(chat.getMember().getId());

        if (!oMember.isPresent()){
            throw new MemberNotFoundException("Member not found with ID: " + chat.getMember().getId());
        }

        Chat storedChat = chatRepository.save(chat);
        chat.getChannel().addChat(chat);

        return storedChat.getId();
    };

    public Long deleteChat(Chat chat){
        Chat storedChat = chatRepository.findById(chat.getId()).get();
        storedChat.delete();

        return storedChat.getId();
    }

    public List<Chat> getChatList(Channel channel){
        return chatRepository.findAllByChannel(channel);
    }
}
