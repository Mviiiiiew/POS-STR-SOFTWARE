package com.example.posstrsoftware.posstrsoftware.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleDeleteAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductDeleteFragment extends Fragment implements View.OnClickListener {

    ImageButton btn_back;
    ButtonRectangle btn_Back;
    ButtonRectangle btn_ok;
    ListView listView_SaleProductDelete;

    public SaleProductDeleteFragment() {
        super();
    }

    public static SaleProductDeleteFragment newInstance() {
        SaleProductDeleteFragment fragment = new SaleProductDeleteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delete_saleproduct, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView_SaleProductDelete = (ListView)rootView.findViewById(R.id.listView_SaleProductDelete);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        btn_Back = (ButtonRectangle)rootView.findViewById(R.id.btn_Back);
        btn_ok = (ButtonRectangle)rootView.findViewById(R.id.btn_ok);
        btn_Back.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        btn_Back.setRippleSpeed(40);
    }

    @Override
    public void onResume() {
        super.onResume();
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        final ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleDeleteList();
        productSaleDAO.close();
        final ProductSaleDeleteAdapter adapter = new ProductSaleDeleteAdapter(getActivity(), productSaleLists);
        listView_SaleProductDelete.setAdapter(adapter);





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
        if(btn_Back == v||btn_back ==v){
            getActivity().finish();
        }
    }
}
