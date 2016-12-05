package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.model.ReportList;

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
        Cursor cursor = database.rawQuery("select productsale_text,sum(CAST(productprice_text as decimal)) as product_price,count(productsale_text),id_product from productsale_list where delete_flag = 'N' group by productsale_text ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mProductSaleList = new ProductSaleList();
            mProductSaleList.setProductSale(cursor.getString(0));
            mProductSaleList.setAmount(cursor.getString(2));
            mProductSaleList.setPrice(cursor.getDouble(1));
            mProductSaleList.setProductid(cursor.getInt(3));
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
            mproductSaleList.setProductSale(cursor.getString(2));
            mproductSaleList.setPrice(cursor.getDouble(3));
            productSaleLists.add(mproductSaleList);
            cursor.moveToNext();

        }
        cursor.close();
        return productSaleLists;
    }


    public void add(ProductSaleList productSaleList) {

        ContentValues values = new ContentValues();
        values.put("id_product", productSaleList.getProductid());
        values.put("productsale_text", productSaleList.getProductSale());
        values.put("productprice_text", productSaleList.getPrice());
        values.put("unit_id", productSaleList.getUnitid());
        values.put("unit_name", productSaleList.getUnitname());
        values.put("group_id", productSaleList.getGroupid());
        values.put("group_name", productSaleList.getGroupname());
        values.put("product_price", productSaleList.getProduct_price());
        values.put("product_cost", productSaleList.getProduct_cost());
        values.put("vat_flag", productSaleList.getVat_flag());

        this.database.insert("productsale_list", null, values);


    }

    public void
    updatebill(String input_discount) {

        this.database.execSQL("update productsale_list set sale_master_id =  (select(case when (select count(*) from productsale_list where sale_master_id is not null) = 0 then 1 else (select (CASE  WHEN sale_master_id  is null then 1 else sale_master_id +1  end) as id from productsale_list where  sale_master_id is not null order by id_productsale desc limit 1) end)),discount=" + input_discount + ",doc_date= date('now'),vatrate=(select vatrate from company_list ) where sale_master_id is null");

    }

    public ReportList addx(){

        ReportList bee = new ReportList();
        Cursor cursor = database.rawQuery("INSERT INTO transectionBill(id_product ,productsale_text ,productprice_text,sale_master_id,unit_id,unit_name ,group_id,group_name,discount ,doc_date,product_price,product_cost,vat_flag,vatrate ,delete_flag ) " +
                "select id_product ,productsale_text ,productprice_text,sale_master_id,unit_id,unit_name ,group_id,group_name,discount ,doc_date,product_price,product_cost,vat_flag,vatrate ,delete_flag  from productsale_list   ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            cursor.moveToNext();
        }
        cursor.close();
        return bee ;
    }




    public void clear(ProductSaleList productSaleList) {

        this.database.execSQL("Delete  From productsale_list");

    }

    public void delete(ProductSaleList productSaleList) {

        this.database.execSQL("Delete From productsale_list  where productsale_text = '" + productSaleList.getProductSale() + "'");


    }

    public void delete_product_id(ProductSaleList productSaleList) {

        this.database.execSQL("Delete From productsale_list  where id_productsale = " + productSaleList.getId());


    }


}
