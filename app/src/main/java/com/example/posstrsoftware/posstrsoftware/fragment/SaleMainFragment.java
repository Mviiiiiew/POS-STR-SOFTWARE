package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductManualActivity;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleMainFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_Sale;
    ButtonRectangle btn_Salebarcode;

    public SaleMainFragment() {
        super();
    }

    public static SaleMainFragment newInstance() {
        SaleMainFragment fragment = new SaleMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_sale, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_Sale = (ButtonRectangle) rootView.findViewById(R.id.btn_Sale);
        btn_Salebarcode=(ButtonRectangle)rootView.findViewById(R.id.btn_Salebarcode);
        btn_Salebarcode.setOnClickListener(this);
        btn_Sale.setOnClickListener(this);
        btn_Salebarcode.setRippleSpeed(25);
        btn_Sale.setRippleSpeed(25);
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
        if(btn_Sale == v) {

            Intent intent = new Intent(getActivity(), SaleProductActivity.class);
            startActivity(intent);
        }else if(btn_Salebarcode == v){
            Intent intent = new Intent(getActivity(), SaleProductManualActivity.class);
            startActivity(intent);
        }
    }
}
