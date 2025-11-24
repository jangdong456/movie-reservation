package jang.app.movie;

import jang.app.movie.Home.Home;
import jang.app.movie.Home.HomeRepository;
import jang.app.movie.Home.HomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HomeServiceTest {
  @Autowired
  HomeService homeService;

  @Autowired
  HomeRepository homeRepository;

  @Test
    public void saveTest() {
      String testUsername= "JSAT_TEST";
//      Home savedHome = homeService.test(testUsername);

//      assertNotNull(savedHome.getId(), "저장 후 ID가 NULL이면 안됨");
//      assertEquals(testUsername, savedHome.getUsername(), "저장된 username이 일치해야함");
  }
}