package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.GroupMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.ProductMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.UnitMainActivity;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductSetMainFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_Set_Unit;
    ButtonRectangle btn_Set_Group;
    ButtonRectangle btn_Set_Product;

    public ProductSetMainFragment() {
        super();
    }

    public static ProductSetMainFragment newInstance() {
        ProductSetMainFragment fragment = new ProductSetMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_productset, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_Set_Unit = (ButtonRectangle)rootView.findViewById(R.id.btn_Set_Unit);
        btn_Set_Group = (ButtonRectangle)rootView.findViewById(R.id.btn_Set_Group);
        btn_Set_Product = (ButtonRectangle)rootView.findViewById(R.id.btn_Set_Product);
        btn_Set_Group.setOnClickListener(this);
        btn_Set_Group.setRippleSpeed(28);
        btn_Set_Unit.setOnClickListener(this);
        btn_Set_Unit.setRippleSpeed(28);
        btn_Set_Product.setOnClickListener(this);
        btn_Set_Product.setRippleSpeed(28);
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


        if(btn_Set_Unit == v){
            Intent intent = new Intent(getContext(),UnitMainActivity.class);
            startActivity(intent);
        } else if(btn_Set_Product == v){
            Intent intent = new Intent(getContext(), ProductMainActivity.class);
            startActivity(intent);

        }else if(btn_Set_Group == v){
            Intent intent = new Intent(getContext(),GroupMainActivity.class);
            startActivity(intent);
        }



    }
}
