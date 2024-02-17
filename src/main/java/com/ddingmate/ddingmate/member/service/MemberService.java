package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void updateMember(String username, MemberUpdateRequest memberUpdateRequest) {
        Member member = findMemberById(convertId(username));
        member.update(memberUpdateRequest);
    }

    @Transactional
    public void deleteMember(String username) {
        memberRepository.deleteById(convertId(username));
    }

    @Transactional(readOnly = true)
    public MemberResponse retrieveMember(String username) {
        return MemberResponse.from(findMemberById(convertId(username)));
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> retrieveAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateMemberPassword(String username, MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        Member member = findMemberById(convertId(username));
        if(!encoder.matches(memberPasswordUpdateRequest.getOldPassword(), member.getPassword())) {
            throw new IllegalArgumentException();
        }

        if(!memberPasswordUpdateRequest.getNewPassword().equals(memberPasswordUpdateRequest.getNewPasswordCheck())) {
            throw new IllegalArgumentException();
        }
        String newPassword = encoder.encode(memberPasswordUpdateRequest.getNewPassword());
        member.updatePassword(newPassword);
    }

    private Long convertId(String username) {
        return Long.valueOf(username);
    }
}
