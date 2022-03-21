package com.example.spring_test.bussiness.abstracts;

import com.example.spring_test.entity.Reseller;
import com.example.spring_test.entity.Role;
import com.example.spring_test.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> getById(Integer id);
    Role add(Role role);
    Role update(Role role);
    Page<Role> getAll(PageRequest of);
}
