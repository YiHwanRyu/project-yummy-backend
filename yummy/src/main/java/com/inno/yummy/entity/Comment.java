package com.inno.yummy.entity;

import com.inno.yummy.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<UserComment> userCommentList = new ArrayList<>();

    public Comment(CommentRequestDto commentRequestDto, String username, String name) {
        this.username = username;
        this.name = name;
        this.content = commentRequestDto.getContent();
    }

    public void connectPost(Post post) {
        this.post = post;
    }
}
