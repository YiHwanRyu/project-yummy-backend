package com.inno.yummy.entity;

import com.inno.yummy.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopname;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgurl;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sort;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();


    public Post(PostRequestDto postRequestDto, String username, String name) {
        this.shopname = postRequestDto.getShopname();
        this.content = postRequestDto.getContent();
        this.username = username;
        this.name = name;
        this.sort = postRequestDto.getSort();
        this.region = postRequestDto.getRegion();
        this.address = postRequestDto.getAddress();
        this.imgurl = postRequestDto.getImgurl();
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.shopname = postRequestDto.getShopname();
        this.content = postRequestDto.getContent();
        this.sort = postRequestDto.getSort();
        this.region = postRequestDto.getRegion();
        this.address = postRequestDto.getAddress();
        this.imgurl = postRequestDto.getImgurl();
    }

    public void connectUser(User user) {
        this.user = user;
    }
}
