package com.inno.yummy.repository;

import com.inno.yummy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUsername(String username);
}
