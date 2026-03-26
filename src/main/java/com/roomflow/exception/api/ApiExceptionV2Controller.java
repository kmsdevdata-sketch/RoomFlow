package com.roomflow.exception.api;

import com.roomflow.exception.exhandler.ErrorResult;
import com.roomflow.global.exception.NotFoundException;
import com.roomflow.global.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    @GetMapping("/api2/users/{id}")
    public UserDto getUser(@PathVariable("id") String id) {

        if (id.equals("ex")) {
            throw new NotFoundException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new UserDto(id, "hello" + id);
    }


    @Data
    @AllArgsConstructor
    static class UserDto {
        private String userId;
        private String name;
    }
}
