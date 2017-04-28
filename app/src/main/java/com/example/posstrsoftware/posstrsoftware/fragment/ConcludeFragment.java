package com.example.posstrsoftware.posstrsoftware.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.POSD.controllers.PrinterController;
import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.PayMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductManualActivity;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.util.PrintFix;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */

/**
 * หน้าสรุปบิลในแต่ละครั้ง
 * */
public class ConcludeFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_manual;
    ButtonRectangle btn_barcode;
    Button btt_RePrint;
    private PrinterController printerController = null;
    TextView txt_mDate;
    TextView txt_mTotal;
    TextView txt_mDiscount;
    TextView txt_mTotalAll;
    TextView txt_mCash;
    TextView txt_mChange;
    TextView txt_symbolDiscount;
    TextView txt_mValueVat;
    String mTotal;
    String mDiscount;
    String mTotalAll;
    String Cash;
    String Change;
    String symbol;
    String discount;
    int processmanual;
    int processbarcode;
    String ValueVat;

    public ConcludeFragment() {
        super();
    }

    public static ConcludeFragment newInstance() {
        ConcludeFragment fragment = new ConcludeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_conclude, container, false);

        Intent intent = getActivity().getIntent();
        processmanual = (intent.getIntExtra("processmanual",0));
        ValueVat = (intent.getStringExtra("ValueVat"));


        Log.d("processm",processmanual+"");
        processbarcode = intent.getIntExtra("processbarcode",1);
        Log.d("processb",processbarcode+"");
        mTotal = intent.getStringExtra("mTotal");
        mDiscount = intent.getStringExtra("mDiscount");
        mTotalAll = intent.getStringExtra("mTotalAll");
        Cash = intent.getStringExtra("mCash");
        Change = intent.getStringExtra("mChange");
        symbol = intent.getStringExtra("symbol");
        discount = intent.getStringExtra("discount");
        Log.d("discount",discount+"");
        initInstances(rootView);
        Date();
        Total();

        return rootView;
    }

    private void initInstances(View rootView) {

        // Init 'View' instance(s) with rootView.findViewById here
        btt_RePrint = (Button)rootView.findViewById(R.id.btt_RePrint);
        btn_manual = (ButtonRectangle) rootView.findViewById(R.id.btn_manual);
        btn_barcode = (ButtonRectangle) rootView.findViewById(R.id.btn_barcode);
        txt_mTotal = (TextView) rootView.findViewById(R.id.txt_mTotal);
        txt_mDate = (TextView) rootView.findViewById(R.id.txt_mDate);
        txt_mDiscount = (TextView) rootView.findViewById(R.id.txt_mDiscount);
        txt_mTotalAll = (TextView)rootView.findViewById(R.id.txt_mTotalAll);
        txt_mCash = (TextView)rootView.findViewById(R.id.txt_mCash);
        txt_mChange = (TextView)rootView.findViewById(R.id.txt_mChange);
        txt_mValueVat = (TextView) rootView.findViewById(R.id.txt_mValueVat);
        txt_symbolDiscount = (TextView)rootView.findViewById(R.id.txt_symbolDiscount);

        btn_barcode.setRippleSpeed(40);
        btn_manual.setRippleSpeed(40);
        btt_RePrint.setOnClickListener(this);
        btn_barcode.setOnClickListener(this);
        btn_manual.setOnClickListener(this);


        if(  processmanual == 1){
            btn_manual.setBackgroundColor(getResources().getColor(R.color.colorDeepOrange));
            btn_barcode.setBackgroundColor(getResources().getColor(R.color.colorGrayz));


        }else{
            btn_barcode.setBackgroundColor(getResources().getColor(R.color.colorDeepOrange));
            btn_manual.setBackgroundColor(getResources().getColor(R.color.colorGrayz));
        }



    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */


    @Override
    public void onResume() {
        super.onResume();
        Date();
        Total();
        VAT();
        Discount();
        TotalAll();
        Cash();
        Change();

    }

    private void VAT() {
        txt_mValueVat.setText(ValueVat.toString());
    }

    private void Change() {
        txt_mChange.setText(Change);
    }

    private void Cash() {
        txt_mCash.setText (formatAmount.formatAmountDouble(Double.valueOf(Cash.replaceAll(",",""))));
    }

    private void TotalAll() {
        txt_mTotalAll.setText(mTotalAll);
    }

    private void Discount() {
        txt_mDiscount.setText(formatAmount.formatAmountDouble(Double.valueOf(discount.replaceAll(",",""))));
        txt_symbolDiscount.setText("< "+symbol+" >");
    }

    private void Total() {
        txt_mTotal.setText(mTotal);
    }

    private void Date() {
        java.text.DateFormat df = new java.text.SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(java.util.Calendar.getInstance().getTime());
        txt_mDate.setText(date);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (btn_manual == v) {
            ProductSaleList productSaleList = new ProductSaleList();
            ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
            productSaleDAO.open();
            productSaleDAO.clear(productSaleList);
            productSaleDAO.close();
            Intent intent = new Intent(getActivity(), SaleProductManualActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getActivity().finishAffinity();
            startActivity(intent);
        } else if (btn_barcode == v) {
            ProductSaleList productSaleList = new ProductSaleList();
            ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
            productSaleDAO.open();
            productSaleDAO.clear(productSaleList);
            productSaleDAO.close();
            Intent intent = new Intent(getActivity(), SaleProductActivity.class);
            getActivity().finishAffinity();

          //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }else if (btt_RePrint == v){

            if(discount.matches("0.0")){

                //No discount

                HeadMaster();
                ProductAll();
                Underline();
                TotalAllNoDiscount();
                EndText();
                Linefeed();

            }else{
                //discount

                HeadMaster();
                ProductAll();
                Underline();
                TotalAllx();
                EndText();
                Linefeed();

            }

                           /*HeadMaster();
                            ProductAll();
                            Underline();
                            TotalAllx();
                            EndText();
                            Linefeed();*/

        }


    }

    private void TotalAllNoDiscount() {

        String CashChangex =formatAmount.formatAmountDouble(Double.valueOf(Cash.replaceAll(",","")))+"/"+formatAmount.formatAmountDouble(Double.valueOf(Change.replaceAll(",","")));
        String x = "--------------------------------";
        String Totaltxt = "Total";
        String total = PrintFix.generatePrice(mTotal, 27);
        String mValueVat = "VAT";
        String vat = PrintFix.generatePrice(ValueVat, 29);
        String changz = PrintFix.generatePrice(CashChangex, 21);
        String CashChange = "Cash/Change";


        printerController.PrinterController_Print(Totaltxt.getBytes());
        printerController.PrinterController_Print(total.getBytes());
        printerController.PrinterController_Print(x.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(mValueVat.getBytes());
        printerController.PrinterController_Print(vat.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Font_Bold();
        printerController.PrinterController_Print(CashChange.getBytes());
        printerController.PrinterController_Print(changz.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Linefeed();


    }

    private void EndText() {

        CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();
        String endtext = companyDAO.InvoiceMaster().getENDbillText();
        printerController.PrinterController_Font_Normal_mode();
        printerController.PrinterController_Set_Center();
        printerController.PrinterController_Print(endtext.getBytes());
        companyDAO.close();


    }

    private void Linefeed() {
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Linefeed();
    }

    private void Underline() {
        String x = "--------------------------------";

        printerController.PrinterController_Print(x.getBytes());
    }

    private void TotalAllx() {
        String CashChangex =formatAmount.formatAmountDouble(Double.valueOf(Cash.replaceAll(",","")))+"/"+formatAmount.formatAmountDouble(Double.valueOf(Change.replaceAll(",","")));
        String x = "--------------------------------";
        String Totaltxt = "Total";
        String total = PrintFix.generatePrice(mTotal, 27);
        String mValueVat = "VAT";
        String vat = PrintFix.generatePrice(ValueVat, 29);
        String changz = PrintFix.generatePrice(CashChangex, 21);
        String CashChange = "Cash/Change";
        String Discounttxt = "DISC." + "->" + (PrintFix.generateName(symbol, 5));
        String discountx = PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.valueOf(discount)), 20);



        printerController.PrinterController_Print(Totaltxt.getBytes());
        printerController.PrinterController_Print(total.getBytes());
        printerController.PrinterController_Print(x.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(mValueVat.getBytes());
        printerController.PrinterController_Print(vat.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(Discounttxt.getBytes());
        printerController.PrinterController_Print(discountx.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Font_Bold();
        printerController.PrinterController_Print(CashChange.getBytes());
        printerController.PrinterController_Print(changz.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Linefeed();

    }

    private void ProductAll() {
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();

        String feed[] = new String[]{"", " "};
        for (ProductSaleList bean : productSaleLists) {
            String price = PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.valueOf(bean.getPrice())), 10);
            String product = PrintFix.generateName(bean.getProductSale(), 18) + feed[1];
            String amount = PrintFix.generateName(bean.getAmount(), 3) + feed[0];

            printerController.PrinterController_Font_Normal_mode();
            printerController.PrinterController_Set_Left();
            printerController.PrinterController_Print(amount.getBytes());
            printerController.PrinterController_Print(product.getBytes());
            printerController.PrinterController_Print(price.getBytes());
            printerController.PrinterController_Print("\n".getBytes());


        }
        productSaleDAO.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void HeadMaster() {
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        java.text.DateFormat df = new java.text.SimpleDateFormat(" d /MM /yyyy, HH:mm");


        String date = df.format(java.util.Calendar.getInstance().getTime());
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        printerController.PrinterController_Font_Normal_mode();
        CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();
        String text1 = "welcome to " + companyDAO.InvoiceMaster().getCompanyName();
        String text2 = "Division " + companyDAO.InvoiceMaster().getDivisionName();
        String text3  ="Tel." + companyDAO.InvoiceMaster().getTelephone();
        String text4 = "TAX ID# " + companyDAO.InvoiceMaster().getTAXID();
        String text5 = "POS# " + companyDAO.InvoiceMaster().getPOSMachineID();
        String text6 = "BillNo # " + productSaleDAO.InvoiceMaster().getBillId();

        printerController.PrinterController_Set_Center();
        printerController.PrinterController_Print(text1.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Set_Left();
        printerController.PrinterController_Print(text2.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(text3.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(text4.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(text5.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(text6.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(date.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Set_Center();
        printerController.PrinterController_Font_Bold();
        printerController.PrinterController_Print("--------------------------------".getBytes());
        printerController.PrinterController_Linefeed();


        productSaleDAO.close();
        companyDAO.close();
    }


}

