package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, User user) {
        userService.updateUser(id, user);
    }

    /**
     * 根据用户 id查找
     * 存在返回，不存在返回 null
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id).orElse(null);
    }


    @GetMapping("/findByAgeBetween")
    public List<User> findByAgeBetween(Integer from, Integer to){
        return this.userService.findByAgeBetween(from,to);
    }


    @GetMapping("/findByAgeBetweenAndNameEqualsAndDescriptionIsLike")
    public   List<User> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer from, Integer to, String name, String description){
        return this.userService.findByAgeBetweenAndNameEqualsAndDescriptionIsLike(from,to,name,description);
    }

    @GetMapping("/condition")
    public Page<User> getUserByCondition(int size, int page, User user) {
        return userService.getUserByCondition(size, page, user);
    }
}
