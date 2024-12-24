package dev.wisespirit.reactivespringapp.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
//for Cassandra
//import org.springframework.data.cassandra.core.mapping.Table;


@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
//for Cassandra database config
//@Table("categories")
@Table("categories")
public class Category extends BaseAuditable{
    @Id
    private Long id;
    private String name;
    private Long parentId;

    public Category(String name,Long parentId){
        this.name = name;
        this.parentId = parentId;
    }
}
