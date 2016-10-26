package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportActionModeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.AddProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.FixProductActivity;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductMainFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    Toolbar my_toolbar;
    SearchView searchViewProduct;
    ListView listView_Product;
    ImageButton btn_back;
    ImageButton btn_add_product;



    public ProductMainFragment() {
        super();
    }

    public static ProductMainFragment newInstance() {
        ProductMainFragment fragment = new ProductMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_product, container, false);

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        searchViewProduct = (SearchView) rootView.findViewById(R.id.searchViewProduct);
        searchViewProduct.setQueryHint("Search..");
        listView_Product = (ListView)rootView.findViewById(R.id.listView_Product);
        btn_add_product = (ImageButton) rootView.findViewById(R.id.btn_add_product);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        btn_add_product.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        listView_Product.setOnItemClickListener(this);


    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        final ProductDAO productDAO = new ProductDAO(getActivity());
        productDAO.open();
        final ArrayList<ProductList> myProductList = productDAO.getAllProductList();
        productDAO.close();
        final ProductAdapter objAdapter = new ProductAdapter(getActivity(),myProductList);
        listView_Product.setAdapter(objAdapter);

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
        if (btn_back == v) {
            getActivity().finish();
        } else if (btn_add_product == v) {
            Intent intent = new Intent(getActivity(), AddProductActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final ProductDAO productDAO = new ProductDAO(getActivity());
        productDAO.open();
        final ArrayList<ProductList> myProductList = productDAO.getAllProductList();
        productDAO.close();
        final ProductAdapter objAdapter = new ProductAdapter(getActivity(),myProductList);
        Intent editIntent = new Intent(getActivity(), FixProductActivity.class);
        editIntent.putExtra("editProduct", (Serializable) objAdapter.getItem(position));
        startActivity(editIntent);
    }
}
