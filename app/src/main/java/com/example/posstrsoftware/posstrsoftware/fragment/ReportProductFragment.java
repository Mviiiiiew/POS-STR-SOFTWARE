package com.example.posstrsoftware.posstrsoftware.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.POSD.controllers.PrinterController;
import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.model.ReportList;
import com.example.posstrsoftware.posstrsoftware.util.PrintFix;
import com.example.posstrsoftware.posstrsoftware.util.SelectDateFragment;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ReportProductFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_print;
    ButtonRectangle btn_back;
    RadioGroup radiogroup_date;
    RadioButton radio_date_day;
    RadioButton radio_date_between;
    EditText edit_date_day;
    EditText edit_date_one;
    EditText edit_date_two;
    String date;
    private PrinterController printerController = null;
    private String dateone;
    private String datetwo;

    public ReportProductFragment() {
        super();
    }

    public static ReportProductFragment newInstance() {
        ReportProductFragment fragment = new ReportProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_product, container, false);
        initInstances(rootView);
        edit_date_one.setFocusableInTouchMode(false);
        edit_date_two.setFocusableInTouchMode(false);
        edit_date_one.setEnabled(false);
        edit_date_two.setEnabled(false);
        radiogroup_date.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radiogroup_date.getCheckedRadioButtonId()) {
                    case R.id.radio_date_day:
                        edit_date_day.setEnabled(true);
                        edit_date_one.setEnabled(false);
                        edit_date_two.setEnabled(false);
                        break;
                    case R.id.radio_date_between:
                        edit_date_two.setEnabled(true);
                        edit_date_one.setEnabled(true);
                        edit_date_day.setEnabled(false);
                        break;
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Date();
    }

    private void Date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        edit_date_day.setText(date);
        edit_date_one.setText(date);
        edit_date_two.setText(date);

    }



    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        radiogroup_date = (RadioGroup)rootView.findViewById(R.id.radiogroup_date);
        radio_date_between=(RadioButton)rootView.findViewById(R.id.radio_date_between);
        radio_date_day=(RadioButton)rootView.findViewById(R.id.radio_date_day);
        edit_date_day=(EditText)rootView.findViewById(R.id.edit_date_day);
        edit_date_one=(EditText)rootView.findViewById(R.id.edit_date_one);
        edit_date_two=(EditText)rootView.findViewById(R.id.edit_date_two);
        btn_print = (ButtonRectangle)rootView.findViewById(R.id.btn_print);
        btn_back = (ButtonRectangle)rootView.findViewById(R.id.btn_back);
        btn_print.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        edit_date_day.setOnClickListener(this);
        edit_date_day.setTextIsSelectable(true);
        edit_date_day.setKeyListener(null);

        edit_date_one.setOnClickListener(this);
        edit_date_one.setTextIsSelectable(true);
        edit_date_one.setKeyListener(null);

        edit_date_two.setOnClickListener(this);
        edit_date_two.setTextIsSelectable(true);
        edit_date_two.setKeyListener(null);

        btn_back.setRippleSpeed(40);
        btn_print.setRippleSpeed(40);
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
        if(btn_print == v){
            ReportDAO reportDAO = new ReportDAO(getActivity());
            reportDAO.open();
            switch (radiogroup_date.getCheckedRadioButtonId()) {

                case R.id.radio_date_day:

                    HeadMaster();
                    Underline();
                    Description();
                    Underline();
                    Linefeed();




                    break;


                case R.id.radio_date_between:

                    dateone = edit_date_one.getText().toString();
                    datetwo = edit_date_two.getText().toString();
                    date = edit_date_day.getText().toString();
                    int oneday = Integer.parseInt(dateone.replaceAll("/", ""));
                    int twoday = Integer.parseInt(datetwo.replaceAll("/", ""));


                    if (oneday < twoday) {
                        HeadMasterbetween();
                        Underline();
                        DescriptionBetweenOneTwo();
                        Underline();
                        Linefeed();
                    } else if (oneday > twoday) {
                        HeadMasterbetween();
                        Underline();
                        DescriptionBetweenTwoOne();
                        Underline();
                        Linefeed();
                    } else{

                        HeadMasterbetween();
                        Underline();
                        DescriptionBetweenOneTwo();
                        Underline();
                        Linefeed();
                    }



                    //   Toast.makeText(getActivity(),oneday + twoday,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(),dateone.replaceAll("/","") + " - " +datetwo,Toast.LENGTH_SHORT).show();
                    break;

            }





        }else if(btn_back == v){
            getActivity().finish();
        }else if(edit_date_day == v){
            showDatePicker();
        }else if(edit_date_one == v){
            showDatePickerOne();
        }else if(edit_date_two == v){
            showDatePickerTwo();
        }

    }

    private void DescriptionBetweenTwoOne() {
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        ReportDAO reportDAO = new ReportDAO(getActivity());
        reportDAO.open();
        dateone = edit_date_one.getText().toString().replaceAll("/","");
        Log.d(dateone+"","Date1");
        datetwo = edit_date_two.getText().toString().replaceAll("/","");
        Log.d(datetwo+"","Date2");
        ArrayList<ReportList> reportLists = reportDAO.getAllPrintReportProductTwoOne(dateone+"",datetwo+"");

        for (ReportList bean : reportLists) {

            String Amount = "Amount#"+PrintFix.generatePrice(bean.getProductprintAmount()+"",25);
            String NameProduct = "Product#"+PrintFix.generatePrice(bean.getNameProduct()+"",24);
            String Unit = "Unit#"+PrintFix.generatePrice(bean.getNameUnit()+"",27);
            String SaleAmt = "SaleAmt#"+PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.parseDouble(bean.getSaleAmt()+"")),24);
            String SumSaleAmt = "SumSaleAmt#"+PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.parseDouble(bean.getSumSaleAmt()+"")),21);
            String Date = "DATE#"+PrintFix.generatePrice(bean.getPrintProductDate()+"",27);


            printerController = PrinterController.getInstance(getActivity());
            printerController.PrinterController_Open();
            printerController.PrinterController_Font_Normal_mode();
            printerController.PrinterController_Linefeed();
            printerController.PrinterController_Linefeed();
            printerController.PrinterController_Print(Date.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(NameProduct.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(Amount.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(Unit.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(SaleAmt.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(SumSaleAmt.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
        }

        reportDAO.close();

    }

    private void DescriptionBetweenOneTwo() {
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        ReportDAO reportDAO = new ReportDAO(getActivity());
        reportDAO.open();
        dateone = edit_date_one.getText().toString().replaceAll("/","");
        Log.d(dateone+"","Date1");
        datetwo = edit_date_two.getText().toString().replaceAll("/","");
        Log.d(datetwo+"","Date2");
        ArrayList<ReportList> reportLists = reportDAO.getAllPrintReportProductOneTwo(dateone+"",datetwo+"");

        for (ReportList bean : reportLists) {

            String Amount = "Amount#"+PrintFix.generatePrice(bean.getProductprintAmount()+"",25);
            String NameProduct = "Product#"+PrintFix.generatePrice(bean.getNameProduct()+"",24);
            String Unit = "Unit#"+PrintFix.generatePrice(bean.getNameUnit()+"",27);
            String SaleAmt = "SaleAmt#"+PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.parseDouble(bean.getSaleAmt()+"")),24);
            String SumSaleAmt = "SumSaleAmt#"+PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.parseDouble(bean.getSumSaleAmt()+"")),21);
            String Date = "DATE#"+PrintFix.generatePrice(bean.getPrintProductDate()+"",27);


            printerController = PrinterController.getInstance(getActivity());
            printerController.PrinterController_Open();
            printerController.PrinterController_Font_Normal_mode();
            printerController.PrinterController_Linefeed();
            printerController.PrinterController_Linefeed();
            printerController.PrinterController_Print(Date.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(NameProduct.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(Amount.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(Unit.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(SaleAmt.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(SumSaleAmt.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
        }

        reportDAO.close();


    }

    private void Description() {

        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        ReportDAO reportDAO = new ReportDAO(getActivity());
        reportDAO.open();
        date = edit_date_day.getText().toString();
        String x = date.replaceAll("/", "");
        Log.d("X",date);
       // Toast.makeText(getActivity(), x, Toast.LENGTH_SHORT).show();
        ArrayList<ReportList> reportLists = reportDAO.getAllPrintReportProduct(x);

        for (ReportList bean : reportLists) {

            String Amount = "Amount#"+PrintFix.generatePrice(bean.getProductprintAmount()+"",25);
            String NameProduct = "Product#"+PrintFix.generatePrice(bean.getNameProduct()+"",24);
            String Unit = "Unit#"+PrintFix.generatePrice(bean.getNameUnit()+"",27);
            String SaleAmt = "SaleAmt#"+PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.parseDouble(bean.getSaleAmt()+"")),24);
            String SumSaleAmt = "SumSaleAmt#"+PrintFix.generatePrice(formatAmount.formatAmountDouble(Double.parseDouble(bean.getSumSaleAmt()+"")),21);


            printerController = PrinterController.getInstance(getActivity());
            printerController.PrinterController_Open();
            printerController.PrinterController_Font_Normal_mode();
            printerController.PrinterController_Linefeed();
            printerController.PrinterController_Linefeed();
            printerController.PrinterController_Print(NameProduct.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(Amount.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(Unit.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(SaleAmt.getBytes());
            printerController.PrinterController_Print("\n".getBytes());
            printerController.PrinterController_Print(SumSaleAmt.getBytes());
            printerController.PrinterController_Print("\n".getBytes());


        }


        reportDAO.close();


    }

    private void showDatePickerTwo() {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        selectDateFragment.setCallBack(ondatetwo);
        selectDateFragment.show(getActivity().getSupportFragmentManager(),"DatePickerTwo");
    }
    DatePickerDialog.OnDateSetListener ondatetwo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (dayOfMonth < 10 && month < 10) {
                edit_date_two.setText(year + "/" + "0" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth < 10 && month >= 10) {
                edit_date_two.setText(year + "/" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth >= 10 && month < 10) {
                edit_date_two.setText(year + "/" +"0"+ (month + 1) + "/"  + dayOfMonth);
            } else {
                edit_date_two.setText(year + "/" + (month + 1) + "/" + dayOfMonth);

            }
        }
    };


    private void showDatePickerOne() {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        selectDateFragment.setCallBack(ondateone);
        selectDateFragment.show(getActivity().getSupportFragmentManager(),"DatePickerOne");
    }
    DatePickerDialog.OnDateSetListener ondateone = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (dayOfMonth < 10 && month < 10) {
                edit_date_one.setText(year + "/" + "0" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth < 10 && month >= 10) {
                edit_date_one.setText(year + "/" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth >= 10 && month < 10) {
                edit_date_one.setText(year + "/" +"0"+ (month + 1) + "/"  + dayOfMonth);
            } else {

                edit_date_one.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
            }
    }
    };

    private void showDatePicker() {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        selectDateFragment.setCallBack(ondate);
        selectDateFragment.show(getActivity().getSupportFragmentManager(),"DatePicker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (dayOfMonth < 10 && month < 10) {
                edit_date_day.setText(year + "/" + "0" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth < 10 && month >= 10) {
                edit_date_day.setText(year + "/" + (month + 1) + "/" + "0" + dayOfMonth);
            } else if (dayOfMonth >= 10 && month < 10) {
                edit_date_day.setText(year + "/" +"0"+ (month + 1) + "/"  + dayOfMonth);
            } else {

                edit_date_day.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
            }

        }
    };

    private void HeadMasterbetween() {
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
        String text2 = "Division " + companyDAO.InvoiceMaster().getDivisionName() ;
        String text3 = "Tel. " + companyDAO.InvoiceMaster().getTelephone();
        String text4 = "TAX ID# " + companyDAO.InvoiceMaster().getTAXID();
        String text5 = "POS# " + companyDAO.InvoiceMaster().getPOSMachineID();
        String text6 = "Re.Date# " +edit_date_one.getText().toString()+"-"+edit_date_two.getText().toString() ;
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
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
        printerController.PrinterController_Linefeed();


        productSaleDAO.close();
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

    private void HeadMaster() {
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        DateFormat df = new SimpleDateFormat(" d /MM /yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        printerController = PrinterController.getInstance(getActivity());
        printerController.PrinterController_Open();
        printerController.PrinterController_Font_Normal_mode();
        CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();
        String text1 = "welcome to " + companyDAO.InvoiceMaster().getCompanyName();
        String text2 = "Division " + companyDAO.InvoiceMaster().getDivisionName() ;
        String text3 = "Tel." + companyDAO.InvoiceMaster().getTelephone();
        String text4 = "TAX ID# " + companyDAO.InvoiceMaster().getTAXID();
        String text5 = "POS# " + companyDAO.InvoiceMaster().getPOSMachineID();
        String text6 = "ReportDATE# " + edit_date_day.getText().toString();
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
        printerController.PrinterController_Linefeed();
        productSaleDAO.close();
        companyDAO.close();
    }

}
