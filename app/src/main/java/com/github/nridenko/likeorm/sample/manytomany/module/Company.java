package com.github.nridenko.likeorm.sample.manytomany.module;

import com.github.nrudenko.orm.annotation.Table;

@Table
public class Company {
    public int id;
    public String name;

    public Company() {
    }

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
