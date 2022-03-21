package com.example.spring_test.bussiness.abstracts;

import com.example.spring_test.entity.Reseller;
import com.example.spring_test.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface IUserService {
    Optional<User> getById(Integer id);
    User add(User user);
    User update(User user);
    Page<User> getAll(PageRequest of);
    User getByName(String name);
}
