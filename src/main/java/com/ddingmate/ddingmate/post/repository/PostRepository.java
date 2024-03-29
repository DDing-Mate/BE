package com.ddingmate.ddingmate.post.repository;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.state.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByType(Type type);
    List<Post> findAllByMember(Member member);

    void deleteAllByMember(Member member);
}
