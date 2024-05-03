package net.ikhyeons.callBee.member.repository;

public interface MemberCustomRepository {
    boolean existByMemberEmail(String email);

    boolean existByMemberNickname(String nickname);
}
