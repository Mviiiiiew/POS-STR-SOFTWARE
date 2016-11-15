package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.posstrsoftware.posstrsoftware.model.UnitList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class UnitDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperUnit;

    public UnitDAO(Context context) {
        dbHelperUnit = new DbHelper(context);
    }

    public void open() {
        database = dbHelperUnit.getWritableDatabase();
    }

    public void close() {
        dbHelperUnit.close();
    }

    public ArrayList<UnitList> getAllUnitList() {

        ArrayList<UnitList> unitList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM unit_list where delete_flag = 'N';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UnitList mUnitList = new UnitList();
            mUnitList.setId(cursor.getInt(0));
            mUnitList.setUnitText(cursor.getString(1));
            unitList.add(mUnitList);
            cursor.moveToNext();


        }
        cursor.close();
        return unitList;
    }



    public int add(UnitList unitList) {
        String query = "Select count(*) from unit_list where unit_text = ? AND delete_flag = ?";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, unitList.getUnitText());
        stmt.bindString(2, "N");
        int count_row = (int) stmt.simpleQueryForLong();
        if (stmt != null) stmt.close();
        if (count_row != 0) {
            return 0;
        } else {
            ContentValues values = new ContentValues();
            values.put("unit_text", unitList.getUnitText());
            this.database.insert("unit_list", null, values);
            return 1;
        }


    }

    public int update(UnitList unitList) {
        String query = "Select count(*) from unit_list where unit_text = ? AND delete_flag = ?";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, unitList.getUnitText());
        stmt.bindString(2, "N");
        int count_row = (int) stmt.simpleQueryForLong();
        if (stmt != null) stmt.close();
        if (count_row != 0) {
            return 0;
        } else {
            UnitList updateUnitList = unitList;
            ContentValues values = new ContentValues();
            values.put("unit_text", updateUnitList.getUnitText());
            values.put("id_unit", updateUnitList.getId());
            String where = "id_unit=" + updateUnitList.getId();
            this.database.update("unit_list", values, where, null);
            return 1;
        }
    }




    public void  delete(UnitList unitList){

        this.database.execSQL("UPDATE unit_list set delete_flag = 'Y' where id_unit = "+ unitList.getId());


    }



}
