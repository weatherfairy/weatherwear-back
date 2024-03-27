package com.weatherfairy.weatherwearback.post.repository;

import com.weatherfairy.weatherwearback.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByMemberNo(Long memberNo, Pageable pageable);
}
