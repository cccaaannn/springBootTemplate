package com.example.spring_test.repository;

import com.example.spring_test.entity.Reseller;
import com.example.spring_test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {

}
