package com.axceldev.central_library.models.dto.rq;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerRq {
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
