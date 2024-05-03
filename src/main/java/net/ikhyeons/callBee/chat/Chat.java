package net.ikhyeons.callBee.chat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean del;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    public void delete(){
        this.del = true;
    }

    public void init(Member member, Channel channel, String message){
        this.message = message;
        this.member = member;
        this.channel = channel;
    }
}
