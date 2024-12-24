package dev.wisespirit.reactivespringapp.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
//for Cassandra
//import org.springframework.data.cassandra.core.mapping.Table;
import java.math.BigDecimal;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
//for Cassandra database config
//@Table("products")
@Table("products")
public class Product extends BaseAuditable{
    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
