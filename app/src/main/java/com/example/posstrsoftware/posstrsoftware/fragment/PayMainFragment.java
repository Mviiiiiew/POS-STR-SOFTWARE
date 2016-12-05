package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.POSD.controllers.PrinterController;
import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.ConcludeActivity;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;

import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;

import com.example.posstrsoftware.posstrsoftware.util.PrintFix;

import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Locale;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PayMainFragment extends Fragment implements View.OnClickListener {

    private static final byte[] Set_Right = new byte[]{(byte) 27, (byte) 97, (byte) 2};
    private PrinterController printerController = null;
    ButtonRectangle btn_back;
    ButtonRectangle btn_cost_1000;
    ButtonRectangle btn_cost_100;
    ButtonRectangle btn_cost_10;
    ButtonRectangle btn_cost_1;
    ButtonRectangle btn_cost_5;
    ButtonRectangle btn_cost_20;
    ButtonRectangle btn_cost_50;
    ButtonRectangle btn_cost_500;
    ButtonRectangle btn_cost_050;
    ButtonRectangle btn_cost_025;
    ButtonRectangle btn_Pay;
    CheckBox checkbox_print;
    TextView txt_NameTotal;
    TextView txt_Discount;
    TextView txt_Totalall;
    String totalall;
    EditText edit_txt_cash;
    Double total = 0.0;
    Double change = 0.0;
    Double cash = 0.0;
    String x;
    String discount;
    String discountpercent;
    String txtdiscount;
    String symbol;
    String process;
    int processmanual;
    int processbarcode;


    public PayMainFragment() {
        super();
    }

    public static PayMainFragment newInstance() {
        PayMainFragment fragment = new PayMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        edit_txt_cash.setText("");


    }

    private String Date() {
        java.text.DateFormat df = new java.text.SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(java.util.Calendar.getInstance().getTime());

        return date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_pay, container, false);
        initInstances(rootView);
        Intent intent = getActivity().getIntent();
        symbol = intent.getStringExtra("symbol");
        Log.d("symbol", symbol);
        processmanual = (intent.getIntExtra("processmanual", 0));
        Log.d("processm", processmanual + "");
        processbarcode = intent.getIntExtra("processbarcode", 0);
        Log.d("processb", processbarcode + "");

        x = intent.getStringExtra("totalx");
        txtdiscount = intent.getStringExtra("discount");
        Log.d("discount", txtdiscount);
        totalall = intent.getStringExtra("totalall");
        txt_Totalall.setText(formatAmount.formatAmountDouble(Double.valueOf(totalall.toString())));
        discount = intent.getStringExtra("discountcost");
        discountpercent = intent.getStringExtra("discountpercent");

        txt_NameTotal.setText(formatAmount.formatAmountDouble(Double.valueOf(x.toString())));
        try {

            txt_Discount.setText(formatAmount.formatAmountDouble(Double.valueOf(discount.toString())));


        } catch (Exception e) {
            txt_Discount.setText(discountpercent.toString());

        }


        edit_txt_cash.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }


            @Override

            public void afterTextChanged(Editable s) {


                edit_txt_cash.removeTextChangedListener(this);

                try {
                    String originalString = String.valueOf(edit_txt_cash.getText());

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###.##");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    edit_txt_cash.setText(formattedString);
                    edit_txt_cash.setSelection(edit_txt_cash.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                edit_txt_cash.addTextChangedListener(this);
            }


        });


        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        checkbox_print = (CheckBox) rootView.findViewById(R.id.checkbox_print);
        txt_NameTotal = (TextView) rootView.findViewById(R.id.txt_NameTotal);
        txt_Discount = (TextView) rootView.findViewById(R.id.txt_Discount);
        txt_Totalall = (TextView) rootView.findViewById(R.id.txt_Totalall);
        edit_txt_cash = (EditText) rootView.findViewById(R.id.edit_txt_cash);
        btn_cost_1000 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_1000);
        btn_cost_100 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_100);
        btn_cost_10 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_10);
        btn_cost_1 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_1);
        btn_cost_5 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_5);
        btn_cost_20 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_20);
        btn_cost_50 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_50);
        btn_cost_500 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_500);
        btn_cost_025 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_025);
        btn_cost_050 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_050);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        btn_Pay = (ButtonRectangle) rootView.findViewById(R.id.btn_Pay);
        btn_cost_050.setOnClickListener(this);
        btn_cost_025.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_cost_1.setOnClickListener(this);
        btn_cost_5.setOnClickListener(this);
        btn_cost_10.setOnClickListener(this);
        btn_cost_20.setOnClickListener(this);
        btn_cost_50.setOnClickListener(this);
        btn_cost_100.setOnClickListener(this);
        btn_cost_500.setOnClickListener(this);
        btn_cost_1000.setOnClickListener(this);
        btn_Pay.setOnClickListener(this);
        btn_cost_1000.setRippleSpeed(50);
        btn_cost_100.setRippleSpeed(50);
        btn_cost_10.setRippleSpeed(50);
        btn_cost_1.setRippleSpeed(50);
        btn_cost_5.setRippleSpeed(50);
        btn_cost_20.setRippleSpeed(50);
        btn_cost_50.setRippleSpeed(50);
        btn_cost_500.setRippleSpeed(50);
        btn_back.setRippleSpeed(50);
        btn_cost_050.setRippleSpeed(50);
        btn_cost_025.setRippleSpeed(50);
        btn_Pay.setRippleSpeed(60);

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

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_back:
                getActivity().finish();
                break;
            case R.id.btn_cost_1:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 1.0));

                break;
            case R.id.btn_cost_5:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 5.0));

                break;
            case R.id.btn_cost_10:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 10.0));
                break;
            case R.id.btn_cost_20:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0"
                        : edit_txt_cash.getText().toString().replaceAll(",", "")) + 20.0));
                break;
            case R.id.btn_cost_50:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 50.0));
                break;
            case R.id.btn_cost_100:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 100.0));
                break;
            case R.id.btn_cost_500:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 500.0));
                break;
            case R.id.btn_cost_1000:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 1000.0));
                break;
            case R.id.btn_cost_050:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 0.50));

                break;
            case R.id.btn_cost_025:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("") ? "0.0" :
                        edit_txt_cash.getText().toString().replaceAll(",", "")) + 0.25));
                break;
            case R.id.btn_Pay:
                try {

                    total = Double.valueOf((txt_NameTotal.getText().toString().replace(",", "")));

                } catch (NumberFormatException e) {

                }
                try {
                    cash = Double.valueOf(edit_txt_cash.getText().toString().replaceAll(",", ""));

                } catch (NumberFormatException e) {

                }
                total = Double.valueOf(txt_Totalall.getText().toString().replace(",", ""));
                change = cash - total;
                if (checkbox_print.isChecked() == true) {
                    if (edit_txt_cash.getText().toString().trim().replaceAll("\\.", "").matches("")) {
                        Toast.makeText(getActivity(), "กรุณาใส่จำนวนเงินรับชำระ", Toast.LENGTH_LONG).show();
                    } else {
                        double cash = Double.parseDouble(edit_txt_cash.getText().toString().trim().replaceAll(",", ""));
                        double totalall = Double.parseDouble((txt_Totalall.getText().toString().trim().replaceAll(",", "")));
                        if (cash >= totalall) {
                            HeadMaster();
                            ProductAll();
                            Underline();
                            TotalAll();
                            EndText();
                            Linefeed();
                           // printerController.PrinterController_Close();


                            ProductSaleDAO productSaleDAO1 = new ProductSaleDAO(getActivity());
                            productSaleDAO1.open();
                            productSaleDAO1.addx();
                            productSaleDAO1.updatebill(txt_Discount.getText().toString().replaceAll(",", ""));
                            productSaleDAO1.close();


                            Toast.makeText(getActivity(), "OK", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), ConcludeActivity.class);
                            intent.putExtra("mTotal", txt_NameTotal.getText().toString());
                            intent.putExtra("mDiscount", txt_Discount.getText().toString());
                            intent.putExtra("mTotalAll", txt_Totalall.getText().toString());
                            intent.putExtra("mCash", edit_txt_cash.getText().toString());
                            intent.putExtra("mChange", formatAmount.formatAmountDouble(change).toString());
                            intent.putExtra("symbol", symbol);
                            intent.putExtra("discount", txtdiscount);
                            intent.putExtra("processmanual", processmanual);
                            intent.putExtra("processbarcode", processbarcode);
                            Toast.makeText(getActivity(), txt_Totalall.getText().toString(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else if (cash < totalall) {
                            Toast.makeText(getActivity(), "รับชำระมีค่าน้อยกว่าราคารวม" + " -> " + formatAmount.formatAmountDouble(totalall), Toast.LENGTH_SHORT).show();
                        }
                    }


                    //   Toast.makeText(getActivity(),companyDAO.InvoiceMaster().getCompanyName().toString(), Toast.LENGTH_SHORT).show();

                } else if (checkbox_print.isChecked() == false) {
                    if (edit_txt_cash.getText().toString().trim().replaceAll("\\.", "").matches("")) {
                        Toast.makeText(getActivity(), "กรุณาใส่จำนวนเงินรับชำระ", Toast.LENGTH_LONG).show();
                    } else {
                        double cash = Double.parseDouble(edit_txt_cash.getText().toString().trim().replaceAll(",", ""));
                        double totalall = Double.parseDouble((txt_Totalall.getText().toString().trim().replaceAll(",", "")));
                        if (cash >= totalall) {
                            Toast.makeText(getActivity(), "OK", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), ConcludeActivity.class);
                            intent.putExtra("mTotal", txt_NameTotal.getText().toString());
                            intent.putExtra("mDiscount", txt_Discount.getText().toString());
                            intent.putExtra("mTotalAll", txt_Totalall.getText().toString());
                            intent.putExtra("mCash", edit_txt_cash.getText().toString());
                            intent.putExtra("mChange", formatAmount.formatAmountDouble(change).toString());
                            intent.putExtra("symbol", symbol);
                            intent.putExtra("discount", txtdiscount);
                            startActivity(intent);


                        } else if (cash < totalall) {
                            Toast.makeText(getActivity(), "รับชำระมีค่าน้อยกว่าราคารวม" + " -> " + formatAmount.formatAmountDouble(totalall), Toast.LENGTH_SHORT).show();
                        }
                    }


                }


                break;
            default:
                break;

        }
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
        String x = "-------------------------------";

        printerController.PrinterController_Print(x.getBytes());
    }

    private void TotalAll() {
        String x = "-------------------------------";
        String Totaltxt = "Total";
        String total = PrintFix.generatePrice(txt_NameTotal.getText().toString(), 27);
        String TotalAlltxt = "TotalAll";
        String totalall = PrintFix.generatePrice(txt_Totalall.getText().toString(), 24);
        String cash = PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.valueOf(edit_txt_cash.getText().toString().replaceAll(",", ""))), 28);
        String changz = PrintFix.generatePrice(formatAmount.formatAmountDouble(change), 26);
        String Cashtxt = "Cash";
        String Changetxt = "Change";
        String Discounttxt = "Discount" + "->" + (PrintFix.generateName(symbol, 5));
        String discount = PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.valueOf(txtdiscount)), 17);
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Print(Totaltxt.getBytes());
        printerController.PrinterController_Print(total.getBytes());
        printerController.PrinterController_Print(x.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Linefeed();
        printerController.PrinterController_Print(Discounttxt.getBytes());
        printerController.PrinterController_Print(discount.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(TotalAlltxt.getBytes());
        printerController.PrinterController_Print(totalall.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(Cashtxt.getBytes());
        printerController.PrinterController_Print(cash.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Font_Bold();
        printerController.PrinterController_Print(Changetxt.getBytes());
        printerController.PrinterController_Print(changz.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Linefeed();

    }

    private void ProductAll() {
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllDetialProductSaleList();

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

    private void HeadMaster() {
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");


        String date = df.format(Calendar.getInstance().getTime());
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        printerController.PrinterController_Font_Normal_mode();
        CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();
        String text1 = "welcome to " + companyDAO.InvoiceMaster().getCompanyName();
        String text2 = "Division " + companyDAO.InvoiceMaster().getDivisionName() + " " + "Tel." + companyDAO.InvoiceMaster().getTelephone();
        String text3 = "TAX ID# " + companyDAO.InvoiceMaster().getTAXID();
        String text4 = "POS# " + companyDAO.InvoiceMaster().getPOSMachineID();
        String text5 = "BillNo # " + productSaleDAO.InvoiceMaster().getSaleMasterid();
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
        printerController.PrinterController_Print(date.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Set_Center();
        printerController.PrinterController_Font_Bold();
        printerController.PrinterController_Print("-----------------------------".getBytes());
        printerController.PrinterController_Linefeed();


        productSaleDAO.close();
        companyDAO.close();
    }


}


