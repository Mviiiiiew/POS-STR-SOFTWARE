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

    ButtonRectangle btn_back;
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
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        final ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleDeleteList();
        productSaleDAO.close();
        final ProductSaleDeleteAdapter adapter = new ProductSaleDeleteAdapter(getActivity(), productSaleLists);
        listView_SaleProductDelete.setAdapter(adapter);

        listView_SaleProductDelete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ProductSaleList productSaleLists1 = new ProductSaleList();
                productSaleLists1.setId((int) adapter.getItemId(position));
                productSaleLists1.setProductSale(((ProductSaleList) adapter.getItem(position)).getProductSale());
                ProductSaleDAO productSaleDAO1 = new ProductSaleDAO(getActivity());
                productSaleDAO1.open();
                productSaleDAO1.delete_product_id(productSaleLists1);
                productSaleDAO1.close();
                productSaleLists.remove(position);
                adapter.notifyDataSetChanged();



            }
        });
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView_SaleProductDelete = (ListView)rootView.findViewById(R.id.listView_SaleProductDelete);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        btn_back.setRippleSpeed(50);

    }

    @Override
    public void onResume() {
        super.onResume();



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
        if(btn_back == v){
            getActivity().finish();
        }
    }
}
