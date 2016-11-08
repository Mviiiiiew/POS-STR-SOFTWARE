package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.PayMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductDeleteActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.gc.materialdesign.views.ButtonRectangle;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    Double total;
    ButtonRectangle btn_Pay;
    ListView listView_SaleProduct;
    ImageButton btn_back;
    EditText edit_Barcode;
    ButtonRectangle btn_clear;
    TextView txt_cost;
    ButtonRectangle btn_delete;



    public SaleProductFragment() {
        super();
    }

    public static SaleProductFragment newInstance() {
        SaleProductFragment fragment = new SaleProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saleproduct, container, false);
        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        listView_SaleProduct = (ListView) rootView.findViewById(R.id.listView_SaleProduct);
        edit_Barcode = (EditText) rootView.findViewById(R.id.edit_Barcode);
        btn_delete = (ButtonRectangle)rootView.findViewById(R.id.btn_delete);
        btn_Pay = (ButtonRectangle) rootView.findViewById(R.id.btn_Pay);
        txt_cost = (TextView) rootView.findViewById(R.id.txt_cost);
        btn_clear = (ButtonRectangle) rootView.findViewById(R.id.btn_clear);
        btn_Pay.setRippleSpeed(15);
        btn_Pay.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_clear.setRippleSpeed(15);
        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_delete.setRippleSpeed(50);



    }

    @Override
    public void onResume() {
        super.onResume();
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        final ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
        productSaleDAO.close();
        final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);

        listView_SaleProduct.setAdapter(adapter);

        listView_SaleProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(" Delete Yes / No ?");

                alertDialogder.setCancelable(false);
                alertDialogder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
                alertDialogder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        ProductSaleList productSaleLists1 = new ProductSaleList();
                        productSaleLists1.setProductSale(((ProductSaleList) adapter.getItem(position)).getProductSale());

                        ProductSaleDAO productSaleDAO1 = new ProductSaleDAO(getActivity());
                        productSaleDAO1.open();
                        productSaleDAO1.delete(productSaleLists1);
                        productSaleDAO1.close();

                        productSaleLists.remove(position);
                        adapter.notifyDataSetChanged();

                    }

                });

                alertDialogder.show();


                return false;
            }
        });
        edit_Barcode.setOnEditorActionListener(this);
        Double x = 0.0;
        DecimalFormat money_format = new DecimalFormat("###,###,###.00");
        for (ProductSaleList bean : productSaleLists) {
            x += Double.valueOf(bean.getPrice());
        }
        txt_cost.setText(money_format.format((x)));



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
        outState.putString("x", (String.valueOf(txt_cost.getText())));

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
            total = Double.valueOf(savedInstanceState.getString("x"));

        }

    }

    @Override
    public void onClick(View v) {
        if (btn_back == v) {
            getActivity().finish();

        } else if (btn_clear == v) {
            ProductSaleList productSaleList = new ProductSaleList();
            ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
            productSaleDAO.open();
            productSaleDAO.clear(productSaleList);
            ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
            final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
            listView_SaleProduct.setAdapter(adapter);
            productSaleDAO.close();
            txt_cost.setText("");
        } else if (btn_Pay == v) {
            Intent intent = new Intent(getActivity(), PayMainActivity.class);
            intent.putExtra("total", txt_cost.getText());
            startActivity(intent);

        } else if (btn_delete == v){
            Intent intent = new Intent(getActivity(), SaleProductDeleteActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Double total = 0.0;
        ProductDAO productDAO = new ProductDAO(getActivity());

        if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
            productDAO.open();
            ProductSaleList productSaleList = new ProductSaleList();
            productSaleList.setProductSale(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getProductSale());
            productSaleList.setPrice(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getPrice());
            productDAO.close();
            ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
            productSaleDAO.open();
            productSaleDAO.add(productSaleList);
            ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
            final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
            listView_SaleProduct.setAdapter(adapter);

            DecimalFormat money_format = new DecimalFormat("###,###,###.00");
            for (ProductSaleList bean : productSaleLists) {
                total += Double.valueOf(bean.getPrice());

            }

            txt_cost.setText(money_format.format((total)));
            adapter.notifyDataSetChanged();
            productSaleDAO.close();
            edit_Barcode.setText("");


            return true;

        }
        return false;
    }


}
