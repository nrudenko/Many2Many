package com.github.nridenko.likeorm.sample.manytomany.module;

import com.github.nrudenko.orm.annotation.Table;

@Table
public class Employee {
    public final int id;
    public final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
