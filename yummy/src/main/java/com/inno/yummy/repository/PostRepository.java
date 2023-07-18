package com.inno.yummy.repository;

import com.inno.yummy.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
     List<Post> findAllByRegion(String region);
     List<Post> findAllBySort(String sort);
     List<Post> findAllByRegionAndSort(String region, String sort);
}
