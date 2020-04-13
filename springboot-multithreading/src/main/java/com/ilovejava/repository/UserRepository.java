package com.ilovejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilovejava.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
