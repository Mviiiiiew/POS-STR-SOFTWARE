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




    public void ReportDaily(String input_date){
        String sql = "select sm.sale_master_id,sale_doc_date," +
                "sum(CAST(product_price_total as decimal))  as sum_product_price_total," +
                "sum(CAST(product_amount as decimal)) as sum_product_amount," +
                "sum(CAST(product_cost_total as decimal)) as sum_product_cost_total," +
                "CAST(discount as decimal) as discount_total" +
                "from sale_detail sd inner join sale_master sm on sd.sale_master_id = sm.sale_master_id " +
                "where substr(sale_doc_date,7)||substr(sale_doc_date,4,2)||substr(sale_doc_date,1,2)  between cast(substr('"+input_date+"',7)||substr('"+input_date+"',4,2)||substr('"+input_date+"',1,2) as decimal) and cast(substr('"+input_date+"',7)||substr('"+input_date+"',4,2)||substr('"+input_date+"',1,2) as decimal) " +
                "group by sm.sale_master_id,sale_doc_date " +
                "order by sm.sale_master_id";
    }


    public void ReportDaily(String input_date_from,String input_date_to){
        String sql = "select sm.sale_master_id,sale_doc_date," +
                "sum(CAST(product_price_total as decimal))  as sum_product_price_total," +
                "sum(CAST(product_amount as decimal)) as sum_product_amount," +
                "sum(CAST(product_cost_total as decimal)) as sum_product_cost_total," +
                "CAST(discount as decimal) as discount_total" +
                "from sale_detail sd inner join sale_master sm on sd.sale_master_id = sm.sale_master_id " +
                "where substr(sale_doc_date,7)||substr(sale_doc_date,4,2)||substr(sale_doc_date,1,2)  between cast(substr('"+input_date_from+"',7)||substr('"+input_date_from+"',4,2)||substr('"+input_date_from+"',1,2) as decimal) and cast(substr('"+input_date_to+"',7)||substr('"+input_date_to+"',4,2)||substr('"+input_date_to+"',1,2) as decimal) " +
                "group by sm.sale_master_id,sale_doc_date " +
                "order by sm.sale_master_id";

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
