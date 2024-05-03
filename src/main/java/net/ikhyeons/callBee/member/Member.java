package net.ikhyeons.callBee.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.ikhyeons.callBee.channel.Channel;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberNickname;

    @Column(nullable = false)
    private String memberBirth;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime JoinDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    public void updateNickname(String nickname){
        this.memberNickname = nickname;
    }
    public void init(String email, String password, String name, String nickname, String birth, Channel channel){
        this.memberEmail = email;
        this.memberPassword = password;
        this.memberName = name;
        this.memberNickname = nickname;
        this.memberBirth = birth;
        this.channel = channel;
    }
}