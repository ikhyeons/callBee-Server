package net.ikhyeons.callBee.reply.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.document.Document;
import net.ikhyeons.callBee.document.repository.DocumentRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import net.ikhyeons.callBee.reply.Reply;
import net.ikhyeons.callBee.reply.repository.ReplyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final DocumentRepository documentRepository;

    public Long writeReply(Long memberId, Long documentId, String content){
        Reply reply = new Reply();
        Member member = memberRepository.findById(memberId).get();
        Document document = documentRepository.findById(documentId).get();
        reply.init(content, member, document);
        replyRepository.save(reply);
        document.addReply(reply);

        return reply.getId();
    }
    public Long deleteReply(Long replyId){
        Reply reply = replyRepository.findById(replyId).get();
        reply.delete();

        return replyId;
    }
    public Long updateReply(Long replyId, String content){
        Reply reply = replyRepository.findById(replyId).get();
        reply.init(content, reply.getMember(), reply.getDocument());

        return replyId;
    }
}
