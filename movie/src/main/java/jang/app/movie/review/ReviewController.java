package jang.app.movie.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public void createReview() {
        ReviewDTO dto = ReviewDTO.builder()
                .memberId(1L)
                .movieId(1L)
                .content("영화 재밌어요!")
                .build();

        reviewService.createReview(dto);
    }

    @GetMapping("/review/find")
    public ResponseEntity<Object> findReview() {
        ReviewDTO dto = ReviewDTO.builder()
                .memberId(1L)
                .movieId(1L)
                .build();

        List<Object[]> result = reviewService.findReview(dto);
        if (result.isEmpty()) {
            log.info(">>>>>>>> 컨트롤러 값이 없어요!!!!!!! <<<<<<<<<<<");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
