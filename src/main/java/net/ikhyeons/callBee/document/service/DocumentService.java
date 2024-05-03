package net.ikhyeons.callBee.document.service;

import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.category.Category;
import net.ikhyeons.callBee.category.repository.CategoryRepository;
import net.ikhyeons.callBee.document.Document;
import net.ikhyeons.callBee.document.repository.DocumentRepository;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public Long writeDoc(Long categoryId, Long memberId, String title, String content){
        Document document = new Document();
        Category category = categoryRepository.findById(categoryId).get();
        Member member = memberRepository.findById(memberId).get();
        document.init(title, content, category, member);

        Long did = documentRepository.save(document).getId();
        category.addDoc(document);

        return did;
    }

    public Long deleteDoc(Long documentId){
        Document document = documentRepository.findById(documentId).get();
        document.delete();

        return document.getId();
    }
    public Long updateDoc(Long documentId, String title, String content){
        Document document = documentRepository.findById(documentId).get();
        document.init(title, content, document.getCategory(), document.getMember());

        return document.getId();}

}
