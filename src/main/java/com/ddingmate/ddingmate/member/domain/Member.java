package com.ddingmate.ddingmate.member.domain;

import com.ddingmate.ddingmate.member.dto.request.MemberPasswordUpdateRequest;
import com.ddingmate.ddingmate.member.dto.request.MemberUpdateRequest;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Role;
import com.ddingmate.ddingmate.member.util.MajorConverter;
import com.ddingmate.ddingmate.member.util.RoleConverter;
import com.ddingmate.ddingmate.post.state.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    @Convert(converter = MajorConverter.class)
    private Major major;
    private Long studentId;
    private LocalDate birth;
    private String introduction;
    private Category category;
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Builder
    public Member(String email, String password, String name, Major major, Long studentId,
                  LocalDate birth, String introduction, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.major = major;
        this.studentId = studentId;
        this.birth = birth;
        this.introduction = introduction;
        this.role = role;
    }

    public void update(MemberUpdateRequest memberUpdateRequest) {
        this.introduction = memberUpdateRequest.getIntroduction();
        this.major = memberUpdateRequest.getMajor();
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
