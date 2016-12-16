package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.posstrsoftware.posstrsoftware.model.ReportList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVWriter;

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

    public void read() {
        database = dbHelperReport.getReadableDatabase();


    }

    public void close() {
        dbHelperReport.close();
    }

    public ArrayList<ReportList> getAllReportList(String input_date) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select *,(sum_product_price - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('"+input_date+"' as decimal)  and cast('"+input_date+"' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setBillId(cursor.getString(9));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(2));
            mReportList.setAmount(cursor.getInt(3));
            mReportList.setSumPrice(cursor.getDouble(4));
            mReportList.setSumCost(cursor.getDouble(5));
            mReportList.setSumVAT(cursor.getDouble(6));
            mReportList.setProfit(cursor.getDouble(10));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }

    public ArrayList<ReportList> getAllReportListOneTwo(String input_date_from,String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select *,(sum_product_price - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('"+input_date_from+"' as decimal)  and cast('"+input_date_to+"' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setBillId(cursor.getString(9));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(2));
            mReportList.setAmount(cursor.getInt(3));
            mReportList.setSumPrice(cursor.getDouble(4));
            mReportList.setSumCost(cursor.getDouble(5));
            mReportList.setSumVAT(cursor.getDouble(6));
            mReportList.setProfit(cursor.getDouble(10));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }
    public ArrayList<ReportList> getAllReportListTwoOne(String input_date_from,String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select *,(sum_product_price - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('"+input_date_to+"' as decimal)  and cast('"+input_date_from +"' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setBillId(cursor.getString(9));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(2));
            mReportList.setAmount(cursor.getInt(3));
            mReportList.setSumPrice(cursor.getDouble(4));
            mReportList.setSumCost(cursor.getDouble(5));
            mReportList.setSumVAT(cursor.getDouble(6));
            mReportList.setProfit(cursor.getDouble(10));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }



    public void exportDataBaseProduct(String input_date) {


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportProduct.csv");

        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("SELECT * FROM viewProductReport where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date + "' as decimal)  and cast('" + input_date + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5),curCSV.getString(6)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
            close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }

    public void exportDataBaseProductOneTwo(String input_date_from,String input_date_to) {


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportProduct.csv");

        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("SELECT * FROM viewProductReport where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5),curCSV.getString(6)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
            close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }
    public void exportDataBaseProductTwoOne(String input_date_from,String input_date_to) {


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportProduct.csv");

        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("SELECT * FROM viewProductReport where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_to + "' as decimal)  and cast('" + input_date_from  + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5),curCSV.getString(6)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
            close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }


    public void exportDataBaseDayDaily(String input_date) {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportDay.csv");
        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("select *,(sum_product_price - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date + "' as decimal)  and cast('" + input_date + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6),curCSV.getString(7)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
            close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }

    public void exportDataBaseBetweenDailyOneTwo(String input_date_from, String input_date_to) {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportDay.csv");
        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("select *,(sum_product_price - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6),curCSV.getString(7)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
            close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }




    public void exportDataBaseBetweenDailyTwoOne(String input_date_from, String input_date_to) {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvfilename.csv");
        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("select *,(sum_product_price - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_to + "' as decimal)  and cast('" + input_date_from + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6),curCSV.getString(7)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
            close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }








}
