package net.ikhyeons.callBee.subscribe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ikhyeons.callBee.channel.Channel;
import net.ikhyeons.callBee.member.Member;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscribe_id")
    private Long id;

    @Column(nullable = false)
    private int contineuity;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    public void init(int contineuity, Member member, Channel channel){
        this.contineuity = contineuity;
        this.member = member;
        this.channel = channel;
        this.deadline = LocalDateTime.now().plusMonths(1);
    }

    public void extendSubscribe(){
        this.contineuity += 1;
        this.deadline = this.deadline.plusMonths(1);
    }
}
