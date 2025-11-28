package jang.app.movie.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {

    private String title;

    @JsonProperty("poster_path")
    private String posterPath;
    private String overview;


    // DTO -> Entity 전환
    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .title(this.title)
                .posterPath(this.posterPath)
                .overview(this.overview)
                .build();
    }

    public void moviesPosterUrl(String baseUrl) {
        this.posterPath = baseUrl + posterPath;
    }
}
