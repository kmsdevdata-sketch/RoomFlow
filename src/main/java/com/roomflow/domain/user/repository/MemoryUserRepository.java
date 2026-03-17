package com.roomflow.domain.user.repository;

import com.roomflow.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemoryUserRepository implements UserRepository{
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

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }



    public Optional<User> findByLoginId(String loginId) {
        return  findAll().stream()
                    .filter(u -> u.getLoginId().equals(loginId))
                    .findFirst();

    }

    //테스트에서 사용하기 위한 메서드
    public void clearStore() {
        store.clear();
    }
}
