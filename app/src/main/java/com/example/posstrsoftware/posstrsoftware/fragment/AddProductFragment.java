package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.ProductMainActivity;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class AddProductFragment extends Fragment implements View.OnClickListener {
    EditText editText_Product;
    ImageButton btn_back;
    ButtonRectangle btn_save;
    ButtonRectangle btn_clear;
    EditText editText_Price;

    public AddProductFragment() {
        super();
    }

    public static AddProductFragment newInstance() {
        AddProductFragment fragment = new AddProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        editText_Product = (EditText) rootView.findViewById(R.id.editText_Product);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        btn_clear = (ButtonRectangle)rootView.findViewById(R.id.btn_clear);
        editText_Price = (EditText)rootView.findViewById(R.id.editText_Price);
        btn_save.setRippleSpeed(15);
        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_clear.setRippleSpeed(15);


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
        int ex = 0;
        if (v == btn_save) {
            if (editText_Product.getText().toString().trim().replaceAll("", "").matches("")) {
                Toast.makeText(getActivity(), "< Please input Product >", Toast.LENGTH_SHORT).show();
            } else if (editText_Price.getText().toString().matches("")){
                Toast.makeText(getActivity(), "< Please input Price >", Toast.LENGTH_SHORT).show();
            }

            else {
                ProductList productList = new ProductList();
                productList.setProductText(Util_String.getGennerlateString(editText_Product.getText().toString()));
                productList.setProductprice(Integer.parseInt(editText_Price.getText().toString()));
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                ex = productDAO.add(productList);
                productDAO.close();
                if (ex == 0) {
                    AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                    alertDialogder.setMessage("< Please change nameproduct because repeat >");
                    alertDialogder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogder.show();
                } else {
                    getActivity().finish();
                }
            }
        } else if (btn_back == v) {
            Intent intent = new Intent(getActivity(), ProductMainActivity.class);
            startActivity(intent);

        }else if(btn_clear == v){
            editText_Product.setText("");
        }
    }
}
