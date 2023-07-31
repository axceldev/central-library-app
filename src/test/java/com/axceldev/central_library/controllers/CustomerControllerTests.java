package com.axceldev.central_library.controllers;
import com.axceldev.central_library.models.model.Customer;
import com.axceldev.central_library.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@WebFluxTest
public class CustomerControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetAllCustomer() {
        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setDocumentNumber("1234567890");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");
        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setDocumentNumber("9876543210");
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setEmail("jane.smith@example.com");
        List<Customer> customerList = Arrays.asList(customer1, customer2);

        Mockito.when(customerService.findAllCustomers())
                .thenReturn(Mono.just(customerList));

        webTestClient.get()
                .uri("/customer/all")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class)
                .hasSize(2)
                .contains(customer1, customer2);
    }

    @Test
    public void testGetCustomerById() {
        int customerId = 1;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setDocumentNumber("1234567890");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");;
        Mockito.when(customerService.findCustomerById(customerId))
                .thenReturn(Mono.just(customer));

        webTestClient.get()
                .uri("/customer/{customerId}", customerId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .value(returnedCustomer -> {
                    // Assert the returned customer matches the expected customer
                    Assertions.assertEquals(customer.getCustomerId(), returnedCustomer.getCustomerId());
                    Assertions.assertEquals(customer.getFirstName(), returnedCustomer.getFirstName());
                    Assertions.assertEquals(customer.getLastName(), returnedCustomer.getLastName());
                    Assertions.assertEquals(customer.getEmail(), returnedCustomer.getEmail());
                    Assertions.assertEquals(customer.getPhone(), returnedCustomer.getPhone());
                });
    }

    @Test
    public void testGetCustomerByDocumentNumber() {
        String documentNumber = "ABC123";
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setDocumentNumber(documentNumber);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");;

        Mockito.when(customerService.findCustomerByDocumentNumber(documentNumber))
                .thenReturn(Mono.just(customer));

        webTestClient.get()
                .uri("/customer/doc/{documentNumber}", documentNumber)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .value(returnedCustomer -> {
                    // Assert the returned customer matches the expected customer
                    Assertions.assertEquals(customer.getCustomerId(), returnedCustomer.getCustomerId());
                    Assertions.assertEquals(customer.getFirstName(), returnedCustomer.getFirstName());
                    Assertions.assertEquals(customer.getLastName(), returnedCustomer.getLastName());
                    Assertions.assertEquals(customer.getEmail(), returnedCustomer.getEmail());
                    Assertions.assertEquals(customer.getPhone(), returnedCustomer.getPhone());
                });
    }
}
