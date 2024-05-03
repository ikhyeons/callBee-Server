package net.ikhyeons.callBee.clip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Clip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clip_id")
    private Long id;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(nullable = false)
    private boolean del;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;


    public void delete(){
        this.del = true;
    }
    public void init(String data, Member member, Channel channel){
        this.data = data;
        this.member = member;
        this.channel = channel;
    }
}
