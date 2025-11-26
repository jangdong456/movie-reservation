package jang.app.movie.tmdb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "tmdb")
public class TmdbProperties {

    private String apiKey;
    private String accessToken;
    private String baseUrl;
}
