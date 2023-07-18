package com.inno.yummy.service;


import com.inno.yummy.dto.CommentRequestDto;
import com.inno.yummy.dto.MessageResponseDto;
import com.inno.yummy.entity.Comment;
import com.inno.yummy.entity.Post;
import com.inno.yummy.entity.User;
import com.inno.yummy.entity.UserComment;
import com.inno.yummy.repository.CommentRepository;
import com.inno.yummy.repository.PostRepository;
import com.inno.yummy.repository.UserCommentRepository;
import com.inno.yummy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserCommentRepository userCommentRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public MessageResponseDto createComment(CommentRequestDto commentRequestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("없는 사용자입니다.")
        );

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );

        Comment comment = new Comment(commentRequestDto, username, user.getName());
        comment.connectPost(post); // comment - post 연관관계 설정
        commentRepository.save(comment);


        UserComment userComment = new UserComment();
        userComment.connectUser(user); // userComment - user 연관관계 설정
        userComment.connectComment(comment); // userComment - comment 연관관계 설정
        userCommentRepository.save(userComment);

        return new MessageResponseDto(HttpStatus.OK.toString(), true);
    }
}
