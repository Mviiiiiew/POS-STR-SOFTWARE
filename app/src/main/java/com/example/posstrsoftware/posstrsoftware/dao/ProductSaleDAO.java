package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/31/2016.
 */

public class ProductSaleDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperProductSale;

    public ProductSaleDAO(Context context) {
        dbHelperProductSale = new DbHelper(context);
    }

    public void open() {
        database = dbHelperProductSale.getWritableDatabase();
    }

    public void close() {
        dbHelperProductSale.close();
    }

        public ArrayList<ProductSaleList> getAllProductSaleList() {
        ArrayList<ProductSaleList> ProductSaleList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select productsale_text,sum(CAST(productprice_text as decimal)) as product_price,count(productsale_text) from productsale_list where delete_flag = 'N' group by productsale_text ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mProductSaleList = new ProductSaleList();
            mProductSaleList.setProductSale(cursor.getString(0));
            mProductSaleList.setAmount(cursor.getString(2));
            mProductSaleList.setPrice(cursor.getString(1));
            ProductSaleList.add(mProductSaleList);
            cursor.moveToNext();
        }
        cursor.close();
        return ProductSaleList;
    }

    public ArrayList<ProductSaleList> getAllProductSaleDeleteList() {

        ArrayList<ProductSaleList> productSaleLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM productsale_list where delete_flag = 'N';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mproductSaleList = new ProductSaleList();
            mproductSaleList.setId(cursor.getInt(0));
            mproductSaleList.setProductSale(cursor.getString(1));
            mproductSaleList.setPrice(cursor.getString(2));
            productSaleLists.add(mproductSaleList);
            cursor.moveToNext();


        }
        cursor.close();
        return productSaleLists;
    }



    public void add(ProductSaleList productSaleList) {


        ContentValues values = new ContentValues();
        values.put("productsale_text", productSaleList.getProductSale());
        values.put("productprice_text", productSaleList.getPrice());
        this.database.insert("productsale_list", null, values);


    }

    public void clear(ProductSaleList productSaleList) {

        this.database.execSQL("Delete  From productsale_list");

    }

    public void delete(ProductSaleList productSaleList) {

        this.database.execSQL("Delete From productsale_list  where productsale_text = '" +productSaleList.getProductSale()+"'");


    }
    public void delete_product_id(ProductSaleList productSaleList) {

        this.database.execSQL("Delete From productsale_list  where id_productsale = "+productSaleList.getId());


    }



}
