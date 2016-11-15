package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.DiscountMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductDeleteActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductListManualAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleManualAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.util.ArrayList;



/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductManualFragment extends Fragment implements View.OnClickListener {
    ListView listview_salemanual;
    ListView listView_SaleProductmanual;
    ButtonRectangle btn_clear;
    ButtonRectangle btn_backz;
    TextView txt_cost;
    ButtonRectangle btn_delete;
    ImageButton btn_back;
    ButtonRectangle btn_Pay;



    public SaleProductManualFragment() {
        super();
    }

    public static SaleProductManualFragment newInstance() {
        SaleProductManualFragment fragment = new SaleProductManualFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saleproduct_manual, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView_SaleProductmanual = (ListView) rootView.findViewById(R.id.listView_SaleProductmanual);
        listview_salemanual = (ListView) rootView.findViewById(R.id.listview_salemanual);
        btn_backz = (ButtonRectangle)rootView.findViewById(R.id.btn_backz);
        btn_clear= (ButtonRectangle)rootView.findViewById(R.id.btn_clear);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        btn_Pay = (ButtonRectangle) rootView.findViewById(R.id.btn_Pay);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        txt_cost = (TextView)rootView.findViewById(R.id.txt_cost);
        btn_Pay.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_backz.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_Pay.setRippleSpeed(40);
        btn_delete.setRippleSpeed(40);
        btn_clear.setRippleSpeed(40);
        btn_backz.setRippleSpeed(40);



    }

    @Override
    public void onPause() {
        super.onPause();
        saveradio();
    }

    private void saveradio() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cost", (txt_cost.getText().toString()));
        editor.commit();
    }

    private void loadradio() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        txt_cost.setText(sharedPreferences.getString("cost", txt_cost.getText().toString()));


    }

    @Override
    public void onResume() {
        super.onResume();
        loadradio();
       final ProductDAO productDAO = new ProductDAO(getActivity());
        productDAO.open();
        final ArrayList<ProductList> myProductList = productDAO.getAllProductList();
        productDAO.close();
        final ProductListManualAdapter objAdapter = new ProductListManualAdapter(getActivity(),myProductList);
        listview_salemanual.setAdapter(objAdapter);
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
        final ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
        listView_SaleProductmanual.setAdapter(adapter);
        listview_salemanual.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Double total = 0.0;
                ProductSaleList productSaleList = new ProductSaleList();
                productSaleList.setPrice(String.valueOf(((ProductList)objAdapter.getItem(position)).getProductprice()));
                productSaleList.setProductSale(((ProductList)objAdapter.getItem(position)).getProductText());
                ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                productSaleDAO.open();
                productSaleDAO.add(productSaleList);
                ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                final ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
                listView_SaleProductmanual.setAdapter(adapter);
                for (ProductSaleList bean : productSaleLists) {
                    total += Double.valueOf(bean.getPrice());

                }
                DecimalFormat money_format = new DecimalFormat("###,###,###.00");
                txt_cost.setText(money_format.format((total)));
                productSaleDAO.close();
                Toast.makeText(getActivity(),"Click Short Successfully"+"   "+((ProductList)objAdapter.getItem(position)).getProductText(), Toast.LENGTH_SHORT).show();


            }
        });

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
        if(btn_clear == v){
            ProductSaleList productSaleList = new ProductSaleList();
            ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
            productSaleDAO.open();
            productSaleDAO.clear(productSaleList);
            ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
            final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
            listView_SaleProductmanual.setAdapter(adapter);
            productSaleDAO.close();
            txt_cost.setText("");


        }else if(btn_backz == v||btn_back ==v){
            getActivity().finish();
        }else if (btn_delete == v) {
            Intent intent = new Intent(getActivity(), SaleProductDeleteActivity.class);
            startActivity(intent);
        }else if (btn_Pay == v) {
            Intent intent = new Intent(getActivity(), DiscountMainActivity.class);
            intent.putExtra("total", txt_cost.getText());
            startActivity(intent);

        }
    }
}
