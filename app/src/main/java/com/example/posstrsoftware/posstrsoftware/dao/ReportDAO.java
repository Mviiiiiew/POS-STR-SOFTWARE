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

        Cursor cursor = database.rawQuery("select *  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('\"+input_date+\"' as decimal)  and cast('\"+input_date+\"' as decimal);", null);
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


    public void ReportDaily(String input_date) {
        String sql = "select *  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date + "' as decimal)  and cast('" + input_date + "' as decimal) ";
    }


    public void ReportDaily(String input_date_from, String input_date_to) {
        String sql = " select *  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal) ;";

    }


    public ReportList addx() {

        ReportList bee = new ReportList();
        Cursor cursor = database.rawQuery("INSERT INTO report_list(nameproduct_text,productprice_text,productcount_text) select productsale_text,sum(CAST(productprice_text as decimal)) as product_price,count(productsale_text) from productsale_list where delete_flag = 'N' group by productsale_text ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cursor.moveToNext();
        }
        cursor.close();
        return bee;
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
