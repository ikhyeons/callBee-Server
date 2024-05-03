package net.ikhyeons.callBee.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import net.ikhyeons.callBee.category.Category;
import net.ikhyeons.callBee.chat.Chat;
import net.ikhyeons.callBee.clip.Clip;
import net.ikhyeons.callBee.follow.Follow;
import net.ikhyeons.callBee.member.Member;
import net.ikhyeons.callBee.subscribe.Subscribe;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    private String channelTitle;

    private String channelCategory;

    private String channelSubtitle;

    private String channelBoard;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "channel")
    private Member member;

    @OneToMany(mappedBy = "channel")
    private List<Chat> chats = new ArrayList<>();

    @OneToMany(mappedBy = "channel")
    private List<Follow> follow = new ArrayList<>();;

    @OneToMany(mappedBy = "channel")
    private List<Subscribe> subscribes = new ArrayList<>();;

    @OneToMany(mappedBy = "channel")
    private List<Clip> clip = new ArrayList<>();

    @OneToMany(mappedBy = "channel")
    private List<Category> category = new ArrayList<>();;

    public void initMember(Member member){
        this.member=member;
    }

    public void addChat(Chat chat){
        this.chats.add(chat);
    }

    public void addCateogry(Category category){
        this.category.add(category);
    }

    public void init(String title, String category, String subtitle, String board){
        this.channelTitle = title;
        this.channelCategory = category;
        this.channelSubtitle = subtitle;
        this.channelBoard = board;
    }
}
