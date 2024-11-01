package com.fawry.productcatalogmanagement.dao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private int id;
    private String name;
    private double price;
}
