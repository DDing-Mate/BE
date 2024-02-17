package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @GetMapping()
//    public ApiResponse<MemberResponse> getCurrentMember(@AuthenticationPrincipal User user) {
//        return ApiResponse.ok(memberService.getCurrentMember(user.getUsername()));
//    }

    @PatchMapping()
    public ApiResponse<Void> updateMember(@AuthenticationPrincipal User user,
                                          @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(user.getUsername(), memberUpdateRequest);
        return ApiResponse.ok();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteMember(@AuthenticationPrincipal User user) {
        memberService.deleteMember(user.getUsername());
        return ApiResponse.ok();
    }

    @GetMapping()
    public ApiResponse<MemberResponse> retrieveMember(@AuthenticationPrincipal User user) {
        return ApiResponse.ok(memberService.retrieveMember(user.getUsername()));
    }

    @GetMapping
    public ApiResponse<List> retrieveAll() {
        return ApiResponse.ok(memberService.retrieveAll());
    }

    @PatchMapping("/password")
    public ApiResponse<Void> updateMemberPassword(@AuthenticationPrincipal User user,
                                                  @RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        memberService.updateMemberPassword(user.getUsername(), memberPasswordUpdateRequest);
        return ApiResponse.ok();
    }

}
