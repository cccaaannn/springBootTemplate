package com.example.spring_test.controller;

import com.example.spring_test.bussiness.abstracts.IResellerService;
import com.example.spring_test.entity.Reseller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Reseller")
public class ResellerController {
    private IResellerService resellerService;

    @Autowired
    public ResellerController(IResellerService resellerService) {
        this.resellerService = resellerService;
    }

    @GetMapping("/getAll/{page}/{search}")
    public ResponseEntity<Page<Reseller>> getAll(@PathVariable Integer page){
        System.out.println("vvvv");
        try{
        return new ResponseEntity<>(resellerService.getAll(PageRequest.of(Integer.parseInt(page.toString()) - 1, 10)),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getById/{resellerId}")
    public Optional<Reseller> getById(@PathVariable Integer resellerId) {
        return resellerService.getById(resellerId);
    }

    @PostMapping("/add")
    public Reseller add(@RequestBody Reseller reseller){
        return resellerService.add(reseller);
    }

    @PutMapping("/update")
    public Reseller update(@RequestBody Reseller reseller){
        return resellerService.update(reseller);
    }

    @GetMapping("/getBySearch/{page}/{size}/{filter}/{field}")
    public ResponseEntity<Page<List<Reseller>>> searchReseller(@PathVariable String filter, @PathVariable Integer size, @PathVariable Integer page, @PathVariable String field){
        try{
            return new ResponseEntity<>(resellerService.searchReseller(page, size, filter, field),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
//    @GetMapping("/getBySearch/{page}/{size}")
//    public ResponseEntity<Page<List<Reseller>>> searchReseller(@PathVariable String filter, @PathVariable Integer size, @PathVariable Integer page, @PathVariable String field){
//        try{
//            return new ResponseEntity<>(resellerService.searchReseller(page, size, filter, field),HttpStatus.OK);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//
//    }
}
