package com.example.spring_test.bussiness.concreate;

import com.example.spring_test.bussiness.abstracts.IResellerService;
import com.example.spring_test.bussiness.abstracts.IRoleService;
import com.example.spring_test.entity.Reseller;
import com.example.spring_test.entity.Role;
import com.example.spring_test.repository.IResellerRepository;
import com.example.spring_test.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    private IRoleRepository roleRepository;

    @Autowired
    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> getById(Integer id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Role add(Role role) {
        role.setId(null);
        return this.roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Page<Role> getAll(PageRequest of) {
        System.out.println(of);
        return this.roleRepository.findAll(of);
    }


}
