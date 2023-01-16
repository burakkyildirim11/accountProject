package com.spring.account.service;

import com.spring.account.dto.CustomerDto;
import com.spring.account.dto.CustomerDtoConverter;
import com.spring.account.exception.CustomerNotFoundException;
import com.spring.account.model.Customer;
import com.spring.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer could not find by id: "+id));
    }

    public CustomerDto getCustomerById(String customerId){

        return converter.convertToCustomerDto(findCustomerById(customerId));
    }

}
