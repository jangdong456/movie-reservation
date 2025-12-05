package jang.app.movie.movies;

import jang.app.movie.cinemas.CinemaDTO;
import jang.app.movie.cinemas.CinemaEntity;
import jang.app.movie.cinemas.CinemaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private CinemaRepository cinemaRepository;
    private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;

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

        String imageBaseUrl = "https://image.tmdb.org/t/p/w500";
        // 3. 원하는 데이터만 추출하기
        if (response != null && response.getResults() != null) {
            // getResults() : 리스트를 추출하는 메서드
            List<MovieDTO> results = response.getResults();

            if (!results.isEmpty()) {
                for (MovieDTO result : results) {
                    result.moviesPosterUrl(imageBaseUrl);
                    MovieEntity movieEntity = result.toEntity();

                    movieRepository.save(movieEntity);
                }
            }
            return results;
        }
        log.warn("응답이 없거나 목록이 비어있습니다.");
        return List.of();
    }

    public List<MovieDTO> getIndexPage() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            MovieDTO dto = entity.toDTO();
            dtos.add(dto);
        }

        return dtos;
    }

    public List<CinemaDTO> getCinemaName() {
        List<CinemaEntity> entity = cinemaRepository.findAll();
        List<CinemaDTO> dtos = new ArrayList<>();

        for (CinemaEntity cinemaEntity : entity) {
            CinemaDTO dto = cinemaEntity.toDTO();
            dtos.add(dto);
        }
        return dtos;
    }
}
