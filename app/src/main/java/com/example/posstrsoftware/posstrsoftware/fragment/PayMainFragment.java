package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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

import java.sql.ResultSet;
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
    ButtonRectangle btn_clear;
    ButtonRectangle btn_Pay;
    CheckBox checkbox_print;
    TextView txt_NameTotal;
    TextView txt_Discount;
    TextView txt_Totalall;
    TextView txt_ValueVat;
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
    int processmanual;
    int processbarcode;
    String date;
    Double ValueVat;
    String CashChangeAll;

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
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        date = sharedPreferences.getString("date", (Date().toString()));
        Log.d("date5", date + "");
        CashChangeAll = cash.toString() + "/" + change.toString();
        Log.d("CashChangeAll", CashChangeAll + "");
        edit_txt_cash.setText("");

       /* edit_txt_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_txt_cash.setText("");

            }
        });
*/


    }

    private String Date() {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyMM");

        String date = df.format(java.util.Calendar.getInstance().getTime());

        return date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main_pay, container, false);
        initInstances(rootView);

        Intent intent = getActivity().getIntent();
        ValueVat = intent.getDoubleExtra("ValueVat", 0.0);
        Log.d("ValueVat", ValueVat + "");
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
        txt_ValueVat.setText(formatAmount.formatAmountDouble(ValueVat));
        discount = intent.getStringExtra("discountcost");
        discountpercent = intent.getStringExtra("discountpercent");

        txt_NameTotal.setText(formatAmount.formatAmountDouble(Double.valueOf(x.toString())));
        try {

            txt_Discount.setText(formatAmount.formatAmountDouble(Double.valueOf(discount.toString())));


        } catch (Exception e) {

            txt_Discount.setText(formatAmount.formatAmountDouble(Double.valueOf(txtdiscount)) + " (" + discountpercent.toString() + ")");

        }

        edit_dialog(edit_txt_cash);


        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        txt_ValueVat = (TextView) rootView.findViewById(R.id.txt_ValueVat);
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
        btn_clear = (ButtonRectangle) rootView.findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
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
        btn_clear.setRippleSpeed(100);
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
        edit_txt_cash.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edit_txt_cash.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit_txt_cash.setText("");

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_number, null);
                final EditText edit_dialog = (EditText) mView.findViewById(R.id.edit_dialog);
                edit_dialog(edit_dialog);


               mBuilder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {


                        edit_txt_cash.setText(edit_dialog.getText().toString());
                        dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                edit_dialog.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                            edit_txt_cash.setText(edit_dialog.getText().toString());
                            dialog.dismiss();

                        }else if(actionId == EditorInfo.IME_ACTION_DONE) {
                            edit_txt_cash.setText(edit_dialog.getText().toString());
                            dialog.dismiss();
                        }
                        return false;
                    }
                });

            }


        });


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
            case R.id.btn_clear:
                edit_txt_cash.setText("");
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
                        Toast.makeText(getActivity(), R.string.txt_please_enter_amount, Toast.LENGTH_LONG).show();
                    } else {
                        double cash = Double.parseDouble(edit_txt_cash.getText().toString().trim().replaceAll(",", ""));
                        double totalall = Double.parseDouble((txt_Totalall.getText().toString().trim().replaceAll(",", "")));
                        if (cash >= totalall) {


                            ProductSaleDAO productSaleDAO1 = new ProductSaleDAO(getActivity());
                            productSaleDAO1.open();
                            productSaleDAO1.addx();
                            productSaleDAO1.updatebill(txtdiscount);
                            productSaleDAO1.updateRunIdBill(Date().toString());


                            //   Toast.makeText(getActivity(), afterdate+"   "+Date().toString(), Toast.LENGTH_LONG).show();
                            productSaleDAO1.close();

                            if (txtdiscount.matches("0.0")) {
                                //No discount

                                HeadMaster();
                                ProductAll();
                                Underline();
                                TotalAllNoDiscount();
                                EndText();
                                Linefeed();

                            } else {
                                //discount

                                HeadMaster();
                                ProductAll();
                                Underline();
                                TotalAll();
                                EndText();
                                Linefeed();

                            }


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
                            intent.putExtra("ValueVat", txt_ValueVat.getText().toString());
                            startActivity(intent);

                        } else if (cash < totalall) {
                            Toast.makeText(getActivity(), getString(R.string.txt_receive_amont) + formatAmount.formatAmountDouble(totalall), Toast.LENGTH_SHORT).show();
                        }
                    }


                    //   Toast.makeText(getActivity(),companyDAO.InvoiceMaster().getCompanyName().toString(), Toast.LENGTH_SHORT).show();

                } else if (checkbox_print.isChecked() == false) {
                    if (edit_txt_cash.getText().toString().trim().replaceAll("\\.", "").matches("")) {
                        Toast.makeText(getActivity(), R.string.txt_please_enter_amount, Toast.LENGTH_LONG).show();
                    } else {
                        double cash = Double.parseDouble(edit_txt_cash.getText().toString().trim().replaceAll(",", ""));
                        double totalall = Double.parseDouble((txt_Totalall.getText().toString().trim().replaceAll(",", "")));
                        if (cash >= totalall) {
                            ProductSaleDAO productSaleDAO1 = new ProductSaleDAO(getActivity());
                            productSaleDAO1.open();
                            productSaleDAO1.addx();
                            productSaleDAO1.updatebill(txtdiscount);
                            productSaleDAO1.updateRunIdBill(Date().toString());


                            //   Toast.makeText(getActivity(), afterdate+"   "+Date().toString(), Toast.LENGTH_LONG).show();
                            productSaleDAO1.close();
                            //  Toast.makeText(getActivity(), "OK", Toast.LENGTH_LONG).show();
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
                            intent.putExtra("ValueVat", txt_ValueVat.getText().toString());
                            startActivity(intent);


                        } else if (cash < totalall) {
                            Toast.makeText(getActivity(), getString(R.string.txt_receive_amont) + formatAmount.formatAmountDouble(totalall), Toast.LENGTH_SHORT).show();
                        }
                    }


                }


                break;
            default:
                break;

        }
    }

    private void TotalAllNoDiscount() {


        String CashChangex = formatAmount.formatAmountDouble(cash) + "/" + formatAmount.formatAmountDouble(change);
        String x = "--------------------------------";
        String Totaltxt = "Total";
        String total = PrintFix.generatePrice(txt_NameTotal.getText().toString(), 27);
        String ValueVat = "VAT";
        String vat = PrintFix.generatePrice(txt_ValueVat.getText().toString(), 29);
        String changz = PrintFix.generatePrice(CashChangex, 21);
        String CashChange = "Cash/Change";


        printerController.PrinterController_Print(Totaltxt.getBytes());
        printerController.PrinterController_Print(total.getBytes());
        printerController.PrinterController_Print(x.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(ValueVat.getBytes());
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

    private void TotalAll() {
        String CashChangex = formatAmount.formatAmountDouble(cash) + "/" + formatAmount.formatAmountDouble(change);
        String x = "--------------------------------";
        String Totaltxt = "Total";
        String total = PrintFix.generatePrice(txt_NameTotal.getText().toString(), 27);
        String ValueVat = "VAT";
        String vat = PrintFix.generatePrice(txt_ValueVat.getText().toString(), 29);
        String changz = PrintFix.generatePrice(CashChangex, 21);
        String CashChange = "Cash/Change";
        String Discounttxt = "DISC." + "->" + (PrintFix.generateName(symbol, 5));
        String discount = PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.valueOf(txtdiscount)), 20);


        printerController.PrinterController_Print(Totaltxt.getBytes());
        printerController.PrinterController_Print(total.getBytes());
        printerController.PrinterController_Print(x.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(ValueVat.getBytes());
        printerController.PrinterController_Print(vat.getBytes());
        printerController.PrinterController_Print("\n".getBytes());
        printerController.PrinterController_Print(Discounttxt.getBytes());
        printerController.PrinterController_Print(discount.getBytes());
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

    private void HeadMaster() {
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        DateFormat df = new SimpleDateFormat("d/ MM/ yyyy, HH:mm");


        String date = df.format(Calendar.getInstance().getTime());
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        printerController.PrinterController_Font_Normal_mode();
        CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();

        String text1 = "welcome to " + companyDAO.InvoiceMaster().getCompanyName();
        String text2 = "Division " + companyDAO.InvoiceMaster().getDivisionName();
        String text3 = "Tel." + companyDAO.InvoiceMaster().getTelephone();
        String text4 = "TAX ID# " + companyDAO.InvoiceMaster().getTAXID();
        String text5 = "POS# " + companyDAO.InvoiceMaster().getPOSMachineID();
        String text6 = "BillNo # " + (productSaleDAO.InvoiceMaster().getBillId());


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

    private void edit_dialog(final EditText edit_dialog) {
        edit_dialog.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override

            public void afterTextChanged(Editable s) {

                edit_dialog.removeTextChangedListener(this);

                try {
                    String originalString = String.valueOf(edit_dialog.getText());

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###.##");
                    String formattedString = formatter.format(longval);


                    //setting text after format to EditText
                    edit_dialog.setText(formattedString);
                    edit_dialog.setSelection(edit_dialog.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                edit_dialog.addTextChangedListener(this);
            }


        });
    }


}


