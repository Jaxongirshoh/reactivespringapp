package dev.wisespirit.reactivespringapp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseAuditable{
    private Long id;
    private String name;
    private Long parentId;

}
