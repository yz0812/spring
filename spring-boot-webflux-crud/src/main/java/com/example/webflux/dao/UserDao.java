package com.example.webflux.dao;

import com.example.webflux.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ReactiveMongoRepository<User, String> {
}
