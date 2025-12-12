package jang.app.movie.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@Slf4j
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/test")
    public void createReview() {
        ReviewDTO dto = ReviewDTO.builder()
                .memberId(1L)
                .movieId(1L)
                .content("영화 재밌어요!")
                .build();

        reviewService.createReview(dto);
    }
}
