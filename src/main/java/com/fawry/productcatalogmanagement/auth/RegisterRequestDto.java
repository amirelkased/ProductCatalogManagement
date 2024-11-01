package com.fawry.productcatalogmanagement.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
