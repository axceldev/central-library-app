package com.axceldev.central_library.services;

import com.axceldev.central_library.exceptions.BusinessException;
import com.axceldev.central_library.models.model.Customer;
import com.axceldev.central_library.repositories.CustomerRepository;
import com.axceldev.central_library.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Mono<List<Customer>> findAllCustomers() {
        return customerRepository.findAll().collectList();
    }

    public Mono<Customer> findCustomerById(Integer customerId) {
        return customerRepository
                .findById(customerId)
                .switchIfEmpty(Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.CUSTOMER_NOT_FOUND)));
    }

    public Mono<Customer> findCustomerByDocumentNumber(String documentNumber) {
        return customerRepository
                .findCustomerByDocumentNumber(documentNumber)
                .switchIfEmpty(Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.CUSTOMER_NOT_FOUND)));
    }
}
