package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.domain.Member;
import lombok.Getter;

import java.util.Date;

@Getter
public class RegisterRequest {

    private String email;
    private String password;
    private String passwordCheck;
    private String name;
//    private Major major;
    private String studentId;
    private Date birth;
    private String introduction;


    public Member toEntity(String encodePassword) {
        return Member.builder()
                .email(this.email)
                .password(encodePassword)
                .name(this.name)
//                .major(this.major)
                .studentId(this.studentId)
                .birth(this.birth)
                .introduction(this.introduction)
                .build();
    }
}
