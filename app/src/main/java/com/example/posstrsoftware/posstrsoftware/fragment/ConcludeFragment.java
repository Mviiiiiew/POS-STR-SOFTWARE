package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.PayMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductManualActivity;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ConcludeFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_manual;
    ButtonRectangle btn_barcode;

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
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_manual = (ButtonRectangle)rootView.findViewById(R.id.btn_manual);
        btn_barcode = (ButtonRectangle)rootView.findViewById(R.id.btn_barcode);
        btn_barcode.setRippleSpeed(40);
        btn_manual.setRippleSpeed(40);
        btn_barcode.setOnClickListener(this);
        btn_manual.setOnClickListener(this);
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
if(btn_manual == v) {
    ProductSaleList productSaleList = new ProductSaleList();
    ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
    productSaleDAO.open();
    productSaleDAO.clear(productSaleList);
    productSaleDAO.close();

    Intent intent = new Intent(getActivity(), SaleProductManualActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
}else if(btn_barcode == v){
    ProductSaleList productSaleList = new ProductSaleList();
    ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
    productSaleDAO.open();
    productSaleDAO.clear(productSaleList);
    productSaleDAO.close();

    Intent intent = new Intent(getActivity(), SaleProductActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);

}


    }
}
