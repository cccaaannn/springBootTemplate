package com.example.spring_test.repository;

import com.example.spring_test.entity.Reseller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IResellerRepository extends JpaRepository<Reseller,Integer> {
    @Query(value = "FROM Reseller where name like %:filter% or primaryContact like %:filter% or contactEmail like %:filter%",countQuery = "SELECT COUNT(*) FROM Reseller where name like %:filter% or primaryContact like %:filter% or contactEmail like %:filter%")
    Page<List<Reseller>> searchReseller(@Param("filter") String filter, Pageable of);
}
