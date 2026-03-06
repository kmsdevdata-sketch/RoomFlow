package com.roomflow.repository;

import com.roomflow.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class UserRepository {
    private final Map<Long, User> store = new HashMap<>();
    private Long sequence = 0L;
    // Repo에서 시퀀스로 아이디를 설정해주는 이유는
    // 도메인에서 아이디를 세팅할경우 책임경계 분리를 위해서
    // DB의 기능(생성 전략 방식)에 의존하여서

    //테스트시에 사용하기 위해 우선 User반환하도록 변경
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        log.info("save user={}",user);
        return user;
    }

    public User findById(Long id) {
        // 3/4 User를 그대로 반환하면 좋지않을것 같지만 정확히 나은 설계 대체를 알지못하여 우선은 그냥 반환
        // 3/6 login과 그외서비스등등 개발하면서 아이디를 기준반환값을 사용할경우가 많아 아직 냅둠
        // 지금 생각으로는 DTO?를 만들어서 넘기는게 좋지않을까 싶음 근데 아직도 필요성은 못느껴 냅두기
        return store.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }


    //테스트에서 사용하기 위한 메서드
    public void clearStore() {
        store.clear();
    }

    public Optional<User> findByLoginId(String loginId) {
        return  findAll().stream()
                    .filter(u -> u.getLoginId().equals(loginId))
                    .findFirst();

    }

}
