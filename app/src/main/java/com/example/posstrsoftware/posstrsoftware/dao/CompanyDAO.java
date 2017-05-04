package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.posstrsoftware.posstrsoftware.model.CompanyList;

import java.util.ArrayList;

/**
 * Created by Wasabi on 10/27/2016.
 */

public class CompanyDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelperCompany;

    public CompanyDAO(Context context) {
        dbHelperCompany = new DbHelper(context);
    }



    public void open() {
        database = dbHelperCompany.getWritableDatabase();
    }

    public void close() {
        dbHelperCompany.close();
    }

    public ArrayList<CompanyList> getAllCompany() {

        ArrayList<CompanyList> companyLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM company_list where delete_flag = 'N';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CompanyList mCompanyList = new CompanyList();
            mCompanyList.setId(cursor.getInt(0));
            mCompanyList.setCompanyName(cursor.getString(1));
            mCompanyList.setCompanyAddress(cursor.getString(2));
            mCompanyList.setTelephone(cursor.getString(3));
            mCompanyList.setTAXID(cursor.getString(4));
            mCompanyList.setDivisionName(cursor.getString(5));
            mCompanyList.setPOSMachineID(cursor.getString(6));
            mCompanyList.setRegisterID(cursor.getString(7));
            mCompanyList.setENDbillText(cursor.getString(8));
            mCompanyList.setVATRate(cursor.getDouble(9));
            companyLists.add(mCompanyList);
            cursor.moveToNext();


        }
        cursor.close();
        return companyLists;
    }
    public int check() {

        String QueryCheckPass = "Select count(*) from PassWordList  where   delete_flag = 'N' ";
        SQLiteStatement stmtcheckpass = database.compileStatement(QueryCheckPass);

        int count_row_password = (int) stmtcheckpass.simpleQueryForLong();
        if (stmtcheckpass != null) stmtcheckpass.close();

        if (count_row_password != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public CompanyList InvoiceMaster() {
        CompanyList companyListsInvoiceMaster = new CompanyList();
        Cursor cursor = database.rawQuery("SELECT * FROM company_list ;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            companyListsInvoiceMaster.setCompanyName(cursor.getString(1));
            companyListsInvoiceMaster.setPOSMachineID(cursor.getString(6));
            companyListsInvoiceMaster.setTelephone(cursor.getString(3));
            companyListsInvoiceMaster.setDivisionName(cursor.getString(5));
            companyListsInvoiceMaster.setTAXID(cursor.getString(4));
            companyListsInvoiceMaster.setENDbillText(cursor.getString(8));

            cursor.moveToNext();

        }
        cursor.close();
        return companyListsInvoiceMaster;


    }
    public int checkPass(CompanyList companyList) {

        String QueryBarcode = "Select count(*) from PassWordList  where  PASSWORD = ? AND delete_flag = ? ";
        SQLiteStatement stmtpassword = database.compileStatement(QueryBarcode);

        stmtpassword.bindString(1, String.valueOf(companyList.getPassWord()));
        stmtpassword.bindString(2, "N");

        int count_row_password = (int) stmtpassword.simpleQueryForLong();
        if (stmtpassword != null) stmtpassword.close();

        if (count_row_password != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public void Updatepass() {

        this.database.execSQL("UPDATE PassWordList set delete_flag = 'Y'");

    }


    public void addPassWord(CompanyList companyList) {

        ContentValues values = new ContentValues();
        values.put("PASSWORD", companyList.getPassWordInsert());

        this.database.update("PassWordList",values, "idPASS = 1", null);

    }


    public void add(CompanyList companyList) {

        ContentValues values = new ContentValues();
        values.put("CompanyName", companyList.getCompanyName());
        values.put("CompanyAddress;", companyList.getCompanyAddress());
        values.put("Telephone", companyList.getTelephone());
        values.put("TAXID", companyList.getTAXID());
        values.put("DivisionName", companyList.getDivisionName());
        values.put("POSMachineID", companyList.getPOSMachineID());
        values.put("RegisterID", companyList.getRegisterID());
        values.put("ENDbillText", companyList.getENDbillText());
        values.put("VATRate", companyList.getVATRate());
        this.database.insert("company_list", null, values);

    }

    public void update(CompanyList companyList) {

        CompanyList updateCompanyList = companyList;
        ContentValues values = new ContentValues();
        values.put("CompanyName", updateCompanyList.getCompanyName());
        values.put("CompanyAddress", updateCompanyList.getCompanyAddress());
        values.put("Telephone", updateCompanyList.getTelephone());
        values.put("TAXID", updateCompanyList.getTAXID());
        values.put("DivisionName", updateCompanyList.getDivisionName());
        values.put("RegisterID", updateCompanyList.getRegisterID());
        values.put("ENDbillText", updateCompanyList.getENDbillText());
        values.put("VATRate", updateCompanyList.getVATRate());

        this.database.update("company_list", values, "id_company = 1", null);

    }
    public void PosMachineID(CompanyList companyList) {

        CompanyList updateCompanyList = companyList;
        ContentValues values = new ContentValues();
        values.put("POSMachineID", updateCompanyList.getPOSMachineID());

        this.database.update("company_list", values, "id_company = 1", null);

    }

}









