package com.koreait.exam.springbatch_app_10.service;

import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import com.koreait.exam.springbatch_app_10.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    @DisplayName("회원가입")
    void t1() {
        String username = "user10";
        String password = "1234";
        String email = "user10@test.com";
        memberService.join(username, password, email);
        Member foundMember = memberService.findByUsername("user10").get();
        assertThat(foundMember.getCreateDate()).isNotNull();
        assertThat(foundMember.getUsername()).isNotNull();
        assertThat(passwordEncoder.matches(password, foundMember.getPassword())).isTrue();
    }
    // 실패 테스트
    @Test
    @DisplayName("회원가입2")
    void t2() {
        String username = "user1";
        String password = "1234";
        String email = "user10@test.com";
        memberService.join(username, password, email);
        Member foundMember = memberService.findByUsername("user1").get();
        assertThat(foundMember.getCreateDate()).isNotNull();
        assertThat(foundMember.getUsername()).isNotNull();
        assertThat(passwordEncoder.matches(password, foundMember.getPassword())).isTrue();
    }
}
