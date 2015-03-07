package com.github.nridenko.likeorm.sample.manytomany.module;

import com.github.nrudenko.orm.annotation.Table;

@Table
public class CompanyEmployee {
    public int companyId;
    public int employeeId;

    public CompanyEmployee() {
    }

    public CompanyEmployee(int companyId, int employeeId) {
        this.companyId = companyId;
        this.employeeId = employeeId;
    }
}
