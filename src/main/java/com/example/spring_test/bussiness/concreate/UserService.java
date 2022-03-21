package com.example.spring_test.bussiness.concreate;

import com.example.spring_test.bussiness.abstracts.IResellerService;
import com.example.spring_test.bussiness.abstracts.IUserService;
import com.example.spring_test.entity.Reseller;
import com.example.spring_test.entity.User;
import com.example.spring_test.repository.IResellerRepository;
import com.example.spring_test.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User add(User user) {
        user.setId(null);
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Page<User> getAll(PageRequest of) {
        System.out.println(of);
        return this.userRepository.findAll(of);
    }

    public User getByName(String name){
        return this.userRepository.getByName(name);
    }


}
