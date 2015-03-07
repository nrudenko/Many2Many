package com.github.nridenko.likeorm.sample.manytomany.module;

import com.github.nrudenko.orm.annotation.Table;

@Table
public class Employee {
    public int id;
    public String name;

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
