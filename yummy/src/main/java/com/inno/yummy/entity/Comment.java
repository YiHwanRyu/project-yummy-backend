package com.inno.yummy.entity;

import com.inno.yummy.dto.CommentRequestDto;
import com.inno.yummy.dto.UpdateCommentRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(CommentRequestDto commentRequestDto, String username, String name) {
        this.username = username;
        this.name = name;
        this.content = commentRequestDto.getContent();
    }

    public void connectPost(Post post) {
        this.post = post;
    }

    public void connectUser(User user) {
        this.user = user;
    }

    public void updateComment(UpdateCommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

}
