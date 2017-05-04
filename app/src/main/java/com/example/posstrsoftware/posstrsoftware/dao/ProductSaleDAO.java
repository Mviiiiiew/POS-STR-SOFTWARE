package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.posstrsoftware.posstrsoftware.model.CompanyList;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.model.ReportList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/31/2016.234234234234
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

    public ProductSaleList InvoiceMaster() {
        ProductSaleList productSaleList = new ProductSaleList();
        Cursor cursor = database.rawQuery("select * from viewmaster_list  order by sale_master_id desc limit 1 ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productSaleList.setSaleMasterid(cursor.getInt(0));
            productSaleList.setDiscount(cursor.getString(2));
            productSaleList.setVat(cursor.getDouble(6));
            productSaleList.setBillId(cursor.getString(10));

            cursor.moveToNext();

        }
        cursor.close();
        return productSaleList;


    }





    public ArrayList<ProductSaleList> getAllMasterProductSaleList() {
        ArrayList<ProductSaleList> ProductSaleList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from viewmaster_list  order by sale_master_id desc limit 1 ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mProductSaleList = new ProductSaleList();
            mProductSaleList.setSaleMasterid(cursor.getInt(0));
            mProductSaleList.setDiscount(cursor.getString(2));
            mProductSaleList.setVat(cursor.getDouble(6));

            ProductSaleList.add(mProductSaleList);
            cursor.moveToNext();
        }
        cursor.close();
        return ProductSaleList;
    }

    public ArrayList<ProductSaleList> getAllDetialProductSaleList() {
        ArrayList<ProductSaleList> ProductSaleList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from viewdetail_list order by productsale_text ASC ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mProductSaleList = new ProductSaleList();
            mProductSaleList.setProductSale(cursor.getString(0));
            mProductSaleList.setAmount(cursor.getString(2));
            mProductSaleList.setPrice(cursor.getDouble(1));

            ProductSaleList.add(mProductSaleList);
            cursor.moveToNext();
        }
        cursor.close();
        return ProductSaleList;
    }




    public ArrayList<ProductSaleList> getAllProductSaleList() {
        ArrayList<ProductSaleList> ProductSaleList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select productsale_text,sum(CAST(product_price as decimal)) " +
                "as product_price,count(productsale_text),id_product,sum(CAST(Value_Vat as decimal)) as ValueVat " +
                "from productsale_list where delete_flag = 'N' group by productsale_text   ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mProductSaleList = new ProductSaleList();
            mProductSaleList.setProductSale(cursor.getString(0));
            mProductSaleList.setAmount(cursor.getString(2));
            mProductSaleList.setPrice(cursor.getDouble(1));
            mProductSaleList.setProductid(cursor.getInt(3));
            mProductSaleList.setValueVat(cursor.getDouble(4));
            ProductSaleList.add(mProductSaleList);
            cursor.moveToNext();
        }
        cursor.close();
        return ProductSaleList;
    }


    public ArrayList<ProductSaleList> getAllSaleList() {
        ArrayList<ProductSaleList> ProductSaleList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from vsale_list ;", null);
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

    public ArrayList<ProductSaleList> getAllProductSaleDelete(String mProduct) {

        ArrayList<ProductSaleList> productSaleLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select * from  productsale_list  where productsale_text  = '"+mProduct+"' and delete_flag = 'N';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductSaleList mproductSaleList = new ProductSaleList();
            mproductSaleList.setId(cursor.getInt(0));
            mproductSaleList.setProductSale(cursor.getString(2));
            mproductSaleList.setPrice(cursor.getDouble(11));
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
        values.put("Symbol_Vat",productSaleList.getSymbolVat());
        values.put("Value_Vat",productSaleList.getValueVat());


        this.database.insert("productsale_list", null, values);


    }

    public void updatebill(String input_discount) {

        this.database.execSQL("update transectionBill set sale_master_id =  (select(case when (select count(*) from transectionBill where sale_master_id is not null) = 0 then 1 else (select (CASE  WHEN sale_master_id  is null then 1 else sale_master_id +1  end) as id from transectionBill where  sale_master_id is not null order by id_productsale desc limit 1) end)),discount=" + input_discount + ",doc_date= date('now'),vatrate=(select vatrate from company_list ) where sale_master_id is null");

    }
    public void updateRunIdBill(String input_RunIdBill) {

        this.database.execSQL("update transectionBill set RunIdBill=(select count(*) from viewmaster_list  where  substr(doc_date,3,2) || substr(doc_date,6,2)  like ("+input_RunIdBill+"))  where  substr(doc_date,3,2) || substr(doc_date,6,2)  like ("+input_RunIdBill+") and RunIdBill is null");

    }



    public ReportList addx(){

        ReportList bee = new ReportList();
        Cursor cursor = database.rawQuery("INSERT INTO transectionBill(id_product ,productsale_text ,productprice_text,unit_id,unit_name ,group_id,group_name ,product_price,product_cost,vat_flag ,delete_flag,Symbol_Vat,Value_Vat ) " +
                "select id_product ,productsale_text ,productprice_text,unit_id,unit_name ,group_id,group_name ,product_price,product_cost,vat_flag ,delete_flag,Symbol_Vat,Value_Vat  from productsale_list   ;", null);
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
    public void delete_product_name(ProductSaleList productSaleList) {

        this.database.execSQL("Delete From productsale_list  where productsale_text = '" + productSaleList.getProductSale() + "'");


    }


}
