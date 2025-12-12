package jang.app.movie.review;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.movies.MovieEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {

    private Long memberId;
    private Long movieId;
    private String content;

    public ReviewEntity toEntity(MemberEntity member, MovieEntity movie) {
        return ReviewEntity.builder()
                .reviewContent(this.content)
                .memberEntity(member)
                .movieEntity(movie)
                .build();
    }
}
