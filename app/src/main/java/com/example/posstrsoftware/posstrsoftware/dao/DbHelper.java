package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String databaseName = "STRPOS.Sqlite";
    private static final int databaseVerSion = 1;
    Context mContect;


    private static final String tableUnitCreateSQL = "CREATE TABLE unit_list("
            +"id_unit INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"unit_text TEXT NOT NULL,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";

    private static final String tableGroupCreateSQL = "CREATE TABLE group_list("
            +"id_group INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"group_text TEXT NOT NULL,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";

    private static final String tableProductCreateSQL = "CREATE TABLE product_list("
            +"id_product INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"product_text TEXT NOT NULL,"
            +"id_unit INTEGER,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";





    public DbHelper(Context context) {
        super(context,databaseName,null,databaseVerSion );
        this.mContect = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUnitCreateSQL);
        db.execSQL(tableProductCreateSQL);
        db.execSQL(tableGroupCreateSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
