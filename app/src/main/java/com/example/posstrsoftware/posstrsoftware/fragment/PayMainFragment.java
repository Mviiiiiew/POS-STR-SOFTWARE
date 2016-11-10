package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.PojoDisCount;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PayMainFragment extends Fragment implements View.OnClickListener {

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
    TextView txt_Change;
    TextView txt_NameTotal;
    EditText edit_txt_cash;
    Double total = 0.0;
    Double change = 0.0;
    Double cash = 0.0;
    String x;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_pay, container, false);
        initInstances(rootView);
        Intent intent = getActivity().getIntent();
        x = intent.getStringExtra("total");
        txt_NameTotal.setText(x.toString());

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
        txt_NameTotal = (TextView) rootView.findViewById(R.id.txt_NameTotal);
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
        txt_Change = (TextView) rootView.findViewById(R.id.txt_Change);
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
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+1.0));

                break;
            case R.id.btn_cost_5:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+5.0));
                break;
            case R.id.btn_cost_10:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+10.0));
                break;
            case R.id.btn_cost_20:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0"
                        :edit_txt_cash.getText().toString().replaceAll(",",""))+20.0));
                break;
            case R.id.btn_cost_50:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+50.0));
                break;
            case R.id.btn_cost_100:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+100.0));
                break;
            case R.id.btn_cost_500:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+500.0));
                break;
            case R.id.btn_cost_1000:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+1000.0));
                break;
            case R.id.btn_cost_050:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+0.50));

                break;
            case R.id.btn_cost_025:
                edit_txt_cash.setText(formatAmount.formatAmountDouble(Double.parseDouble(edit_txt_cash.getText().toString().equals("")?"0.0":
                        edit_txt_cash.getText().toString().replaceAll(",",""))+0.25));
                break;
            case R.id.btn_Pay:
                try {
                    total = Double.valueOf((txt_NameTotal.getText().toString().replace(",", "")));

                } catch (NumberFormatException e){

                }
                try {
                    cash = Double.valueOf(edit_txt_cash.getText().toString().replaceAll(",", ""));

                } catch (NumberFormatException e){

                }
                change = cash - total;
                txt_Change.setText(formatAmount.formatAmountDouble(Double.valueOf(change.toString())));


                break;
            default:
                break;

        }
    }


}


