package com.example.posstrsoftware.posstrsoftware.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wasabi on 10/14/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String databaseName = "STRPOS.Sqlite";
    private static final int databaseVerSion = 1;
    Context mContect;
    public DbHelper(Context context) {
        super(context,databaseName,null,databaseVerSion );
        this.mContect = context;
    }

    private static final String tableUnitCreateSQL = "CREATE TABLE unit_list("
            +"id_unit INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"unit_text TEXT NOT NULL,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";

    private static final String tableGroupCreateSQL = "CREATE TABLE group_list("
            +"id_group INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"group_text TEXT NOT NULL,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";

    private static final String tableProductCreateSQL = "CREATE TABLE product_list("
            +"id_product INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"id_barcode TEXT NOT NULL,"
            +"product_text TEXT NOT NULL,"
            +"price_text TEXT NOT NULL,"
            +"id_unit INTEGER,"
            +"id_group INTEGER,"
            +"delete_flag TEXT DEFAULT 'N',"
            +"vat_flag TEXT, "
            +"Cost TEXT "

            +");";
    private static final String tableCompanyCreateSQL = "CREATE TABLE company_list("
            +"id_company INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"CompanyName TEXT ,"
            +"CompanyAddress TEXT ,"
            +"Telephone TEXT ,"
            +"TAXID TEXT ,"
            +"DivisionName TEXT,"
            +"POSMachineID TEXT ,"
            +"RegisterID TEXT ,"
            +"ENDbillText TEXT ,"
            +"VATRate TEXT ,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";

    private static final String tableReportCreateSQL = "CREATE TABLE report_list("
            +"id_Report INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"id_BillNo TEXT,"
            +"nameproduct_text TEXT NOT NULL,"
            +"productprice_text TEXT NOT NULL,"
            +"productcount_text TEXT NOT NULL"
            +");";

    private static final String tableProductSaleCreateSQL = "CREATE TABLE productsale_list("
            +"id_productsale INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"productsale_text TEXT NOT NULL,"
            +"productprice_text TEXT NOT NULL,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";




    private static  final String viewProductList = "CREATE VIEW vproduct_list as select *,CASE WHEN vat_flag = 'Y' THEN cast(price_text as decimal) +( cast(price_text as decimal)*(select vatrate /100.0 from company_list limit 1)) ELSE cast(price_text as decimal) END AS cal_tax from product_list order by 1";




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUnitCreateSQL);
        db.execSQL(tableProductCreateSQL);
        db.execSQL(tableGroupCreateSQL);
        db.execSQL(tableCompanyCreateSQL);
        db.execSQL(tableProductSaleCreateSQL);
        db.execSQL(tableReportCreateSQL);
        db.execSQL(viewProductList);
        String insertData = "INSERT INTO company_list (CompanyName,CompanyAddress,Telephone,TAXID,DivisionName,DivisionName,POSMachineID,RegisterID,ENDbillText,VATRate)  VALUES ('CompanyName','CompanyAddress','Telephone','TAXID','DivisionName','DivisionName','POSMachineID','RegisterID','ENDbillText','7');";
        db.execSQL(insertData);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
