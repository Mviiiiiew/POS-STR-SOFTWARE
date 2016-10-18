package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.posstrsoftware.posstrsoftware.model.ProductList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/17/2016.
 */

public class ProductDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperProduct;

    public ProductDAO(Context context) {
        dbHelperProduct = new DbHelper(context);
    }

    public void open() {
        database = dbHelperProduct.getWritableDatabase();
    }

    public void close() {
        dbHelperProduct.close();
    }

    public ArrayList<ProductList> getAllProductList() {

        ArrayList<ProductList> productList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM product_list where delete_flag = 'N';",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductList productList1 = new ProductList();
            productList1.setId(cursor.getInt(0));
            productList1.setProductText(cursor.getString(1));
            productList1.setProductprice(cursor.getInt(2));
            productList.add(productList1);
            cursor.moveToNext();
        }
        cursor.close();
        return productList;
    }


    public int add(ProductList productList) {
        String query = "Select count(*) from product_list where product_text = ?  AND delete_flag = ? ";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, productList.getProductText());
        stmt.bindString(2, "N");
        int count_row = (int) stmt.simpleQueryForLong();
        if (stmt != null) stmt.close();
        if (count_row != 0) {
            return 0;
        } else {
            ContentValues values = new ContentValues();
            values.put("product_text", productList.getProductText());
            values.put("price_text",productList.getProductprice());
            this.database.insert("product_list", null, values);
            return 1;
        }


    }

    public void delete(ProductList productList) {

        this.database.execSQL("UPDATE product_list set delete_flag = 'Y' where id_product = " + productList.getId());

    }
}
