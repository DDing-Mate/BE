package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/{userId}")
    public ApiResponse<Void> updateMember(@PathVariable("userId") Long id,
                                          @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(id, memberUpdateRequest);
        return ApiResponse.ok();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ApiResponse.ok();
    }

    @GetMapping("{id}")
    public ApiResponse<MemberResponse> retrieveMember(@PathVariable Long id) {
//        Member member = memberService.retrieveMember(id);
//        MemberResponse memberResponse = MemberResponse.from(member);
        return ApiResponse.ok(memberService.retrieveMember(id));
    }

    @GetMapping
    public ApiResponse<List> retrieveAll() {
        return ApiResponse.ok(memberService.retrieveAll());
    }

    @PatchMapping("/password/{userId}")
    public ApiResponse<Void> updateMemberPassword(@PathVariable("userId") Long id,
                                                  @RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        memberService.updateMemberPassword(id, memberPasswordUpdateRequest);
        return ApiResponse.ok();
    }

}
