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
            +"Cost TEXT, "
            +"UnitName TEXT, "
            +"GroupName TEXT "

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
            +"id_product TEXT ,"
            +"productsale_text TEXT,"
            +"productprice_text TEXT,"
            +"sale_master_id INTEGER,"
            +"unit_id TEXT ,"
            +"unit_name TEXT ,"
            +"group_id TEXT ,"
            +"group_name TEXT, "
            +"discount TEXT ,"
            +"doc_date TEXT ,"
            +"product_price TEXT ,"
            +"product_cost TEXT ,"
            +"vat_flag TEXT ,"
            +"vatrate TEXT ,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";
    private static final String tablesale_masterCreateSQL = "CREATE TABLE sale_master("
            +"sale_master_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"sale_doc_id TEXT,"
            +"sale_doc_date TEXT ,"
            +"discount TEXT ,"
            +"id_company TEXT DEFAULT 1"
            +");";
    private static final String tablesale_detailCreateSQL = "CREATE TABLE sale_detail("
            +"sale_detail_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"sale_master_id INTEGER,"
            +"line_no TEXT ,"
            +"product_id TEXT ,"
            +"product_name TEXT ,"
            +"unit_id TEXT ,"
            +"group_id TEXT ,"
            +"group_name TEXT, "
            +"product_amount TEXT ,"
            +"product_price TEXT ,"
            +"product_price_total TEXT ,"
            +"product_price_total_vat TEXT ,"
            +"product_cost TEXT ,"
            +"product_cost_total TEXT "
            +");";

    private static final String tabletransectionBillCreateSQL = "CREATE TABLE transectionBill("
            +"id_productsale INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"id_product TEXT ,"
            +"productsale_text TEXT,"
            +"productprice_text TEXT,"
            +"sale_master_id INTEGER,"
            +"unit_id TEXT ,"
            +"unit_name TEXT ,"
            +"group_id TEXT ,"
            +"group_name TEXT, "
            +"discount TEXT ,"
            +"doc_date TEXT ,"
            +"product_price TEXT ,"
            +"product_cost TEXT ,"
            +"vat_flag TEXT ,"
            +"vatrate TEXT ,"
            +"delete_flag TEXT DEFAULT 'N'"
            +");";








    private static  final String viewProductList = "CREATE VIEW vproduct_list as select *,CASE WHEN vat_flag = 'Y' THEN cast(price_text as decimal) +( cast(price_text as decimal)*(select vatrate /100.0 from company_list limit 1)) ELSE cast(price_text as decimal) END AS cal_tax from product_list order by 1";

    private static  final String viewmasterList = "CREATE VIEW viewmaster_list as select sale_master_id,doc_date,cast(discount as decimal) as bill_discount," +
            "count(sale_master_id) as count_amount,\n" +
            "sum(cast(product_price as decimal)) as sum_product_price,\n" +
            "sum(cast(product_cost as decimal)) as sum_product_cost ,\n" +
            "sum(case when vat_flag  = 'Y' then ((cast(product_price as decimal) * (cast(vatrate as decimal))/(100.0 + cast(vatrate as decimal)))) else 0.00 end) as sum_vat\n" +
            "from transectionBill \n" +
            "group by sale_master_id,doc_date\n" +
            "order by cast(sale_master_id as decimal)";

    private static  final String viewdetailList = "CREATE VIEW viewdetail_list as  select productsale_text,sum(cast(product_price as decimal)) as sum_product_price,count(productsale_text) as product_amount from transectionBill where sale_master_id = (select sale_master_id from viewmaster_list   order by sale_master_id desc limit 1) group by productsale_text order by productsale_text";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUnitCreateSQL);
        db.execSQL(tableProductCreateSQL);
        db.execSQL(tableGroupCreateSQL);
        db.execSQL(tableCompanyCreateSQL);
        db.execSQL(tableProductSaleCreateSQL);
        db.execSQL(tableReportCreateSQL);
        db.execSQL(viewProductList);
        db.execSQL(tablesale_masterCreateSQL);
        db.execSQL(tablesale_detailCreateSQL);
        db.execSQL(tabletransectionBillCreateSQL);
        db.execSQL(viewmasterList);
        db.execSQL(viewdetailList);

        String insertData = "INSERT INTO company_list (CompanyName,CompanyAddress,Telephone,TAXID,DivisionName,DivisionName,POSMachineID,RegisterID,ENDbillText,VATRate)  VALUES ('CompanyName','CompanyAddress','Telephone','TAXID','DivisionName','DivisionName','POSMachineID','RegisterID','ENDbillText','7');";
        db.execSQL(insertData);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
