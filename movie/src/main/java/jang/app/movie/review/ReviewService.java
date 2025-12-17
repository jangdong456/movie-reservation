package jang.app.movie.review;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.members.MemberRepository;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.movies.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Object[]> findReview(ReviewDTO dto) {
//        MemberEntity member = memberRepository.findById(dto.getMemberId())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + dto.getMemberId()));
//        MovieEntity movie = movieRepository.findById(dto.getMovieId())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + dto.getMovieId()));
//
//        ReviewEntity reviewEntity = dto.toEntity(member, movie);
//
//        reviewRepository.findById(member.getMemberId());
         log.info("@@@@@@@시작@@@@@");
         List<Object[]> find = reviewRepository.findUserReviewsNative("user0000002@test.com",2024);
         log.info(">>>>>>>>>>> 결과물 : {} <<<<<<<<<<<", find);

         return find;
    }

}
