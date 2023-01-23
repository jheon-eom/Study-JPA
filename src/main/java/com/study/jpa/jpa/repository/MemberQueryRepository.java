package com.study.jpa.jpa.repository;

import com.study.jpa.jpa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final EntityManager em;

    List<Member> findAllMember() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }

}
