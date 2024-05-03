package net.ikhyeons.callBee.reply;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.document.Document;
import net.ikhyeons.callBee.member.Member;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(nullable = false)
    private String replyContent;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Column(nullable = false)
    private boolean del;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    public void delete(){
        this.del = true;
    };

    public void init(String content, Member member, Document document){
        this.replyContent = content;
        this.member = member;
        this.document = document;
    }
}
