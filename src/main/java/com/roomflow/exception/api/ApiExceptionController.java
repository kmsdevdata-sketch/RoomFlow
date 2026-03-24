package com.roomflow.exception.api;

import com.roomflow.global.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/users/{id}")
    public UserDto getUser(@PathVariable("id") String id) {

        if (id.equals("ex")) {
            throw new NotFoundException("잘못된 사용자");
        }

        return new UserDto(id,"hello" + id);
    }

    @Data
    @AllArgsConstructor
    static class UserDto {
        private String userId;
        private String name;
    }
}
