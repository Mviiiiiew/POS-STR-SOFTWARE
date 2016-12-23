package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
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
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleDeleteAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.model.ReportList;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductDeleteFragment extends Fragment implements View.OnClickListener {

    ImageButton btn_back;
    ListView listView_SaleProductDelete;
    String mProduct;



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
        Intent intent = getActivity().getIntent();
        mProduct = intent.getStringExtra("mProduct");
        Log.d("mProductx",mProduct);


        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        final ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleDelete(mProduct);
        productSaleDAO.close();
        final ProductSaleDeleteAdapter adapter = new ProductSaleDeleteAdapter(getActivity(), productSaleLists);
        listView_SaleProductDelete.setAdapter(adapter);


        listView_SaleProductDelete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setMessage("คุณแน่ใจว่าจะทำการลบสินค้าทั้งหมด :  "+((ProductSaleList)adapter.getItem(position)).getProductSale());
                alertDialogder.setTitle("ลบสินค้า 'ทั้งหมด' ");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductSaleList productSaleLists1 = new ProductSaleList();
                        productSaleLists1.setId((int) adapter.getItemId(position));
                        productSaleLists1.setProductSale(((ProductSaleList) adapter.getItem(position)).getProductSale());
                        ProductSaleDAO productSaleDAO1 = new ProductSaleDAO(getActivity());
                        productSaleDAO1.open();
                        productSaleDAO1.delete_product_name(productSaleLists1);
                        productSaleDAO1.close();
                        getActivity().finish();
                    }
                });
                alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogder.show();
                return false;
            }
        });
        listView_SaleProductDelete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                 AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setMessage("คุณแน่ใจว่าจะทำการลบสินค้า :  "+((ProductSaleList)adapter.getItem(position)).getProductSale());
                alertDialogder.setTitle("ลบสินค้า");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                  ///PRODUCTSALELIST///
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
                alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();
            }
        });

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView_SaleProductDelete = (ListView)rootView.findViewById(R.id.listView_SaleProductDelete);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
             btn_back.setOnClickListener(this);


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
