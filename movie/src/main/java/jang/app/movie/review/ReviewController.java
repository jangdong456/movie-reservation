package jang.app.movie.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("find")
    public void findReview() {
        ReviewDTO dto = ReviewDTO.builder()
                .memberId(1L)
                .movieId(1L)
                .build();

        reviewService.findReview(dto);
    }
}
