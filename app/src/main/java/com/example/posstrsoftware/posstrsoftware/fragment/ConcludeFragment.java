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
import android.widget.TextView;


import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.PayMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductManualActivity;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ConcludeFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_manual;
    ButtonRectangle btn_barcode;
    TextView txt_mDate;
    TextView txt_mTotal;
    TextView txt_mDiscount;
    TextView txt_mTotalAll;
    TextView txt_mCash;
    TextView txt_mChange;
    TextView txt_symbolDiscount;
    String mTotal;
    String mDiscount;
    String mTotalAll;
    String mCash;
    String mChange;
    String symbol;
    String discount;
    int processmanual;
    int processbarcode;

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

        Log.d("processm",processmanual+"");
        processbarcode = intent.getIntExtra("processbarcode",1);
        Log.d("processb",processbarcode+"");
        mTotal = intent.getStringExtra("mTotal");
        mDiscount = intent.getStringExtra("mDiscount");
        mTotalAll = intent.getStringExtra("mTotalAll");
        mCash = intent.getStringExtra("mCash");
        mChange = intent.getStringExtra("mChange");
        symbol = intent.getStringExtra("symbol");
        discount = intent.getStringExtra("discount");
        initInstances(rootView);
        Date();
        Total();

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_manual = (ButtonRectangle) rootView.findViewById(R.id.btn_manual);
        btn_barcode = (ButtonRectangle) rootView.findViewById(R.id.btn_barcode);
        txt_mTotal = (TextView) rootView.findViewById(R.id.txt_mTotal);
        txt_mDate = (TextView) rootView.findViewById(R.id.txt_mDate);
        txt_mDiscount = (TextView) rootView.findViewById(R.id.txt_mDiscount);
        txt_mTotalAll = (TextView)rootView.findViewById(R.id.txt_mTotalAll);
        txt_mCash = (TextView)rootView.findViewById(R.id.txt_mCash);
        txt_mChange = (TextView)rootView.findViewById(R.id.txt_mChange);
        txt_symbolDiscount = (TextView)rootView.findViewById(R.id.txt_symbolDiscount);
        btn_barcode.setRippleSpeed(40);
        btn_manual.setRippleSpeed(40);
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
        Discount();
        TotalAll();
        Cash();
        Change();

    }

    private void Change() {
        txt_mChange.setText(mChange);
    }

    private void Cash() {
        txt_mCash.setText (formatAmount.formatAmountDouble(Double.valueOf(mCash.replaceAll(",",""))));
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
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

        }


    }
}
