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

    public ArrayList<ReportList> getAllReportList() {
        ArrayList<ReportList> reportLists = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM report_list ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setId(cursor.getInt(0));
            mReportList.setNameProduct(cursor.getString(1));
            mReportList.setPrice(cursor.getDouble(2));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }





    public void add(ReportList reportList) {

        ContentValues values = new ContentValues();
        values.put("nameproduct_text", reportList.getNameProduct());
        values.put("productprice_text", reportList.getPrice());
        this.database.insert("report_list", null, values);



    }
    public void delete_Report_id(ReportList reportList) {

        this.database.execSQL("Delete From report_list  where id_BillNo = "+reportList.getId());


    }

}
