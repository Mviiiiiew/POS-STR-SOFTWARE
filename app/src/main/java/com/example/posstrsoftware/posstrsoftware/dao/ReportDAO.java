package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.posstrsoftware.posstrsoftware.model.ReportList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 11/28/2016.
 */

public class ReportDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperReport;
    public ReportDAO(Context context) {
        dbHelperReport = new DbHelper(context);
    }

    public void open() {
        database = dbHelperReport.getWritableDatabase();
    }
    public void close() {
        dbHelperReport.close();
    }

    public ArrayList<ReportList> getAllReportList(String input_date) {
        ArrayList<ReportList> reportLists = new ArrayList<>();
        Cursor cursor = database.rawQuery("select *  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('"+input_date+"' as decimal)  and cast('"+input_date+"' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(2));
            mReportList.setAmount(cursor.getInt(3));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }


    public void ReportDaily(String input_date){
        String sql = "select *  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('"+input_date+"' as decimal)  and cast('"+input_date+"' as decimal) ";
    }


    public void ReportDaily(String input_date_from,String input_date_to){
        String sql = " select *  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('"+input_date_from+"' as decimal)  and cast('"+input_date_to+"' as decimal) ;";

    }


    public ReportList addx(){

        ReportList bee = new ReportList();
        Cursor cursor = database.rawQuery("INSERT INTO report_list(nameproduct_text,productprice_text,productcount_text) select productsale_text,sum(CAST(productprice_text as decimal)) as product_price,count(productsale_text) from productsale_list where delete_flag = 'N' group by productsale_text ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            cursor.moveToNext();
        }
        cursor.close();
        return bee ;
    }




/*

    public void add(ReportList reportList) {

        ContentValues values = new ContentValues();
        values.put("nameproduct_text", reportList.getNameProduct());
        values.put("productprice_text", reportList.getPrice());
        this.database.insert("report_list", null, values);



    }
*/
}
