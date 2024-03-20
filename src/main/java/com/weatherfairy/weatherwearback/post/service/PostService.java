package com.weatherfairy.weatherwearback.post.service;

import com.weatherfairy.weatherwearback.post.dto.response.GetPostsResponse;
import com.weatherfairy.weatherwearback.post.entity.Post;
import com.weatherfairy.weatherwearback.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<GetPostsResponse> getPostsScroll(Pageable pageable, Long memberNo) {

        Page<Post> postsList = postRepository.findAllByMemberId(memberNo, pageable);

        return postsList.map(GetPostsResponse::from);
    }
}
