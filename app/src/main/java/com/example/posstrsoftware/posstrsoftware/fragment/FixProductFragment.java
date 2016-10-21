package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.ProductMainActivity;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FixProductFragment extends Fragment implements View.OnClickListener {
    ImageButton btn_back;
    EditText editText_product;
    ButtonRectangle btn_edit_product;
    ButtonRectangle btn_delete;

    public FixProductFragment() {
        super();
    }

    public static FixProductFragment newInstance() {
        FixProductFragment fragment = new FixProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fix_product, container, false);
        initInstances(rootView);
        ProductList editProductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
        editText_product.setText(editProductList.getProductText());
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        editText_product = (EditText) rootView.findViewById(R.id.editText_product);
        btn_edit_product = (ButtonRectangle) rootView.findViewById(R.id.btn_edit_product);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        btn_back.setOnClickListener(this);


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
        if (btn_back == v){
            getActivity().finish();
        }

    }
}
