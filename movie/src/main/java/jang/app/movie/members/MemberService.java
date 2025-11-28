package jang.app.movie.members;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberDTO memberDTO) {
        log.info("createMember 메서드 시작");
        MemberEntity memberEntity = memberDTO.toEntity();
        memberRepository.save(memberEntity);
    }

    @Transactional
    public boolean loginMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberDTO.toEntity();
        String inputPassword = memberEntity.getMemberPassword();

        Optional<MemberEntity> member = memberRepository.findByLoginId(memberEntity.getLoginId());
        log.info("===== 디비에 있는 값 member : {} ",member.get().getMemberPassword());

        // isPresent() : 객체가 있느냐 없느냐 확인하는 메서드
        // 반환 값 : boolean -> true, false
        if (member.isPresent()) {
            log.info("============= 서비스 잘 들어옴 ==========");
            MemberEntity foundMember = member.get();
            String dbPassword = foundMember.getMemberPassword();
            return dbPassword.equals(inputPassword);
        }
        log.info("============= 서비스 잘 안들어옴 ==========");
        return false;
    }
}

