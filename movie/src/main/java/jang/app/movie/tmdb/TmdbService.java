package jang.app.movie.tmdb;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class TmdbService {

    private final RestTemplate restTemplate;
    private final TmdbProperties tmdbProperties;

    public TmdbService(RestTemplate restTemplate, TmdbProperties tmdbProperties) {
        this.restTemplate = restTemplate;
        this.tmdbProperties = tmdbProperties;
    }

    public TmdbResponseDTO getNowPlayingMovies() {
        // 1. 요청 URL 구성 (쿼리 파라미터 추가)
        String url = tmdbProperties.getBaseUrl() + "/movie/now_playing";

        String requestUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("language", "ko-KR")
                .queryParam("page",1)
                .toUriString();

        //2.HTTP 헤더 설정 (Access Token을 Bearer 토큰으로 설정)
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(tmdbProperties.getAccessToken());
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        // 3.요청 Entity 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 4. RestTemplate 호출
        try {
            ResponseEntity<TmdbResponseDTO> response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    entity,
                    TmdbResponseDTO.class
            );

            // 응답 상태 코드 출력
            System.out.println("TMDB API 응답 상태 코드: " + response.getStatusCode());
            if (response.getStatusCode().is2xxSuccessful()) {
                TmdbResponseDTO body = response.getBody();

                // ✨ 2. 응답 본문 데이터 존재 여부 로그 출력
                if (body != null && body.getResults() != null) {
                    System.out.println("영화 데이터 수: " + body.getResults().size());
                } else {
                    System.out.println("경고: 응답 본문(Body)이 비어 있거나 'results' 목록이 비어 있습니다.");
                }
                return body;
            }
        } catch (Exception e) {
            //오류 처리 로직
            System.err.println("TMDB API 호출 중 오류 발생 : " + e.getMessage());
        }

        return null;
    }
}
