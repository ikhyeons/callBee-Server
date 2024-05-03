package net.ikhyeons.callBee.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.chat.Chat;
import net.ikhyeons.callBee.document.Document;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private boolean del;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @OneToMany(mappedBy = "category")
    private List<Document> documents = new ArrayList<>();

    public void delete(){
        this.del = true;
    }

    public void addDoc(Document doc){
        this.documents.add(doc);
    }


    public void rename(String name){
        this.categoryName = name;
    }

    public void init(String name, Channel channel){
        this.categoryName = name;
        this.channel = channel;
    }
}
