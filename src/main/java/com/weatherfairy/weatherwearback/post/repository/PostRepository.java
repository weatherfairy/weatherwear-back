package com.weatherfairy.weatherwearback.post.repository;

import com.weatherfairy.weatherwearback.common.enums.SkyCategory;
import com.weatherfairy.weatherwearback.common.enums.TempCategory;
import com.weatherfairy.weatherwearback.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    Page<Post> findAllByMemberNo(Long memberNo, Pageable pageable);
    List<Post> findAllByMemberNo(Long memberNo);

    @Query("SELECT p FROM Post p WHERE p.weatherDataVO.sky = :skyCategory " +
            "AND p.weatherDataVO.tempCategory = :tempCategory " +
            "AND p.memberNo = :memberNo " +
            "ORDER BY p.date DESC LIMIT 3")
    List<Post> findRecentPostsBySkyAndTempCategory(@Param("memberNo") Long memberNo,
                                                   @Param("skyCategory") SkyCategory skyCategory,
                                                   @Param("tempCategory") TempCategory tempCategory);
}
