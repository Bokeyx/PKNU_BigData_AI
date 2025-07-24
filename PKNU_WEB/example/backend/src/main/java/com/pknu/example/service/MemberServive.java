package com.pknu.example.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pknu.example.controller.IndexController;
import com.pknu.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServive {

    private final MemberRepository MemberRepository;
    private final IndexController IndexController;

    @Autowired
    public MemberServive(MemberRepository memberRepository, IndexController indexController) {
        this.MemberRepository = memberRepository;
        this.IndexController = indexController;
    }
    
    public List<Member> getMemberList() {
        return this.MemberRepository.findAll();
    }

    public Member getMemberView(String mem_id) {
        // Optional: 객체를 감싸서 null 체크를 편리하게하는 클래스
        Optional<Member> member = this.MemberRepository.findById(mem_id);

        // 조회결과가 있다면.
        if(member.isPresent()) {
            // 터미널에 log 메시지 남기기
            log.info("회원아이디[{}]의 정보를 정상적으로 조회하였습니다.",mem_id);
            // get(): Optional객체에 담겨있는 Day6001.Member 객체를 추출합니다
            return member.get();
        } else {
            // 조회결과가 없다면.
            return null;
        }
    }
    
}
