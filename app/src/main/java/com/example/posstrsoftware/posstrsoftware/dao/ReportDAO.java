package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.posstrsoftware.posstrsoftware.model.ReportList;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



/**
 * Created by Wasabi on 11/28/2016.24234234
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


    public ArrayList<ReportList> getAllPrintReportProduct(String input_date) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT *  FROM viewProductReport WHERE CAST(replace(doc_date,'-','')  AS DECIMAL) between CAST('" + input_date + "' AS DECIMAL)  AND CAST('" + input_date + "' AS DECIMAL);;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setNameProduct(cursor.getString(2));
            mReportList.setProductprintAmount(cursor.getInt(4));
            mReportList.setSumSaleAmt(cursor.getDouble(7));
            mReportList.setSaleAmt(cursor.getDouble(5));
            mReportList.setNameUnit(cursor.getString(3));
            mReportList.setPrintProductDate(cursor.getString(1));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }

    public ArrayList<ReportList> getAllPrintReportProductOneTwo(String input_date_from, String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT *  FROM viewProductReport WHERE CAST(replace(doc_date,'-','')  AS DECIMAL) between CAST('" + input_date_from + "' AS DECIMAL)  AND CAST('" + input_date_to + "' AS DECIMAL);;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setNameProduct(cursor.getString(2));
            mReportList.setProductprintAmount(cursor.getInt(4));
            mReportList.setSumSaleAmt(cursor.getDouble(7));
            mReportList.setSaleAmt(cursor.getDouble(5));
            mReportList.setNameUnit(cursor.getString(3));
            mReportList.setPrintProductDate(cursor.getString(1));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }

    public ArrayList<ReportList> getAllPrintReportProductTwoOne(String input_date_from, String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT *  FROM viewProductReport WHERE CAST(replace(doc_date,'-','')  AS DECIMAL) between CAST('" + input_date_to + "' AS DECIMAL)  AND CAST('" + input_date_from + "' AS DECIMAL);;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setNameProduct(cursor.getString(2));
            mReportList.setProductprintAmount(cursor.getInt(4));
            mReportList.setSumSaleAmt(cursor.getDouble(7));
            mReportList.setSaleAmt(cursor.getDouble(5));
            mReportList.setNameUnit(cursor.getString(3));
            mReportList.setPrintProductDate(cursor.getString(1));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }


    public ArrayList<ReportList> getAllReportList(String input_date) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select *,(PriceSumVat - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date + "' as decimal)  and cast('" + input_date + "' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(3));
            mReportList.setAmount(cursor.getInt(4));
            mReportList.setSumPrice(cursor.getDouble(5));
            mReportList.setSumCost(cursor.getDouble(6));
            mReportList.setSumVAT(cursor.getDouble(7));
            mReportList.setProfit(cursor.getDouble(11));
            mReportList.setBillId(cursor.getString(10));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }


    public ArrayList<ReportList> getAllReportListOneTwo(String input_date_from, String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select *,(PriceSumVat - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(3));
            mReportList.setAmount(cursor.getInt(4));
            mReportList.setSumPrice(cursor.getDouble(5));
            mReportList.setSumCost(cursor.getDouble(6));
            mReportList.setSumVAT(cursor.getDouble(7));
            mReportList.setProfit(cursor.getDouble(11));
            mReportList.setBillId(cursor.getString(10));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }

    public ArrayList<ReportList> getSumBill(String input_date_from, String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select  sum(bill_discount ),sum(count_amount),sum(PriceSumVat),sum(sum_product_cost)    " +
                "  ,sum(sum_vat),(sum(PriceSumVat ) - sum(bill_discount) - sum(sum_product_cost)-sum(sum_vat))" +
                " as profit  from viewmaster_list where cast(replace(doc_date,'-','')  as decimal)" +
                " between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setxSumAmount(cursor.getInt(1));
            mReportList.setxSumDisc(cursor.getDouble(0));
            mReportList.setxSumSaleAmt(cursor.getDouble(2));
            mReportList.setxSumCost(cursor.getDouble(3));
            mReportList.setxSumVat(cursor.getDouble(4));
            mReportList.setxSumProfit(cursor.getDouble(5));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }
    public ArrayList<ReportList> getSumBillTwoOne(String input_date_from, String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select  sum(bill_discount ),sum(count_amount),sum(PriceSumVat),sum(sum_product_cost)\n" +
                ",sum(sum_vat),(sum(PriceSumVat ) - sum(bill_discount) - sum(sum_product_cost)-sum(sum_vat)) as profit" +
                " from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_to + "' as decimal)  and cast('" + input_date_from + "' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setxSumAmount(cursor.getInt(1));
            mReportList.setxSumDisc(cursor.getDouble(0));
            mReportList.setxSumSaleAmt(cursor.getDouble(2));
            mReportList.setxSumCost(cursor.getDouble(3));
            mReportList.setxSumVat(cursor.getDouble(4));
            mReportList.setxSumProfit(cursor.getDouble(5));
            reportLists.add(mReportList);
            cursor.moveToNext();
        }
        cursor.close();
        return reportLists;
    }

    public ArrayList<ReportList> getAllReportListTwoOne(String input_date_from, String input_date_to) {
        ArrayList<ReportList> reportLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("select *,(PriceSumVat - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_to + "' as decimal)  and cast('" + input_date_from + "' as decimal);", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ReportList mReportList = new ReportList();
            mReportList.setSale_masterid(cursor.getInt(0));
            mReportList.setDate(cursor.getString(1));
            mReportList.setDiscount(cursor.getDouble(3));
            mReportList.setAmount(cursor.getInt(4));
            mReportList.setSumPrice(cursor.getDouble(5));
            mReportList.setSumCost(cursor.getDouble(6));
            mReportList.setSumVAT(cursor.getDouble(7));
            mReportList.setProfit(cursor.getDouble(11));
            mReportList.setBillId(cursor.getString(10));
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
            FileOutputStream os = new FileOutputStream(file);
            os.write(0xef);
            os.write(0xbb);
            os.write(0xbf);
            CSVWriter csvWrite = new CSVWriter(new OutputStreamWriter(os));
            csvWrite.flush();
            read();
            Cursor curCSV = database.rawQuery("select   doc_date as DATE ,productsale_text as  NameProduct " +
                    ",unit_name as  NameUnit,\n" +
                    " count(productsale_text)as Amount ,product_price as SaleAmt\n" +
                    " ,sum(product_price) as SumSaleAmt   from transectionBill   \n" +
                    " where cast(replace(doc_date,'-','')  as decimal)  \n" +
                    " between cast('"+input_date+"' as decimal)   and cast('"+input_date+"' as decimal)  \n" +
                    " group by  productsale_text , doc_date order by doc_date asc", null);


            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1),
                        curCSV.getString(2), curCSV.getString(3),
                        curCSV.getString(4), curCSV.getString(5)
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

    public void exportDataBaseProductOneTwo(String input_date_from, String input_date_to) {


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportProduct.csv");

        try {

            file.createNewFile();
            FileOutputStream os = new FileOutputStream(file);
            os.write(0xef);
            os.write(0xbb);
            os.write(0xbf);
            CSVWriter csvWrite = new CSVWriter(new OutputStreamWriter(os));
            csvWrite.flush();
            read();
            Cursor curCSV = database.rawQuery("select   doc_date as DATE,productsale_text as  NameProduct ,unit_name as    NameUnit,  count(productsale_text)as Amount ,product_price as SaleAmt ,sum(product_price) as SumSaleAmt   from transectionBill    where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal) group by  productsale_text ,doc_date order by doc_date asc  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5)
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

    public void exportDataBaseProductTwoOne(String input_date_from, String input_date_to) {


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvReportProduct.csv");

        try {

            file.createNewFile();
            FileOutputStream os = new FileOutputStream(file);
            os.write(0xef);
            os.write(0xbb);
            os.write(0xbf);
            CSVWriter csvWrite = new CSVWriter(new OutputStreamWriter(os));
            csvWrite.flush();
            read();
            Cursor curCSV = database.rawQuery("select   doc_date as DATE ,productsale_text as  NameProduct ,unit_name as    NameUnit,  count(productsale_text)as Amount ,product_price as SaleAmt ,sum(product_price) as SumSaleAmt   from transectionBill    where cast(replace(doc_date,'-','')  as decimal) between cast('" +input_date_to+ "' as decimal)  and cast('" + input_date_from + "' as decimal) group by  productsale_text,doc_date  order by doc_date asc  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5)
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
            Cursor curCSV = database.rawQuery("select bill_id as BillNo,doc_date as DATE,PriceSumVat as SaleAmt ,count_amount as Amount,bill_discount as Discount,sum_product_cost as Cost,sum_vat as VAT,(PriceSumVat - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date + "' as decimal)  and cast('" + input_date + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7)
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
            Cursor curCSV = database.rawQuery("select bill_id as BillNo,doc_date as DATE,PriceSumVat as SaleAmt ,count_amount as Amount,bill_discount as Discount,sum_product_cost as Cost,sum_vat as VAT,(PriceSumVat - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_from + "' as decimal)  and cast('" + input_date_to + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7)
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

        File file = new File(exportDir, "csvReportDay.csv");
        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            read();
            Cursor curCSV = database.rawQuery("select bill_id as BillNo,doc_date as DATE,PriceSumVat as SaleAmt ,count_amount as Amount,bill_discount as Discount,sum_product_cost as Cost,sum_vat as VAT,(PriceSumVat - bill_discount - sum_product_cost - sum_vat) as profit from viewmaster_list where cast(replace(doc_date,'-','')  as decimal) between cast('" + input_date_to + "' as decimal)  and cast('" + input_date_from + "' as decimal)  ", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export you can add over here...
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7)
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
