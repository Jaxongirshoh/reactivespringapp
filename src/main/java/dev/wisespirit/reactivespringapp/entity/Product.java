package dev.wisespirit.reactivespringapp.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseAuditable{
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
