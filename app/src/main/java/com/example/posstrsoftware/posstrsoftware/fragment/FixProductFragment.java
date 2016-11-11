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
public class FixProductFragment extends Fragment implements View.OnClickListener {
    ButtonRectangle btn_back;
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
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        editText_product = (EditText) rootView.findViewById(R.id.editText_product);
        btn_edit_product = (ButtonRectangle) rootView.findViewById(R.id.btn_edit_product);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);

        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_edit_product.setOnClickListener(this);
        btn_delete.setRippleSpeed(15);
        btn_edit_product.setRippleSpeed(15);
        btn_back.setRippleSpeed(40);


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
        ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
        int ex = 0;
        if (v == btn_edit_product) {
            if (editText_product.getText().toString().trim().replaceAll("", "").matches("")) {
                Toast.makeText(getActivity(), "Not Name Product", Toast.LENGTH_SHORT).show();
            } else {
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                ex = productDAO.update(eProductList);
                productDAO.close();
                if (ex == 0) {
                    AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                    alertDialogder.setMessage("Repeat Product");
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
            getActivity().finish();
        } else if (btn_delete == v) {
            ProductDAO productDAODel = new ProductDAO(getActivity());
            productDAODel.open();
            productDAODel.delete(editproductList);
            productDAODel.close();
            getActivity().finish();

        }
    }
}
