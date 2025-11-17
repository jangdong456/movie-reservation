package jang.app.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;

    @Transactional
    public Home test(String username) {
        System.out.println("=== Serviec ===");
        Home home = new Home();
        home.setUsername(username);

        Home saveHome = homeRepository.save(home);
        return saveHome;
    }

    public List<Home> findAllUsers() {
        return homeRepository.findAll();
    }
}
