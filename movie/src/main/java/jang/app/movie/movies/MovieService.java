package jang.app.movie.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tmdb.api-key}")
    private String apiKey;

    @Value("${tmdb.base-url}")
    private String baseUrl;

    public List<MovieDTO> getMovieData() {
        // 1. 요청 url 구성
        String url = baseUrl + "/movie/now_playing";
        String requestUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("api_key", apiKey)
                .queryParam("language", "ko-KR")
                .queryParam("page", 1)
                .encode()
                .toUriString();

        // 2. get으로 요청해서 데이터 담기
        TmdbResponseDTO response = restTemplate.getForObject(requestUrl, TmdbResponseDTO.class);

        // 3. 원하는 데이터만 추출하기
        if (response != null && response.getResults() != null) {
            // getResults() : 리스트를 추출하는 메서드
            List<MovieDTO> results = response.getResults();
            log.info("=== API 호출 성공 총 영화 수 : {} 개 ===", results);
            if (!results.isEmpty()) {
                MovieDTO firstMovie = results.get(0);
                log.info("   첫 번째 영화 제목: {}", firstMovie.getTitle());
                log.info("   첫 번째 영화 포스터 경로: {}", firstMovie.getPosterPath());
            }
            return results;
        }

        log.warn("응답이 없거나 목록이 비어있습니다.");
        return List.of();
    }
}
