package com.inno.yummy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inno.yummy.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUsername(String username);

    List<Comment> findAllByPostId(Long postId);
}
