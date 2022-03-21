package com.example.spring_test.bussiness.abstracts;

import com.example.spring_test.entity.Reseller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IResellerService {
    Optional<Reseller> getById(Integer id);
    Reseller add(Reseller reseller);
    Reseller update(Reseller reseller);
    Page<Reseller> getAll(PageRequest of);
    Page<List<Reseller>> searchReseller(Integer page, Integer size, String filter, String field);
}
