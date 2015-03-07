package com.github.nridenko.likeorm.sample.manytomany.db;

import com.github.nrudenko.orm.LikeOrmContentProvider;
import com.github.nrudenko.orm.LikeOrmSQLiteOpenHelper;

public class M2MContentProvider extends LikeOrmContentProvider {
    @Override
    public LikeOrmSQLiteOpenHelper getDbHelper() {
        return new M2MDatabaseHelper(getContext());
    }
}
