package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.gc.materialdesign.views.ButtonRectangle;


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
    TextView txt_NameTotal;
    EditText txt_cash;
    String total;
    Double cash = 0.0;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_pay, container, false);
        initInstances(rootView);
        Intent intent = getActivity().getIntent();
        total = intent.getStringExtra("total");
        txt_NameTotal.setText(total);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        txt_NameTotal = (TextView) rootView.findViewById(R.id.txt_NameTotal);
        txt_cash = (EditText) rootView.findViewById(R.id.txt_cash);
        btn_cost_1000 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_1000);
        btn_cost_100 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_100);
        btn_cost_10 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_10);
        btn_cost_1 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_1);
        btn_cost_5 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_5);
        btn_cost_20 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_20);
        btn_cost_50 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_50);
        btn_cost_500 = (ButtonRectangle) rootView.findViewById(R.id.btn_cost_500);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        btn_cost_1.setOnClickListener(this);
        btn_cost_5.setOnClickListener(this);
        btn_cost_10.setOnClickListener(this);
        btn_cost_20.setOnClickListener(this);
        btn_cost_50.setOnClickListener(this);
        btn_cost_100.setOnClickListener(this);
        btn_cost_500.setOnClickListener(this);
        btn_cost_1000.setOnClickListener(this);
        btn_cost_1000.setRippleSpeed(15);
        btn_cost_100.setRippleSpeed(15);
        btn_cost_10.setRippleSpeed(15);
        btn_cost_1.setRippleSpeed(15);
        btn_cost_5.setRippleSpeed(15);
        btn_cost_20.setRippleSpeed(15);
        btn_cost_50.setRippleSpeed(15);
        btn_cost_500.setRippleSpeed(15);
        btn_back.setRippleSpeed(15);

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
                cash += 1.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_5:
                cash += 5.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_10:
                cash += 10.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_20:
                cash += 20.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_50:
                cash += 50.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_100:
                cash += 100.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_500:
                cash += 500.0;
                txt_cash.setText(cash.toString());
                break;
            case R.id.btn_cost_1000:
                cash += 1000.0;
                txt_cash.setText(cash.toString());
                break;
            default:
                break;

        }


    }
}
