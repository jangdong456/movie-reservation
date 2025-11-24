package jang.app.movie.members;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberDTO memberDTO) {
        log.info("서비스 시작");
        MemberEntity memberEntity = memberDTO.toEntity();
        log.info("값 확인 : {}" ,memberEntity.getMemberName());
        memberRepository.save(memberEntity);
    }
}

