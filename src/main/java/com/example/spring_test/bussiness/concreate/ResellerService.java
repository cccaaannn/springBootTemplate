package com.example.spring_test.bussiness.concreate;

import com.example.spring_test.bussiness.abstracts.IResellerService;
import com.example.spring_test.entity.Reseller;
import com.example.spring_test.repository.IResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResellerService implements IResellerService {

    private IResellerRepository iResellerRepository;

    @Autowired
    public ResellerService(IResellerRepository iResellerRepository) {
        this.iResellerRepository = iResellerRepository;
    }

    @Override
    public Optional<Reseller> getById(Integer id) {
        return this.iResellerRepository.findById(id);
    }

    @Override
    public Reseller add(Reseller reseller) {
        reseller.setId(null);
        return this.iResellerRepository.save(reseller);
    }

    @Override
    public Reseller update(Reseller reseller) {
        return this.iResellerRepository.save(reseller);
    }

    @Override
    public Page<Reseller> getAll(PageRequest of) {
        System.out.println(of);
        return this.iResellerRepository.findAll(of);
    }

    @Override
    public Page<List<Reseller>> searchReseller(Integer page, Integer size,String filter,String field) {
        return this.iResellerRepository.searchReseller(filter, PageRequest.of(page - 1, size).withSort(Sort.by(field)));
    }

//    @Override
//    public Reseller getBySearch(String search) {
//        return this.iResellerRepository.getResellerByNameAndAndAddressAndMsisdnAndPrimaryContactAndAndContactEmailAndStatus(search);
//    }


}
