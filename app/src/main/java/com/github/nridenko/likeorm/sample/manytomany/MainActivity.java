package com.github.nridenko.likeorm.sample.manytomany;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.nridenko.likeorm.sample.manytomany.adapter.CompaniesAdapter;
import com.github.nridenko.likeorm.sample.manytomany.module.Company;
import com.github.nridenko.likeorm.sample.manytomany.module.CompanyEmployee;
import com.github.nridenko.likeorm.sample.manytomany.module.Employee;
import com.github.nrudenko.orm.QueryBuilder;
import com.github.nrudenko.orm.QueryCursorLoader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView list;
    private CompaniesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        adapter = new CompaniesAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
                intent.putExtra(CompanyActivity.KEY_INNER_COMPANY_ID, id);
                startActivity(intent);
            }
        });
        getSupportLoaderManager().initLoader(0, null, this);
        initData();
    }

    private void initData() {

        new QueryBuilder<Employee>(this).table(Employee.class).delete();
        new QueryBuilder<Company>(this).table(Company.class).delete();
        new QueryBuilder<CompanyEmployee>(this).table(CompanyEmployee.class).delete();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Jhon"));
        employees.add(new Employee(2, "Sara"));
        employees.add(new Employee(3, "Eric"));

        new QueryBuilder<Employee>(this).table(Employee.class).insert(employees);

        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Goolge"));
        companies.add(new Company(2, "Apple"));
        companies.add(new Company(3, "Microsoft"));

        new QueryBuilder<Company>(this).table(Company.class).insert(companies);

        List<CompanyEmployee> companyEmployees = new ArrayList<>();

        companyEmployees.add(new CompanyEmployee(1, 1));
        companyEmployees.add(new CompanyEmployee(1, 2));
        companyEmployees.add(new CompanyEmployee(2, 2));
        companyEmployees.add(new CompanyEmployee(3, 3));

        new QueryBuilder<CompanyEmployee>(this).table(CompanyEmployee.class).insert(companyEmployees);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        QueryBuilder builder = new QueryBuilder(this)
                .table(Company.class);
        return new QueryCursorLoader(this, builder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
