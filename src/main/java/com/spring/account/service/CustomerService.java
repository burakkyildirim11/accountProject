package com.spring.account.service;

import com.spring.account.dto.CreateCustomerRequest;
import com.spring.account.dto.CustomerDto;
import com.spring.account.dto.CustomerDtoConverter;
import com.spring.account.exception.CustomerNotFoundException;
import com.spring.account.model.Account;
import com.spring.account.model.Customer;
import com.spring.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;
    private BigDecimal sum = new BigDecimal(0);


    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer could not find by id: " + id));
    }

    public CustomerDto getCustomerById(String customerId) {

        return converter.convertToCustomerDto(findCustomerById(customerId));
    }


    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer("", createCustomerRequest.getName(), createCustomerRequest.getSurname(), new HashSet<>());

        return converter.convertToCustomerDto(customerRepository.save(customer));

    }

    public BigDecimal getCustomerTotalBalance(String customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        Set<Account> accounts = customer.getAccounts();
        for (Account ac : accounts) {
            sum = sum.add(ac.getBalance());
        }
        return sum;
    }

}
