package com.ddingmate.ddingmate.member.dto.response;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.state.Category;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MemberResponse(String name, String major, LocalDate birth, String introduction, List<Category> categories) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getMajor().getValue(), member.getBirth(), member.getIntroduction(), member.getCategories());
    }
}
