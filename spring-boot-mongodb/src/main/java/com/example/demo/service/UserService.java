package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final MongoTemplate template;
    public List<User> getUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUser(String id) {
        return this.userDao.findById(id);
    }

    /**
     * 新增和修改都是 save方法，
     * id 存在为修改，id 不存在为新增
     */
    public User createUser(User user) {
        user.setId(null);
        return userDao.save(user);
    }

    public void deleteUser(String id) {
        this.userDao.findById(id)
                .ifPresent(user -> this.userDao.delete(user));
    }

    public void updateUser(String id, User user) {
        this.userDao.findById(id)
            .ifPresent(
                u -> {
                    u.setName(user.getName());
                    u.setAge(user.getAge());
                    u.setDescription(user.getDescription());
                    this.userDao.save(u);
                }
            );
    }

    public List<User> findByAgeBetween(Integer from, Integer to){
        return this.userDao.findByAgeBetween(from,to);
    }

    public   List<User> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer from, Integer to, String name, String description){
        return this.userDao.findByAgeBetweenAndNameEqualsAndDescriptionIsLike(from,to,name,description);
    }

    public Page<User> getUserByCondition(int size, int page, User user) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        //根据名字查找
        if (!StringUtils.isEmpty(user.getName())) {
            criteria.and("name").is(user.getName());
        }
        //根据描述查找
        if (!StringUtils.isEmpty(user.getDescription())) {
            criteria.and("description").regex(user.getDescription());
        }

        query.addCriteria(criteria);
        //根据年龄降序
        Sort sort =Sort.by(Sort.Direction.DESC,"age");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<User> users = template.find(query.with(pageable), User.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> template.count(query, User.class));

    }
}
