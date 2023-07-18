package com.inno.yummy.service;

import com.inno.yummy.dto.HomeResponseDto;
import com.inno.yummy.dto.MessageResponseDto;
import com.inno.yummy.dto.PostRequestDto;
import com.inno.yummy.dto.PostResponseDto;
import com.inno.yummy.entity.Post;
import com.inno.yummy.entity.User;
import com.inno.yummy.repository.PostRepository;
import com.inno.yummy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<HomeResponseDto> getHomePosts() {
        return postRepository.findAll().stream().map(HomeResponseDto::new).toList();
    }

    public List<HomeResponseDto> getHomePostsByWant(String region, String sort) {
        if(region == null) {
            return postRepository.findAllBySort(sort).stream().map(HomeResponseDto::new).toList();
        }

        if(sort == null) {
            return postRepository.findAllByRegion(region).stream().map(HomeResponseDto::new).toList();
        }

        return postRepository.findAllByRegionAndSort(region, sort).stream().map(HomeResponseDto::new).toList();
    }

    public MessageResponseDto createPost(PostRequestDto postRequestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("없는 사용자입니다.")
        );
        Post post = new Post(postRequestDto, username, user.getName());

        post.connectUser(user);

        postRepository.save(post);

        return new MessageResponseDto(HttpStatus.OK.toString(), true);
    }

    public PostResponseDto getPost(Long postid) {
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public MessageResponseDto updatePost(Long postId, PostRequestDto postRequestDto, String username) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );

        if(!post.getUsername().equals(username)) {
            throw new IllegalArgumentException("글을 작성한 사용자가 아닙니다.");
        }

        post.updatePost(postRequestDto);

        postRepository.save(post);

        return new MessageResponseDto(HttpStatus.OK.toString(), true);
    }

    @Transactional
    public MessageResponseDto deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );

        if(!post.getUsername().equals(username)) {
            throw new IllegalArgumentException("글을 작성한 사용자가 아닙니다.");
        }

        postRepository.delete(post);

        return new MessageResponseDto(HttpStatus.OK.toString(), true);
    }
}
