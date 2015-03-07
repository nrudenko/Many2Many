package com.github.nridenko.likeorm.sample.manytomany.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.nridenko.likeorm.sample.manytomany.module.Company;
import com.github.nridenko.likeorm.sample.manytomany.module.CompanyEmployee;
import com.github.nridenko.likeorm.sample.manytomany.module.Employee;
import com.github.nrudenko.orm.LikeOrmSQLiteOpenHelper;

import java.util.List;

public class M2MDatabaseHelper extends LikeOrmSQLiteOpenHelper {
    private static final int version = 1;
    private static final String name = "database";

    public M2MDatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    protected void appendSchemes(List<Class> classes) {
        classes.add(Company.class);
        classes.add(Employee.class);
        classes.add(CompanyEmployee.class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
    }
}
