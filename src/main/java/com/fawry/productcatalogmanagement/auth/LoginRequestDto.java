package com.fawry.productcatalogmanagement.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    private String username;
    private String password;
}
