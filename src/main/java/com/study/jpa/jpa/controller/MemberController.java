package com.study.jpa.jpa.controller;

import com.study.jpa.jpa.dto.MemberDTO;
import com.study.jpa.jpa.entity.Member;
import com.study.jpa.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();

        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
//        Member findMember = memberRepository.findById(member.getId()).get();

        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<Member> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);

        return page;
    }

    @GetMapping("/members2")
    public Page<MemberDTO> listDTO(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDTO> memberDTOs = page.map(m -> new MemberDTO(m.getId(), m.getUsername(), null));

        return memberDTOs;
    }

//    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("userA" + i, i));
        }
    }

}
