package com.github.nridenko.likeorm.sample.manytomany.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.github.nridenko.likeorm.sample.manytomany.module.Company;
import com.github.nrudenko.orm.CursorUtil;

import static com.github.nrudenko.orm.CursorUtil.isCursorEmpty;

public class CompaniesAdapter extends CursorAdapter {
    public CompaniesAdapter(Context context) {
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
            Company company = CursorUtil.cursorToObject(cursor, Company.class);
            text.setText(company.id + " " + company.name);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}