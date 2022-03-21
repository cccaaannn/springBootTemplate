package com.example.spring_test.repository;

import com.example.spring_test.entity.Reseller;
import com.example.spring_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User getByName(@Param("name") String name);
}
