package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.posstrsoftware.posstrsoftware.model.GroupList;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.model.UnitList;


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

        Cursor cursor = database.rawQuery("select pl.id_product,pl.id_barcode,pl.product_text,pl.price_text,pl.id_unit,ul.unit_text,pl.id_group,gl.group_text from product_list pl " +
                "inner join  unit_list ul on pl.id_unit = ul.id_unit and  ul.delete_flag = 'N' " +
                "inner join  group_list gl on pl.id_group = gl.id_group and  gl.delete_flag = 'N' " +
                        "where pl.delete_flag = 'N';",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductList productList1 = new ProductList();
            productList1.setId(cursor.getInt(0));
            productList1.setBarcode(cursor.getString(1));
            productList1.setProductText(cursor.getString(2));
            productList1.setProductprice(cursor.getDouble(3));
            productList1.setUnitList(new UnitList(cursor.getInt(4),cursor.getString(5)));
            productList1.setGroupList(new GroupList(cursor.getInt(6),cursor.getString(7)));
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
            values.put("id_barcode",productList.getBarcode());
            values.put("price_text",productList.getProductprice());
            values.put("id_unit",productList.getUnitList().getId());
            values.put("id_group",productList.getGroupList().getId());
            this.database.insert("product_list", null, values);
            return 1;
        }



    }

    public ProductSaleList SearchID(String ID){

        ProductSaleList bee = new ProductSaleList();

        Cursor cursor = database.rawQuery("SELECT * FROM product_list where delete_flag = 'N' and id_barcode ='"+ID+"';",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            bee.setProductSale(cursor.getString(2));
            bee.setPrice(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        return bee ;

    }
    public int update(ProductList productList) {
        String query = "Select count(*) from product_list where product_text = ? AND delete_flag = ?";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, productList.getProductText());
        stmt.bindString(2, "N");
        int count_row = (int) stmt.simpleQueryForLong();
        if (stmt != null) stmt.close();
        if (count_row != 0) {
            return 0;
        } else {
            ProductList updateProductList = productList;
            ContentValues values = new ContentValues();
            values.put("product_text", updateProductList.getProductText());
            values.put("id_product", updateProductList.getId());
            String where = "id_product=" + updateProductList.getId();
            this.database.update("product_list", values, where, null);
            return 1;
        }
    }

    public void delete(ProductList productList) {

        this.database.execSQL("UPDATE product_list set delete_flag = 'Y' where id_product = " + productList.getId());

    }


}
