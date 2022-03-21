package com.example.spring_test.bussiness.abstracts;

import com.example.spring_test.dto.LoginDTO;
import com.example.spring_test.entity.Reseller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ILoginService {
    String login(LoginDTO loginDTO);
}
