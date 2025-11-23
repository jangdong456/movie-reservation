package jang.app.movie.Home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final HomeRepository homeRepository;

    public List<Home> findAllUsers() {
        return homeRepository.findAll();
    }

    @Transactional
    public void createMember(HomeDTO homeDTO) {
        Home newHome = homeDTO.toEntity();

        homeRepository.save(newHome);
    }
}
