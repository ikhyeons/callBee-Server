package net.ikhyeons.callBee.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.member.QMember;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory query;

    @Override
    public boolean existByMemberEmail(String email) {
        QMember member = new QMember("m");

        return query.selectOne()
                .from(member)
                .where(member.memberEmail.eq(email))
                .fetchFirst() != null;
    }

    @Override
    public boolean existByMemberNickname(String nickname) {
        QMember member = new QMember("m");

        return query.selectOne()
                .from(member)
                .where(member.memberNickname.eq(nickname))
                .fetchFirst() != null;
    }
}