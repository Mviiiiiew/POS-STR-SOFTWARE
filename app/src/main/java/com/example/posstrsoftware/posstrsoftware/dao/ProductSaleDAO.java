package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperProductSale;

    public  ProductSaleDAO(Context context){
        dbHelperProductSale = new DbHelper(context);
    }
    public void open(){
        database = dbHelperProductSale.getWritableDatabase();
    }
    public void close() {
        dbHelperProductSale.close();
    }

    public ArrayList<ProductSaleList> getAllProductSaleList() {

        ArrayList<ProductSaleList> ProductSaleList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM productsale_list where delete_flag = 'N';",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ProductSaleList mProductSaleList = new ProductSaleList();
            mProductSaleList.setId(cursor.getInt(0));
            mProductSaleList.setProductSale(cursor.getString(1));
            ProductSaleList.add(mProductSaleList);
            cursor.moveToNext();


        }
        cursor.close();
        return ProductSaleList;
    }




    public void   add(ProductSaleList productSaleList){

            ContentValues values = new ContentValues();
            values.put("productsale_text",productSaleList.getProductSale());
            this.database.insert("productsale_list",null,values);

    }
    public void delete(ProductSaleList productSaleList) {

        this.database.execSQL("Delete  From productsale_list" );

    }




}
