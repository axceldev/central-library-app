package com.axceldev.central_library.models.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(value = "customers")
public class Customer {
    @Id
    @Column(value = "customer_id")
    private Integer customerId;
    @Column(value = "document_number")
    private String documentNumber;
    @Column(value = "first_name")
    private String firstName;
    @Column(value = "last_name")
    private String lastName;
    private String email;
    private String phone;
}
