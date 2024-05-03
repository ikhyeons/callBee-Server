package net.ikhyeons.callBee.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.ikhyeons.callBee.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {}
