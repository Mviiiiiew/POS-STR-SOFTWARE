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

            Cursor cursor = database.rawQuery("  select pl.id_product,pl.id_barcode,pl.product_text,round (pl.PriceNoVAT,2),pl.price_text,pl.Cost,pl.vat_flag,pl.id_unit,ul.unit_text,pl.id_group,gl.group_text,pl.Symbol_Vat,round (pl.ValueVat,2) from vproduct_list pl      \n" +
                    "inner join  unit_list ul on pl.id_unit = ul.id_unit       \n" +
                    "inner join  group_list gl on pl.id_group = gl.id_group    \n" +
                    "where pl.delete_flag = 'N' ORDER BY  gl.group_text  ASC;",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductList productList1 = new ProductList();
            productList1.setId(cursor.getInt(0));
            productList1.setBarcode(cursor.getString(1));
            productList1.setProductText(cursor.getString(2));
            productList1.setProductprice(cursor.getDouble(4));
            productList1.setCheckvat(cursor.getString(6));
            productList1.setCost(cursor.getDouble(5));
            productList1.setUnitList(new UnitList(cursor.getInt(7),cursor.getString(8)));
            productList1.setGroupList(new GroupList(cursor.getInt(9),cursor.getString(10)));
            productList1.setProductpricesumvat(cursor.getDouble(3));
            productList1.setSymbolVat(cursor.getString(11));
            productList1.setValueVat(cursor.getDouble(12));



            productList.add(productList1);
            cursor.moveToNext();
        }
        cursor.close();
        return productList;
}



    public int add(ProductList productList) {
        String query = "Select count(*) from product_list  where product_text = ?  AND delete_flag = ? ";
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
            values.put("vat_flag",productList.getCheckvat());
            values.put("id_unit",productList.getUnitList().getId());
            values.put("id_group",productList.getGroupList().getId());
            values.put("Cost",productList.getCost());
            values.put("UnitName",productList.getUnitList().getUnitText());
            values.put("GroupName",productList.getGroupList().getGroupText());
            values.put("Symbol_Vat",productList.getSymbolVat());

            this.database.insert("product_list", null, values);
            return 1;
        }




    }

    public ProductSaleList SearchID(String ID){

        ProductSaleList bee = new ProductSaleList();

        Cursor cursor = database.rawQuery("SELECT * FROM vproduct_list where delete_flag = 'N' and id_barcode ='"+ID+"';",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            bee.setProductSale(cursor.getString(2));
            bee.setPrice(cursor.getDouble(12));
            bee.setProductid(cursor.getInt(0));
            bee.setUnitid(cursor.getInt(4));
            bee.setGroupid(cursor.getInt(5));
            bee.setVat_flag(cursor.getString(7));
            bee.setProduct_cost(cursor.getDouble(8));
            bee.setProduct_price(cursor.getDouble(3));
            bee.setUnitname(cursor.getString(9));
            bee.setGroupname(cursor.getString(10));
            bee.setSymbolVat(cursor.getString(11));
            bee.setValueVat(cursor.getDouble(13));
            cursor.moveToNext();
        }
        cursor.close();
        return bee ;

    }
    public int check(ProductList productList) {
        String query = "Select count(*) from product_list where product_text = ? AND delete_flag = ?";
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, productList.getProductText());
        stmt.bindString(2, "N");
        int count_row = (int) stmt.simpleQueryForLong();
        if (stmt != null) stmt.close();
        if (count_row != 0) {
            return 0;
        } else {
            return 1;
        }
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
            values.put("id_unit", updateProductList.getUnitList().getId());
            values.put("id_group", updateProductList.getGroupList().getId());
            String where = "id_product=" + updateProductList.getId();
            this.database.update("product_list", values, where, null);
            return 1;
        }
    }
    public void updatereplace(ProductList productList) {

        ProductList updateProductList = productList;
        ContentValues values = new ContentValues();
        values.put("product_text", updateProductList.getProductText());
        values.put("id_product", updateProductList.getId());
        values.put("id_unit", updateProductList.getUnitList().getId());
        values.put("id_group", updateProductList.getGroupList().getId());
        values.put("price_text", updateProductList.getProductprice());
        values.put("Cost", updateProductList.getCost());
        values.put("id_barcode", updateProductList.getBarcode());
        values.put("vat_flag", updateProductList.getCheckvat());
        values.put("Symbol_Vat", updateProductList.getSymbolVat());
        String where = "id_product=" + updateProductList.getId();
        this.database.update("product_list", values, where, null);
    }



    public void delete(ProductList productList) {

        this.database.execSQL("UPDATE product_list set delete_flag = 'Y' where id_product = " + productList.getId());

    }


}
