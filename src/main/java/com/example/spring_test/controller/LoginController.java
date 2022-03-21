package com.example.spring_test.controller;

import com.example.spring_test.bussiness.abstracts.ILoginService;
import com.example.spring_test.bussiness.abstracts.IResellerService;
import com.example.spring_test.dto.LoginDTO;
import com.example.spring_test.entity.Reseller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class LoginController {
    private ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        try {

           return new ResponseEntity<>(loginService.login(loginDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
