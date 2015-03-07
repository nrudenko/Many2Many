package com.github.nridenko.likeorm.sample.manytomany;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.github.nridenko.likeorm.sample.manytomany.module.Company;
import com.github.nridenko.likeorm.sample.manytomany.module.CompanyEmployee;
import com.github.nridenko.likeorm.sample.manytomany.module.Employee;
import com.github.nrudenko.orm.QueryBuilder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {

        new QueryBuilder<Employee>(this).table(Employee.class).delete();
        new QueryBuilder<Company>(this).table(Company.class).delete();
        new QueryBuilder<CompanyEmployee>(this).table(CompanyEmployee.class).delete();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Jhon"));
        employees.add(new Employee(2, "Sara"));
        employees.add(new Employee(2, "Eric"));

        new QueryBuilder<Employee>(this).table(Employee.class).insert(employees);

        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Goolge"));
        companies.add(new Company(2, "Apple"));
        companies.add(new Company(2, "Microsoft"));

        new QueryBuilder<Company>(this).table(Company.class).insert(companies);

        List<CompanyEmployee> companyEmployees = new ArrayList<>();

        companyEmployees.add(new CompanyEmployee(1,1));
        companyEmployees.add(new CompanyEmployee(1,2));
        companyEmployees.add(new CompanyEmployee(2,2));
        companyEmployees.add(new CompanyEmployee(3,3));

        new QueryBuilder<CompanyEmployee>(this).table(CompanyEmployee.class).insert(companyEmployees);
    }



}
