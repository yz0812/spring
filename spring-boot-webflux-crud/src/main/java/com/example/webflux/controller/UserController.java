package com.example.webflux.controller;

import com.example.webflux.entity.User;
import com.example.webflux.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 以数组的形式一次性返回所有数据
     */
    @GetMapping
    public Flux<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * 以 Server sent events形式多次返回数据
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsersStream() {
        return userService.getUsers();
    }

    @PostMapping
    public Mono<User> createUser(User user) {
        return userService.createUser(user);
    }

    /**
     * 存在返回 200，不存在返回 404
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 存在返回修改后的 User
     * 不存在返回 404
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable String id, User user) {
        return userService.updateUser(id, user)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据用户 id查找
     * 存在返回，不存在返回 404
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable String id) {
        return userService.getUser(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 分页
     * @param size
     * @param page
     * @param user
     * @return
     */
    @GetMapping("/condition")
    public Mono<Page<User>>  getUserByCondition(int size, int page, User user) {
        return userService.getUserByCondition(user, page, size);
    }
}
