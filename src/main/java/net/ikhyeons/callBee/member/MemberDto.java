package net.ikhyeons.callBee.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberBirth;
}