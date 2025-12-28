package com.artemisa.sandbox.sandbox.dtos;

import com.artemisa.sandbox.sandbox.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
    private Long price;
    private String name;

}
