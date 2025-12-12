package jang.app.movie.review;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.members.MemberRepository;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.movies.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MovieRepository movieRepository;

    public void createReview(ReviewDTO dto) {
        MemberEntity member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + dto.getMemberId()));
        MovieEntity movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + dto.getMovieId()));

        ReviewEntity reviewEntity = dto.toEntity(member, movie);

        reviewRepository.save(reviewEntity);
    }


}
