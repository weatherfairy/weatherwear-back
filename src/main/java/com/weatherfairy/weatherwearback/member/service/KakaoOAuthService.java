package com.weatherfairy.weatherwearback.member.service;

import com.weatherfairy.weatherwearback.common.auth.AccessTokenInfo;
import com.weatherfairy.weatherwearback.common.auth.JwtTokenProvider;
import com.weatherfairy.weatherwearback.common.auth.KakaoProfileResponse;
import com.weatherfairy.weatherwearback.common.auth.RefreshTokenInfo;
import com.weatherfairy.weatherwearback.member.entity.Member;
import com.weatherfairy.weatherwearback.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoOAuthService {

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.current-version-uri}")
    private String currentVersionUri;

    @Value("${kakao.admin-key}")
    private String adminKey;

    private final RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public String getKakaoUri() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("client_id", kakaoClientId)
                .queryParam("redirect_uri", currentVersionUri + kakaoRedirectUri)
                .queryParam("response_type", "code");

        return builder.toUriString();
    }


    @Transactional
    public Map<String, String> kakaoOauth(String code) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", currentVersionUri + kakaoRedirectUri);
        params.add("code", code);

        String accessTokenRequestUrl = "https://kauth.kakao.com/oauth/token";
        Map<String, String> response = restTemplate.postForObject(accessTokenRequestUrl, params, Map.class);

        String kakaoAccessToken = response != null ? response.get("access_token") : null;
        if (kakaoAccessToken == null) {
            throw new IllegalArgumentException();
        }

        KakaoProfileResponse kakaoProfileResponse = getKakaoProfile(kakaoAccessToken);
        if (kakaoProfileResponse == null) {
            throw new IllegalArgumentException();
        }
        Optional<Member> optionalMember = memberRepository.findByKakaoId(kakaoProfileResponse.getKakaoId().toString());

        return optionalMember.map(this::loginWithKakao).orElseGet(() -> loginWithKakaoCreateMember(kakaoProfileResponse));
    }


    KakaoProfileResponse getKakaoProfile(String kakaoAccessToken){
        // user 정보를 가져오는 kakao api url
        String url = "https://kapi.kakao.com/v2/user/me";

        // http header 설정 : access token 을 넣어서 user 정보에 접근할 수 있도록 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + kakaoAccessToken);

        // 필요한 사용자 정보 가져오기
        MultiValueMapAdapter<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"id\", \"kakao_account.email\", \"properties.nickname\"]");

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(url, request, KakaoProfileResponse.class);
    }


    private Map<String, String> loginWithKakao(Member member) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo(member.getMemberNo());
        RefreshTokenInfo refreshTokenInfo = new RefreshTokenInfo(member.getKakaoId());

        String refreshToken = jwtTokenProvider.createRefreshToken(refreshTokenInfo);
        String accessToken = jwtTokenProvider.createAccessToken(accessTokenInfo);

        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }


    private Map<String, String> loginWithKakaoCreateMember(KakaoProfileResponse kakaoProfile) {
        Member member = Member.builder()
                .kakaoId(kakaoProfile.getKakaoId().toString())
                .kakaoName(kakaoProfile.getNickname())
                .build();

        // 리프레시 토큰 생성
        RefreshTokenInfo refreshTokenInfo = new RefreshTokenInfo(member.getKakaoId());
        String refreshToken = jwtTokenProvider.createRefreshToken(refreshTokenInfo);

        member.updateRefreshToken(refreshToken);

        // 멤버 저장, 액세스 토큰 생성
        Member savedMember = memberRepository.saveAndFlush(member);
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo(savedMember.getMemberNo());
        String accessToken = jwtTokenProvider.createAccessToken(accessTokenInfo);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    @Transactional
    public boolean unlinkAndDeleteMember(Long memberNo) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(EntityNotFoundException::new);

        // 연결을 끊는 kakao api url
        String url = "https://kapi.kakao.com/v1/user/unlink";

        // http header 설정 : access token 을 넣어서 user 정보에 접근할 수 있도록 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK " + adminKey);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("target_id_type", "user_id");
        body.add("target_id", member.getKakaoId());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =  restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        memberRepository.delete(member);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        return true;
    }
}
