package com.spring.account.service;

import com.spring.account.dto.CustomerDto;
import com.spring.account.dto.CustomerDtoConverter;
import com.spring.account.exception.CustomerNotFoundException;
import com.spring.account.model.Customer;
import com.spring.account.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, converter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id","name","surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result = service.findCustomerById("id");

        Assert.assertEquals(result,customer);
    }



    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        Assert.assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));


    }


    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id","name","surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id","name","surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById("id");

        Assert.assertEquals(result,customerDto);
    }


    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        Assert.assertThrows(CustomerNotFoundException.class,
                () -> service.getCustomerById("id"));

        Mockito.verifyNoInteractions(converter);

    }

}