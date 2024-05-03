package net.ikhyeons.callBee.document;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.category.Category;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.reply.Reply;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;

    @Column(nullable = false)
    private String docTitle;

    @Column(nullable = false)
    private String docContent;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "document")
    private List<Reply> replys = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Column(nullable = false)
    private boolean del;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void addReply(Reply reply){
        this.replys.add(reply);
    }

    public void init(String title, String content, Category category, Member member){
        this.docTitle = title;
        this.docContent = content;
        this.category = category;
        this.member = member;
    }

    public void delete() {
        this.del = true;
    }
}
