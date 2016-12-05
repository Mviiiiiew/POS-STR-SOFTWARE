package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.posstrsoftware.posstrsoftware.model.SaleMasterList;

import java.util.ArrayList;

/**
 * Created by MAN on 12/4/2016.
 */

public class SaleMasterDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperSaleMaster;
    public SaleMasterDAO(Context context) {
        dbHelperSaleMaster = new DbHelper(context);
    }

    public void open() {
        database = dbHelperSaleMaster.getWritableDatabase();
    }
    public void close() {
        dbHelperSaleMaster.close();
    }



    public ArrayList<SaleMasterList> getAllSaleMasterList() {
        ArrayList<SaleMasterList> saleMasterLists = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM sale_master ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SaleMasterList saleMasterList = new SaleMasterList();

            saleMasterLists.add(saleMasterList);
            cursor.moveToNext();
        }
        cursor.close();
        return saleMasterLists;
    }








    public void addsale_master (SaleMasterList saleMasterList) {

        ContentValues values = new ContentValues();
        values.put("sale_doc_date", saleMasterList.getDate());
        values.put("discount", saleMasterList.getDiscount());
        this.database.insert("sale_master", null, values);



    }



}
