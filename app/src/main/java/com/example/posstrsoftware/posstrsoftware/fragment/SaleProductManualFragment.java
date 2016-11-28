package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductDeleteActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductListManualAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleManualAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.model.ReportList;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductManualFragment extends Fragment implements View.OnClickListener {
    ListView listview_salemanual;
    ListView listView_SaleProductmanual;
    ButtonRectangle btn_clear;
    ButtonRectangle btn_backz;
    SearchView searchViewProduct;
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
        searchViewProduct = (SearchView) rootView.findViewById(R.id.searchViewProduct);
        searchViewProduct.setQueryHint("Search..");
        btn_backz = (ButtonRectangle) rootView.findViewById(R.id.btn_backz);
        btn_clear = (ButtonRectangle) rootView.findViewById(R.id.btn_clear);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        btn_Pay = (ButtonRectangle) rootView.findViewById(R.id.btn_Pay);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        txt_cost = (TextView) rootView.findViewById(R.id.txt_cost);
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

    }


    private String Date() {
        java.text.DateFormat df = new java.text.SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(java.util.Calendar.getInstance().getTime());
        return date;
    }


    @Override
    public void onResume() {
        super.onResume();
        List<String> list = new ArrayList<>();
        list.add(Date());

        Log.d("date", String.valueOf(list));


        final ProductDAO productDAO = new ProductDAO(getActivity());
        productDAO.open();
        final ArrayList<ProductList> myProductList = productDAO.getAllProductList();
        productDAO.close();
        final ProductListManualAdapter objAdapter = new ProductListManualAdapter(getActivity(), myProductList);
        listview_salemanual.setAdapter(objAdapter);

        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
        Double x = 0.0;
        DecimalFormat money_format = new DecimalFormat("###,###,##0.00");
        for (ProductSaleList bean : productSaleLists) {
            x += Double.valueOf(bean.getPrice());
        }
        txt_cost.setText(money_format.format((x)));


        final ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
        listView_SaleProductmanual.setAdapter(adapter);

        listview_salemanual.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Double total = 0.0;
                ProductSaleList productSaleList = new ProductSaleList();
                productSaleList.setPrice(Double.valueOf(((ProductList) objAdapter.getItem(position)).getProductprice()));
                productSaleList.setProductSale(((ProductList) objAdapter.getItem(position)).getProductText());
                ReportList reportList = new ReportList();
                reportList.setNameProduct(((ProductList) objAdapter.getItem(position)).getProductText());
                reportList.setPrice(Double.valueOf(((ProductList) objAdapter.getItem(position)).getProductprice()));
                ReportDAO reportDAO = new ReportDAO(getActivity());
                reportDAO.open();
                reportDAO.add(reportList);
                ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                productSaleDAO.open();
                productSaleDAO.add(productSaleList);
                ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                final ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
                listView_SaleProductmanual.setAdapter(adapter);
                for (ProductSaleList bean : productSaleLists) {
                    total += Double.valueOf(bean.getPrice());

                }

                txt_cost.setText(formatAmount.formatAmountDouble(total));
                productSaleDAO.close();


            }
        });
        searchViewProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                objAdapter.getFilter().filter(query);
                return false;
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
        if (btn_clear == v) {
            AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
            alertDialogder.setMessage("คุณแน่ใจว่าจะทำการเริ่มรายการใหม่");
            alertDialogder.setTitle("เริ่มรายการใหม่ ?");
            alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProductSaleList productSaleList = new ProductSaleList();
                    ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                    productSaleDAO.open();
                    productSaleDAO.clear(productSaleList);
                    ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                    final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
                    listView_SaleProductmanual.setAdapter(adapter);
                    productSaleDAO.close();
                    txt_cost.setText("0.00");
                }
            });
            alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialogder.show();

        } else if (btn_backz == v || btn_back == v) {

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            this.getActivity().finish();

        } else if (btn_delete == v) {
            Intent intent = new Intent(getActivity(), SaleProductDeleteActivity.class);
            startActivity(intent);
        } else if (btn_Pay == v) {
            Intent intent = new Intent(getActivity(), DiscountMainActivity.class);
            intent.putExtra("total", txt_cost.getText());
            startActivity(intent);


        }
    }
}
