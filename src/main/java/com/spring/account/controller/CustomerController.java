package com.spring.account.controller;

import com.spring.account.dto.CreateCustomerRequest;
import com.spring.account.dto.CustomerDto;
import com.spring.account.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping("/totalBalance/{customerId}")
    public ResponseEntity<BigDecimal> getCustomerTotalBalance(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomerTotalBalance(customerId));
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }


}
