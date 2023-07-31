package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.model.Customer;
import com.axceldev.central_library.services.CustomerService;
import io.r2dbc.spi.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/library")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping("/customer/all")
    public Mono<List<Customer>> getAllCustomer(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public Mono<Customer> getCustomerById(@PathVariable("customerId") Integer customerId){
        return customerService.findCustomerById(customerId);
    }

    @GetMapping("/customer/doc/{documentNumber}")
    public Mono<Customer> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber){
        return customerService.findCustomerByDocumentNumber(documentNumber);
    }
}
