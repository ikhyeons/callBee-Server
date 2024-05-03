package net.ikhyeons.callBee.follow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.member.Member;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    private LocalDateTime stopTime;

    private LocalDateTime restartTime;

    @Column(nullable = false)
    private boolean activate;

    @Column(nullable = false)
    private Long timeLength;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    public void init(Member member, Channel channel){
        this.member = member;
        this.channel = channel;
        this.activate = true;
        this.timeLength = 0L;
    }

    public void stopFollowTime(){
        this.stopTime = LocalDateTime.now();
    }

    public void restartFollowTime(){
        this.restartTime = LocalDateTime.now();
    }

    public void increaseTimeLength(Long diff){
        this.timeLength += diff;
    }

    public void activeFollow(){
        this.activate = true;
    }

    public void inactiveFollow(){
        this.activate = false;
    }
}