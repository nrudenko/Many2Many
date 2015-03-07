package com.github.nridenko.likeorm.sample.manytomany.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.github.nridenko.likeorm.sample.manytomany.module.Employee;
import com.github.nrudenko.orm.CursorUtil;

import static com.github.nrudenko.orm.CursorUtil.isCursorEmpty;

public class EmployeesAdapter extends CursorAdapter {
    public EmployeesAdapter(Context context) {
        super(context, null, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return View.inflate(context, android.R.layout.simple_list_item_1, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        if (!isCursorEmpty(cursor)) {
            Employee company = CursorUtil.cursorToObject(cursor, Employee.class);
            text.setText(company.id + " " + company.name);
        }
    }
}