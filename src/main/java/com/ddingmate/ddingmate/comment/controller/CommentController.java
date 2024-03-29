package com.ddingmate.ddingmate.comment.controller;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.comment.dto.request.CreateCommentRequest;
import com.ddingmate.ddingmate.comment.dto.request.CreateReplyRequest;
import com.ddingmate.ddingmate.comment.dto.response.CommentResponse;
import com.ddingmate.ddingmate.comment.service.CommentService;
import com.ddingmate.ddingmate.member.state.UserAuthorize;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    @UserAuthorize
    public ApiResponse<Void> createComment(@AuthenticationPrincipal Long memberId,  @RequestBody CreateCommentRequest createCommentRequest) {
        commentService.createComment(memberId, createCommentRequest);
        return ApiResponse.ok();
    }

    @PostMapping("/reply/{commentId}")
    @UserAuthorize
    public ApiResponse<Void> toComment(@PathVariable(name = "commentId") Long id,
                                       @AuthenticationPrincipal Long memberId,
                                       @RequestBody CreateReplyRequest createReplyRequest) {
        commentService.createReply(id, memberId, createReplyRequest);
        return ApiResponse.ok();
    }

    @GetMapping("/byPost/{postId}")
    public ApiResponse<List<CommentResponse>> retrieveCommentByPost(@AuthenticationPrincipal Long user,
                                                                    @PathVariable(name = "postId") Long id) {
        List<CommentResponse> commentResponses = commentService.retrieveCommentByPost(id).stream()
                .map(c -> {
                    boolean isMine = c.getMember() != null && c.getMember().getId().equals(user);
                    return CommentResponse.from(c, isMine);
                })
                .collect(Collectors.toList());

        return ApiResponse.ok(commentResponses);
    }

    @GetMapping("/byMember")
    public ApiResponse<List<CommentResponse>> retrieveCommentByMember(@AuthenticationPrincipal Long memberId) {
        List<CommentResponse> comments = commentService.retrieveCommentByMember(memberId).stream()
                .map(comment -> CommentResponse.from(comment, true))
                .collect(Collectors.toList());
        return ApiResponse.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    @UserAuthorize
    public ApiResponse<Void> deleteCommentById(@AuthenticationPrincipal Long user,
                                               @PathVariable(name = "commentId") Long id) {
        commentService.deleteCommentById(id);
        return ApiResponse.ok();
    }

    @PatchMapping("/{commentId}")
    @UserAuthorize
    public ApiResponse<Void> updateComment(@AuthenticationPrincipal Long user,
                                           @PathVariable(name = "commentId") Long id, @RequestParam String content) {
        commentService.updateComment(id, content);
        return ApiResponse.ok();
    }

}
