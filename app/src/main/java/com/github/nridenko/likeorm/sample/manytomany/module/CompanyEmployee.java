package com.github.nridenko.likeorm.sample.manytomany.module;

import com.github.nrudenko.orm.annotation.Table;

@Table
public class CompanyEmployee {
    public final int companyId;
    public final int employeeId;

    public CompanyEmployee(int companyId, int employeeId) {
        this.companyId = companyId;
        this.employeeId = employeeId;
    }
}
