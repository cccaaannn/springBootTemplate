package com.example.spring_test.bussiness.concreate;

import com.example.spring_test.Utils.JWTToken;
import com.example.spring_test.bussiness.abstracts.ILoginService;
import com.example.spring_test.bussiness.abstracts.IUserService;
import com.example.spring_test.dto.LoginDTO;
import com.example.spring_test.entity.Role;
import com.example.spring_test.entity.User;
import com.example.spring_test.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService implements ILoginService {

    private IUserService userService;
    private JWTToken jwtToken;
    @Autowired
    public LoginService(UserService userService, JWTToken jwtToken) {
        this.userService = userService;
        this.jwtToken = jwtToken;
    }

    public String login(LoginDTO loginDTO){
        User user = userService.getByName(loginDTO.getUsername());
        if (Objects.isNull(user)) {
            throw new IllegalStateException();
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        return jwtToken.generateToken(user);
    }





}
