package com.weatherfairy.weatherwearback.post.repository;

import com.weatherfairy.weatherwearback.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    List<Post> getFilteredPosts(Map<String, Object> filters);
}
