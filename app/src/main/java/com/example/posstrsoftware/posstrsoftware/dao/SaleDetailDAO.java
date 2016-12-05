package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.posstrsoftware.posstrsoftware.model.SaleDetailList;

import java.util.ArrayList;

/**
 * Created by MAN on 12/4/2016.
 */

public class SaleDetailDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperSaleDetail;
    public SaleDetailDAO(Context context) {
        dbHelperSaleDetail = new DbHelper(context);
    }

    public void open() {
        database = dbHelperSaleDetail.getWritableDatabase();
    }
    public void close() {
        dbHelperSaleDetail.close();
    }







    public ArrayList<SaleDetailList> getAllSaleDetailList() {
        ArrayList<SaleDetailList> saleMasterLists = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM sale_master ;", null);
        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            SaleDetailList saleDetailList = new SaleDetailList();

            saleMasterLists.add(saleDetailList);
            cursor.moveToNext();
        }
        cursor.close();
        return saleMasterLists;
    }








    public void addsale_detail (SaleDetailList saleDetailList) {

        ContentValues values = new ContentValues();
        /*values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());
        values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());
        values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());
        values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());
        values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());
        values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());
        values.put("sale_doc_date", saleDetailList.getDate());
        values.put("discount", saleDetailList.getDiscount());*/
        this.database.insert("sale_detail", null, values);



    }



}
