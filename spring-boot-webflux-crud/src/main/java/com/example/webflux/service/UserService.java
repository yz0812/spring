package com.example.webflux.service;

import com.example.webflux.dao.UserDao;
import com.example.webflux.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final UserDao userDao;

    public Flux<User> getUsers() {
        return userDao.findAll();
    }

    public Mono<User> getUser(String id) {
        return this.userDao.findById(id);
    }

    public Mono<User> createUser(User user) {
        return userDao.save(user);
    }

    public Mono<Void> deleteUser(String id) {
        return this.userDao.findById(id)
                .flatMap(user -> this.userDao.delete(user));
    }

    public Mono<User> updateUser(String id, User user) {
        return this.userDao.findById(id)
            .flatMap(u -> {
                u.setName(user.getName());
                u.setAge(user.getAge());
                u.setDescription(user.getDescription());
                return this.userDao.save(u);
            });
    }

    public Mono<Page<User>> getUserByCondition(User user, Integer page, Integer size) {

        Criteria criteria = new Criteria();
        //根据名字查找
        if (!StringUtils.isEmpty(user.getName())) {
            criteria.and("name").is(user.getName());
        }
        //根据描述查找
        if (!StringUtils.isEmpty(user.getDescription())) {
            criteria.and("description").regex(user.getDescription());
        }
        //根据年龄降序
        Sort sort =Sort.by(Sort.Direction.DESC,"age");

        Pageable pageable = PageRequest.of(page, size,sort);
        Query query = new Query().with(pageable);
        query.addCriteria(criteria);
        Flux<User> chatUserFlux = reactiveMongoTemplate.find(query, User.class);
        Mono<Long> countMono = reactiveMongoTemplate.count(Query.of(query).limit(-1).skip(-1), User.class);
        return Mono.zip(chatUserFlux.collectList(),countMono).map(tuple2 -> PageableExecutionUtils.getPage(
                tuple2.getT1(),
                pageable,
                () -> tuple2.getT2()));

    }
}
