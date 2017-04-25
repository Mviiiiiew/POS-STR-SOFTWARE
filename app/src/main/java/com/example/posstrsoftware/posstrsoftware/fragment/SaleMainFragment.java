package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductManualActivity;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleMainFragment extends Fragment implements View.OnClickListener {
    ImageButton image_btn_barcode;
    ImageButton image_btn_salemanual;

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
        image_btn_barcode = (ImageButton) rootView.findViewById(R.id.image_btn_barcode);
        image_btn_salemanual = (ImageButton) rootView.findViewById(R.id.image_btn_salemanual);
        image_btn_barcode.setOnClickListener(this);
        image_btn_salemanual.setOnClickListener(this);

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
        if (image_btn_barcode == v) {
            Intent intent = new Intent(getActivity(), SaleProductActivity.class);
            startActivity(intent);

        } else if (image_btn_salemanual == v) {
            Intent intent = new Intent(getActivity(), SaleProductManualActivity.class);
            startActivity(intent);

        }
    }
}
