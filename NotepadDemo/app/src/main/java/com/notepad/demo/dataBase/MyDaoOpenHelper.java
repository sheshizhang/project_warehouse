package com.notepad.demo.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.notepad.demo.greendao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by feiran.zhang on 2018/4/30.
 */

public class MyDaoOpenHelper extends DaoMaster.OpenHelper{
    public MyDaoOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        //升级数据库
    }
}
