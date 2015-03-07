package com.github.nridenko.likeorm.sample.manytomany;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.github.nridenko.likeorm.sample.manytomany.adapter.EmployeesAdapter;
import com.github.nridenko.likeorm.sample.manytomany.db.schema.CompanyEmployeeScheme;
import com.github.nridenko.likeorm.sample.manytomany.db.schema.CompanyScheme;
import com.github.nridenko.likeorm.sample.manytomany.db.schema.EmployeeScheme;
import com.github.nridenko.likeorm.sample.manytomany.module.Company;
import com.github.nridenko.likeorm.sample.manytomany.module.CompanyEmployee;
import com.github.nridenko.likeorm.sample.manytomany.module.Employee;
import com.github.nrudenko.orm.CursorUtil;
import com.github.nrudenko.orm.QueryBuilder;
import com.github.nrudenko.orm.QueryCursorLoader;
import com.github.nrudenko.orm.sql.TableJoin;


public class CompanyActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView companyTitle;
    private ListView list;
    private EmployeesAdapter adapter;

    public static String KEY_INNER_COMPANY_ID = "key_inner_company_id";

    final int COMPANY_QUERY_ID = 1;
    final int EMPLOYEES_QUERY_ID = 2;
    private int companyId;
    private int innerCompanyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        companyTitle = (TextView) findViewById(R.id.company_title);
        list = (ListView) findViewById(R.id.list);

        innerCompanyId = (int) getIntent().getLongExtra(KEY_INNER_COMPANY_ID, -1l);
        if (innerCompanyId < 0) {
            finish();
        } else {
            getSupportLoaderManager().initLoader(COMPANY_QUERY_ID, null, CompanyActivity.this);
            adapter = new EmployeesAdapter(this);
            list.setAdapter(adapter);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        switch (id) {
            case COMPANY_QUERY_ID:
                QueryBuilder builder
                        = new QueryBuilder(this)
                        .table(Company.class)
                        .where(CompanyScheme._ID).is(innerCompanyId);
                loader = new QueryCursorLoader(this, builder);
                break;
            case EMPLOYEES_QUERY_ID:
                builder
                        = new QueryBuilder(this)
                        .table(CompanyEmployee.class,
                                new TableJoin(Employee.class,
                                        EmployeeScheme.ID,
                                        CompanyEmployeeScheme.EMPLOYEE_ID))
                        .projection(EmployeeScheme._ID, EmployeeScheme.ID, EmployeeScheme.NAME)
                        .where(CompanyEmployeeScheme.COMPANY_ID).is(companyId);
                loader = new QueryCursorLoader(this, builder);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case COMPANY_QUERY_ID:
                cursor.moveToFirst();
                Company company = CursorUtil.cursorToObject(cursor, Company.class);
                companyId = company.id;
                companyTitle.setText(company.name);
                getSupportLoaderManager().initLoader(EMPLOYEES_QUERY_ID, null, CompanyActivity.this);
                break;
            case EMPLOYEES_QUERY_ID:
                adapter.changeCursor(cursor);
                break;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
